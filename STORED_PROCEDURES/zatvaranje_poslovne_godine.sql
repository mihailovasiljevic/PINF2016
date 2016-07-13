IF EXISTS (SELECT name
   FROM   sysobjects
   WHERE  name = 'zatvaranje_poslovne_godine' AND type = 'P')
   DROP PROCEDURE [zatvaranje_poslovne_godine]
GO
---------------------------------------------------------
--   Procedura za zatvaranje poslovne godine
---------------------------------------------------------
CREATE PROCEDURE dbo.[zatvaranje_poslovne_godine]
   @POSL_GOD_ID INT,
   @OK INT OUTPUT
AS
	declare @PROM_DOK_STAT char(1)
	SET @OK = 0

	DECLARE cursDOKUMENT CURSOR FOR SELECT PROM_DOK_STAT
	FROM PROMETNI_DOKUMENT WHERE @POSL_GOD_ID = POSL_GOD_ID
	
	OPEN cursDOKUMENT
	 FETCH NEXT FROM cursDOKUMENT INTO @PROM_DOK_STAT 
	WHILE @@FETCH_STATUS = 0
	 BEGIN
	   IF(@PROM_DOK_STAT = 'F')
		BEGIN
		  SET @OK = 1
		END
	   FETCH NEXT FROM cursDOKUMENT INTO @PROM_DOK_STAT
	 END
	 IF(@OK = 0)
	   BEGIN
	     UPDATE POSLOVNA_GODINA set POSL_GOD_ZAKLJ = 1 where POSL_GOD_ID = @POSL_GOD_ID;
	   END
	 CLOSE cursDOKUMENT
	 DEALLOCATE cursDOKUMENT

GO