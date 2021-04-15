CREATE TRIGGER after_enchere
ON ENCHERES 
AFTER INSERT
AS
BEGIN 
UPDATE ARTICLES_VENDUS  
SET prix_vente = inserted.montant_enchere 
FROM inserted
WHERE ARTICLES_VENDUS.no_article = inserted.no_article
END