IF EXISTS (SELECT name
   FROM   sysobjects
   WHERE  name = 'proknjizi_popisni_dokument' AND type = 'P')
   DROP PROCEDURE [proknjizi_popisni_dokument]
GO
---------------------------------------------------------
--   Procedura za knjizenje popisnog dokumenta
---------------------------------------------------------
CREATE PROCEDURE dbo.[proknjizi_popisni_dokument]
   @PROM_DOK_ID INT
AS

---------------------------------------------------------
--  Provera ispunjenosti uslova:
--	1. status dokumenta mora biti “u fazi formiranja”
--	2. dokument mora imati bar jednu stavku
--	3. poslovna godina dokumenta ne sme biti zaključena
--	4. datum prometnog dokumenta ne sme biti veći od današnjeg
---------------------------------------------------------
 DECLARE
  @BROJ_ANALITIKA NUMERIC(5),
  @BROJ_TRANSAKCIJA INT,
  @PORUKA VARCHAR(100),
  @OK bit,
   @PROM_DOK_VRST INT,
   @PROM_DOK_STAT INT

  SET @OK = 0
  SELECT @PROM_DOK_STAT = PROM_DOK_STAT,
		 @PROM_DOK_VRST = PROM_DOK_VRST
  FROM dbo.PROMETNI_DOKUMENT WHERE PROM_DOK_ID = @PROM_DOK_ID
  IF @PROM_DOK_STAT <> 0
  BEGIN
    RAISERROR('Nije dozvoljeno knjizene prometnog dokumenta  koji nije u fazi formiranja!',11,2)
    RETURN
  END

  IF(SELECT COUNT(STAV_PROM_DOK_ID) FROM STAVKA_PROMETNOG_DOKUMENTA WHERE PROM_DOK_ID= @PROM_DOK_ID) = 0
  BEGIN
    RAISERROR('Nije dozvoljeno knjizene prometnog dokumenta koji nema najmanje jednu stavku!',11,2)
    RETURN
  END

  IF(SELECT count(*) FROM PROMETNI_DOKUMENT WHERE PROM_DOK_DAT_FORM < GETDATE() AND PROM_DOK_ID = @PROM_DOK_ID) = 0
  BEGIN
    RAISERROR('Nije dozvoljeno knjizene prometnog dokumenta ciji datum je veci od danasnjeg!',11,2)
    RETURN
  END
---------------------------------------------------------
--  Knjizenje dokumenta:
--  knjiženje dokumenta je uspešno ako su uspešno obrađene sve stavke (u suprotnom se knjiženje poništava)
---------------------------------------------------------
 DECLARE
  @STAV_PROM_DOK_ID INT,
  @ROBA_ID INT,
  @STAV_PROM_DOK_KOL numeric(14,4),
  @STAV_PROM_DOK_CEN numeric(14,4),
  @STAV_PROM_DOK_VRED numeric(14,4)
  
  SET @BROJ_TRANSAKCIJA = @@TRANCOUNT

  IF @BROJ_TRANSAKCIJA = 0
  BEGIN TRANSACTION
	 DECLARE cursPROKNJIZI CURSOR FOR SELECT STAV_PROM_DOK_ID,ROBA_ID,STAV_PROM_DOK_KOL,STAV_PROM_DOK_CEN,STAV_PROM_DOK_VRED
	  FROM STAVKA_PROMETNOG_DOKUMENTA
	  WHERE PROM_DOK_ID=@PROM_DOK_ID
	 OPEN cursPROKNJIZI
	 FETCH NEXT FROM cursPROKNJIZI INTO @STAV_PROM_DOK_ID,@ROBA_ID,@STAV_PROM_DOK_KOL,@STAV_PROM_DOK_CEN,@STAV_PROM_DOK_VRED
	  WHILE @@FETCH_STATUS = 0
	   BEGIN
		


		 IF @STAV_PROM_DOK_KOL < 0
		  BEGIN
			RAISERROR('Kolicina u stavci ulaznog dokumenta je manja od 0!',11,2)
			IF @BROJ_TRANSAKCIJA = 0
				ROLLBACK TRANSACTION
			CLOSE cursPROKNJIZI
			DEALLOCATE cursPROKNJIZI
			RETURN
		  END			
		 IF @PROM_DOK_VRST = 0
		 BEGIN
			 DECLARE			 
			 @UKUPNA_KOLICINA numeric(14,4),
			 @UKUPNA_VREDNOST numeric(14,4),
			 @PROMET_ULAZA_KOLICINSKI numeric(14,4),
			 @PROMET_ULAZA_VREDNOSNO numeric(14,4),
			 @ROBNA_KARTICA_ID INT
			 
			 SELECT @UKUPNA_KOLICINA = ROB_KART_UKKOL, 
					@UKUPNA_VREDNOST = ROB_KART_UKVRED,
					@PROMET_ULAZA_KOLICINSKI = ROB_KART_PRUL_KOL,
					@PROMET_ULAZA_VREDNOSNO = ROB_KART_PRUL_VRED,
					@ROBNA_KARTICA_ID = ROB_KART_ID
			 FROM ROBNA_KARTICA 
			 WHERE ROBA_ID = @ROBA_ID 



			 SET @UKUPNA_KOLICINA = @UKUPNA_KOLICINA + @STAV_PROM_DOK_KOL
			 SET @UKUPNA_VREDNOST = @UKUPNA_VREDNOST + @STAV_PROM_DOK_VRED
			 SET @PROMET_ULAZA_KOLICINSKI = @PROMET_ULAZA_KOLICINSKI + @STAV_PROM_DOK_KOL
			 SET @PROMET_ULAZA_VREDNOSNO = @PROMET_ULAZA_VREDNOSNO + @STAV_PROM_DOK_KOL

			 UPDATE ROBNA_KARTICA
			 SET 
				ROB_KART_UKKOL = @UKUPNA_KOLICINA,
				ROB_KART_UKVRED = @UKUPNA_VREDNOST,
				ROB_KART_PRUL_KOL = @PROMET_ULAZA_KOLICINSKI,
				ROB_KART_PRUL_VRED = @PROMET_ULAZA_VREDNOSNO

			 WHERE 
				ROBA_ID = @ROBA_ID
			 IF @@ERROR <> 0

			 BEGIN
				  SET @PORUKA = 'Greska pri izmeni podataka za magacinsku karticu za materijal ' + CAST(@ROBA_ID as varchar(5))
                                
				  RAISERROR(@PORUKA, 11, 2)
				  IF @BROJ_TRANSAKCIJA = 0
					  ROLLBACK TRANSACTION
				  CLOSE cursPROKNJIZI
				  DEALLOCATE cursPROKNJIZI
				  RETURN
			 END

			 -- INSERT ANALITIKA_ROBNE_KARTICE
			 SELECT @BROJ_ANALITIKA = COUNT(*) 
			 FROM ANALITIKA_MAGACINSKE_KARTICE
			 WHERE ROB_KART_ID = @ROBNA_KARTICA_ID

			 SET @BROJ_ANALITIKA = @BROJ_ANALITIKA + 1
			 INSERT INTO ANALITIKA_MAGACINSKE_KARTICE 
			 VALUES(@BROJ_ANALITIKA, @ROBNA_KARTICA_ID, @BROJ_ANALITIKA, 0, @STAV_PROM_DOK_KOL,@STAV_PROM_DOK_CEN,@STAV_PROM_DOK_VRED,0)
			 IF @@ERROR <> 0

			 BEGIN
				  SET @PORUKA = 'Greska pri dodavanju odgovarajuce analitike ' + CAST(@BROJ_ANALITIKA as varchar(5))
                                
				  RAISERROR(@PORUKA, 11, 2)
				  IF @BROJ_TRANSAKCIJA = 0
					  ROLLBACK TRANSACTION
				  CLOSE cursPROKNJIZI
				  DEALLOCATE cursPROKNJIZI
				  RETURN
			 END			
			 
			 -- UPDATE STATUSA i DATUMA KNJIZENJA

			 UPDATE PROMETNI_DOKUMENT
			 SET 
				PROM_DOK_DAT_KNJIZ = GETDATE(),
				PROM_DOK_VRST = 1

			 WHERE 
				PROM_DOK_ID = @PROM_DOK_ID
			 IF @@ERROR <> 0

			 BEGIN
				  SET @PORUKA = 'Greska pri izmeni prometnog dokumenta' + CAST(@PROM_DOK_ID as varchar(5))
                                
				  RAISERROR(@PORUKA, 11, 2)
				  IF @BROJ_TRANSAKCIJA = 0
					  ROLLBACK TRANSACTION
				  CLOSE cursPROKNJIZI
				  DEALLOCATE cursPROKNJIZI
				  RETURN
			 END
		 END
		 FETCH NEXT FROM cursPROKNJIZI INTO @STAV_PROM_DOK_ID,@ROBA_ID,@STAV_PROM_DOK_KOL,@STAV_PROM_DOK_CEN,@STAV_PROM_DOK_VRED
	   END
	   
	   IF @BROJ_TRANSAKCIJA = 0 
			COMMIT TRANSACTION
	   CLOSE cursPROKNJIZI
	   DEALLOCATE cursPROKNJIZI
	   SET @OK = 1
GO