IF EXISTS (SELECT name
    FROM   sysobjects
    WHERE  name = '[proknjizi_prometni_dokument]' AND type = 'P')
    DROP PROCEDURE [proknjizi_prometni_dokument]
 GO
---------------------------------------------------------
--   Procedura za knjizenje popisnog dokumenta
---------------------------------------------------------
CREATE PROCEDURE dbo.[proknjizi_prometni_dokument]
   @PROM_DOK_ID INT
AS

---------------------------------------------------------
--  Provera ispunjenosti uslova:
--	1. status dokumenta mora biti â€œu fazi formiranjaâ€�
--	2. dokument mora imati bar jednu stavku
--	3. poslovna godina dokumenta ne sme biti zakljuÄ�ena
--	4. datum prometnog dokumenta ne sme biti veÄ‡i od danaÅ¡njeg
---------------------------------------------------------
 DECLARE
  @BROJ_ANALITIKA NUMERIC(5),
  @BROJ_TRANSAKCIJA INT,
  @PORUKA VARCHAR(100),
  @OK bit,
   @PROM_DOK_VRST char(1),
   @PROM_DOK_STAT char(1),
  @POSLOVNA_GODINA_ID INT,
  @MAG_ID INT,
  @MAG_MAG_ID INT

  SET @OK = 0
  SELECT @PROM_DOK_STAT = PROM_DOK_STAT,
		 @PROM_DOK_VRST = PROM_DOK_VRST,
		 @POSLOVNA_GODINA_ID = POSL_GOD_ID,
         @MAG_ID = MAG_ID,
		 @MAG_MAG_ID = MAG_MAG_ID
  FROM dbo.PROMETNI_DOKUMENT WHERE PROM_DOK_ID = @PROM_DOK_ID
  IF @PROM_DOK_STAT <> 'F'
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
--  knjizenje dokumenta je uspesno ako su uspesno obradjene sve stavke (u suprotnom se knjizenje ponistava)
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
		DECLARE			 
			@UKUPNA_KOLICINA numeric(14,4),
			@UKUPNA_VREDNOST numeric(14,4),
			@PROMET_ULAZA_KOLICINSKI numeric(14,4),
			@PROMET_ULAZA_VREDNOSNO numeric(14,4),
			@PROMET_IZLAZA_KOLICINSKI numeric(14,4),
			@PROMET_IZLAZA_VREDNOSNO numeric(14,4),
			@POCETNO_STANJE_KOLICINSKI numeric(14,4),
			@POCETNO_STANJE_VREDNOSNO numeric(14,4),
			@ROBNA_KARTICA_ID INT,
			@CENA numeric(14,4)		

		-- AKO NE POSTOJI ROBNA KARTICA NAPRAVI JE
			 
        IF(SELECT COUNT(*) FROM ROBNA_KARTICA WHERE ROBA_ID = @ROBA_ID AND POSL_GOD_ID = @POSLOVNA_GODINA_ID) = 0
        BEGIN
            INSERT INTO ROBNA_KARTICA VALUES(@MAG_ID,@ROBA_ID, @POSLOVNA_GODINA_ID, 0,0,0,0,0,0,0,0,0)
			
			IF @@ERROR <> 0

			BEGIN
				SET @PORUKA = 'Greska pri pokusaju dodavanja nove robne kartice! '
                                
				RAISERROR(@PORUKA, 11, 2)
				IF @BROJ_TRANSAKCIJA = 0
					ROLLBACK TRANSACTION
				CLOSE cursPROKNJIZI
				DEALLOCATE cursPROKNJIZI
				RETURN
			END
        END

		SELECT @UKUPNA_KOLICINA = ROB_KART_UKKOL, 
			@UKUPNA_VREDNOST = ROB_KART_UKVRED,
			@PROMET_ULAZA_KOLICINSKI = ROB_KART_PRUL_KOL,
			@PROMET_ULAZA_VREDNOSNO = ROB_KART_PRUL_VRED,
			@ROBNA_KARTICA_ID = ROB_KART_ID,
			@POCETNO_STANJE_KOLICINSKI = ROB_KART_POC_KOL,
			@POCETNO_STANJE_VREDNOSNO = ROB_KART_POC_VRED,
			@PROMET_IZLAZA_KOLICINSKI = ROB_KART_PRIZ_KOL,
			@PROMET_IZLAZA_VREDNOSNO = ROB_KART_PRIZ_VRED,
			@CENA = ROB_KART_CEBA
		FROM ROBNA_KARTICA 
		WHERE ROBA_ID = @ROBA_ID 

		 IF @PROM_DOK_VRST = 'P'
		 BEGIN
			 SET @PROMET_ULAZA_KOLICINSKI = @PROMET_ULAZA_KOLICINSKI + @STAV_PROM_DOK_KOL
			 SET @PROMET_ULAZA_VREDNOSNO = @PROMET_ULAZA_VREDNOSNO + @STAV_PROM_DOK_VRED
			 SET @UKUPNA_KOLICINA = @POCETNO_STANJE_KOLICINSKI + @PROMET_ULAZA_KOLICINSKI-@PROMET_IZLAZA_KOLICINSKI
			 SET @UKUPNA_VREDNOST = @POCETNO_STANJE_VREDNOSNO + @PROMET_ULAZA_VREDNOSNO-@PROMET_IZLAZA_KOLICINSKI
			 SET @CENA = (@UKUPNA_VREDNOST+@STAV_PROM_DOK_KOL*@STAV_PROM_DOK_CEN)/(@UKUPNA_KOLICINA+@STAV_PROM_DOK_KOL)
			 --(ukupna vrednost + količina ulaza * nabavna cena) / (ukupna količina + količina ulaza)
		END
		IF @PROM_DOK_VRST = 'O'
		BEGIN
			 SET @PROMET_IZLAZA_KOLICINSKI = @PROMET_IZLAZA_KOLICINSKI + @STAV_PROM_DOK_KOL
			 SET @PROMET_IZLAZA_VREDNOSNO = @PROMET_IZLAZA_VREDNOSNO + @STAV_PROM_DOK_VRED
			 SET @UKUPNA_KOLICINA = @POCETNO_STANJE_KOLICINSKI + @PROMET_ULAZA_KOLICINSKI-@PROMET_IZLAZA_KOLICINSKI
			 SET @UKUPNA_VREDNOST = @POCETNO_STANJE_VREDNOSNO + @PROMET_ULAZA_VREDNOSNO-@PROMET_IZLAZA_KOLICINSKI
			 IF(@UKUPNA_KOLICINA < 0)
				BEGIN
				  SET @PORUKA = 'Ukupna kolicina ne sme pasti ispod nule '
                                
				  RAISERROR(@PORUKA, 11, 2)
				  IF @BROJ_TRANSAKCIJA = 0
					  ROLLBACK TRANSACTION
				  CLOSE cursPROKNJIZI
				  DEALLOCATE cursPROKNJIZI
				  RETURN					
				END

			IF(@UKUPNA_VREDNOST < 0)
				BEGIN
				  SET @PORUKA = 'Ukupna vrednost ne sme pasti ispod nule '
                                
				  RAISERROR(@PORUKA, 11, 2)
				  IF @BROJ_TRANSAKCIJA = 0
					  ROLLBACK TRANSACTION
				  CLOSE cursPROKNJIZI
				  DEALLOCATE cursPROKNJIZI
				  RETURN					
				END
				--(ukupna vrednost + količina ulaza * nabavna cena) / (ukupna količina + količina ulaza)
				SET @CENA = (@UKUPNA_VREDNOST+@STAV_PROM_DOK_KOL*@STAV_PROM_DOK_CEN)/(@UKUPNA_KOLICINA+@STAV_PROM_DOK_KOL)
		END
				
					 UPDATE ROBNA_KARTICA
					 SET 
						ROB_KART_UKKOL = @UKUPNA_KOLICINA,
						ROB_KART_UKVRED = @UKUPNA_VREDNOST,
						ROB_KART_PRUL_KOL = @PROMET_ULAZA_KOLICINSKI,
						ROB_KART_PRUL_VRED = @PROMET_ULAZA_VREDNOSNO,
						ROB_KART_PRIZ_KOL = @PROMET_IZLAZA_KOLICINSKI,
						ROB_KART_PRIZ_VRED = @PROMET_IZLAZA_VREDNOSNO,
						ROB_KART_CEBA = @CENA
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

			 DECLARE
				@SMER char(1),
				@VRSTA char(1)
			 IF @PROM_DOK_VRST = 'P'
			 BEGIN
				set @SMER = 'U'
				set @VRSTA = 'P'
			 END
			 ELSE
			 BEGIN
				set @SMER = 'I'
				set @VRSTA = 'O'
			 END

			 INSERT INTO ANALITIKA_MAGACINSKE_KARTICE 
			 VALUES(@ROBNA_KARTICA_ID, @BROJ_ANALITIKA, @SMER, @STAV_PROM_DOK_KOL,@STAV_PROM_DOK_CEN,@STAV_PROM_DOK_VRED,@VRSTA)
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
				PROM_DOK_STAT = 'P'

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
		 FETCH NEXT FROM cursPROKNJIZI INTO @STAV_PROM_DOK_ID,@ROBA_ID,@STAV_PROM_DOK_KOL,@STAV_PROM_DOK_CEN,@STAV_PROM_DOK_VRED
	   END
	   
	   IF @BROJ_TRANSAKCIJA = 0 
			COMMIT TRANSACTION
	   CLOSE cursPROKNJIZI
	   DEALLOCATE cursPROKNJIZI
	   SET @OK = 1
GO