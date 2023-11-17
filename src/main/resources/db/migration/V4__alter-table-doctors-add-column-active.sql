ALTER TABLE doctors ADD active tinyint;
UPDATE doctors set active = 1;