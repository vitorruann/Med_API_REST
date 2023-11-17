ALTER TABLE patients ADD active tinyint;
UPDATE patients set active = 1;