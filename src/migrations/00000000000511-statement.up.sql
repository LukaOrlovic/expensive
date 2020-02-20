CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE statement
    SET amount = amount - OLD.amount
    WHERE statementid = OLD.statementid;
    DELETE FROM statement
    WHERE amount = 0;
    RETURN NULL;
END
$$ language 'plpgsql';