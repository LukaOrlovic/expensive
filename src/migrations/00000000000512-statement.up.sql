CREATE TRIGGER up_statement AFTER DELETE
ON receipt FOR EACH ROW EXECUTE PROCEDURE
update_updated_at();