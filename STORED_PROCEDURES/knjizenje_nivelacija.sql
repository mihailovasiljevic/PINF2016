IF EXISTS (SELECT name
    FROM   sysobjects
    WHERE  name = '[nivelacija]' AND type = 'P')
    DROP PROCEDURE nivelacija
 GO
 ---------------------------------------------------------
 --   Procedura za storniranje popisnog dokumenta
 ---------------------------------------------------------
CREATE PROCEDURE dbo.nivelacija
   @ROBNA_KARTICA_ID INT
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
  @OK bit

DECLARE			 
	@UKUPNA_KOLICINA numeric(14,4),
	@UKUPNA_VREDNOST numeric(14,4),
	@NIVELACIJA numeric(14,4)	,
	@CENA numeric(14,4),
	@ROBA_ID INT
  SET @BROJ_TRANSAKCIJA = @@TRANCOUNT

  IF @BROJ_TRANSAKCIJA = 0
  BEGIN TRANSACTION
	 DECLARE cursPROKNJIZI CURSOR 
	 FOR SELECT ROB_KART_UKKOL, ROB_KART_UKVRED, ROB_KART_CEBA, ROBA_ID, ROB_KART_ID
	  FROM ROBNA_KARTICA
	  WHERE ROB_KART_ID = @ROBNA_KARTICA_ID
	 OPEN cursPROKNJIZI
	 FETCH NEXT FROM cursPROKNJIZI INTO @UKUPNA_KOLICINA,@UKUPNA_VREDNOST,@CENA,@ROBA_ID,@ROBNA_KARTICA_ID
	  WHILE @@FETCH_STATUS = 0
	   BEGIN
		SET @NIVELACIJA = (@UKUPNA_KOLICINA*@CENA)-@UKUPNA_VREDNOST
		SET @UKUPNA_VREDNOST = (@UKUPNA_KOLICINA*@CENA)
			UPDATE ROBNA_KARTICA
			SET ROB_KART_UKVRED = @UKUPNA_VREDNOST
			WHERE 
			ROB_KART_ID = @ROBNA_KARTICA_ID
			IF @@ERROR <> 0

			BEGIN
				SET @PORUKA = 'Greska pri izmeni podataka za magacinsku karticu za materijal ' + CAST(@ROBNA_KARTICA_ID as varchar(5))
                                
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

			 SET @SMER = 'U'
			 SET @VRSTA = 'N'

			 -- INSERT ANALITIKA_ROBNE_KARTICE
			 SELECT @BROJ_ANALITIKA = COUNT(*) 
			 FROM ANALITIKA_MAGACINSKE_KARTICE
			 WHERE ROB_KART_ID = @ROBNA_KARTICA_ID

			 SET @BROJ_ANALITIKA = @BROJ_ANALITIKA + 1

			 INSERT INTO ANALITIKA_MAGACINSKE_KARTICE 
			 VALUES(@ROBNA_KARTICA_ID, @BROJ_ANALITIKA, @SMER, @NIVELACIJA/@CENA,@CENA,@NIVELACIJA,@VRSTA)
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
			FETCH NEXT FROM cursPROKNJIZI INTO @UKUPNA_KOLICINA,@UKUPNA_VREDNOST,@CENA,@ROBA_ID,@ROBNA_KARTICA_ID
	   END
	   
	   IF @BROJ_TRANSAKCIJA = 0 
			COMMIT TRANSACTION
	   CLOSE cursPROKNJIZI
	   DEALLOCATE cursPROKNJIZI
	   SET @OK = 1
GO