CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE statement
    SET amount = amount - OLD.amount
    WHERE statementid = OLD.statementid;
    RETURN NULL;
END
$$ language 'plpgsql';