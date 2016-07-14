CREATE TRIGGER checkIfProknjizen ON dbo.STAVKA_PROMETNOG_DOKUMENTA
AFTER INSERT  
AS  
IF NOT EXISTS (SELECT *  
           FROM INSERTED AS spd  
           JOIN PROMETNI_DOKUMENT AS pd   
           ON spd.PROM_DOK_ID = pd.PROM_DOK_ID
		   WHERE pd.PROM_DOK_STAT='F'
          )  
BEGIN  
RAISERROR ('Pokusavate da dodate stavku za proknjizen ili stornrian dokument!.', 16, 1);  
ROLLBACK TRANSACTION;  
RETURN   
END;    

GO  
