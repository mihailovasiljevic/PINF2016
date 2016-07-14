
CREATE TRIGGER CheckBusinessYear ON dbo.PROMETNI_DOKUMENT
AFTER INSERT  
AS  
IF NOT EXISTS (SELECT *  
           FROM INSERTED AS pd  
           JOIN POSLOVNA_GODINA AS pg   
           ON pd.POSL_GOD_ID = pg.POSL_GOD_ID
		   WHERE pd.POSL_SIS_ID = pg.POSL_SIS_ID
          )  
BEGIN  
RAISERROR ('Poslovna godina i poslovni sistem se ne poklapaju.', 16, 1);  
ROLLBACK TRANSACTION;  
RETURN   
END;  
GO  
