CREATE UNIQUE INDEX unique_name USING BTREE ON `programs` (name);
CREATE UNIQUE INDEX unique_reason USING BTREE ON `reasoncancellation` (reason);
CREATE UNIQUE INDEX unique_name USING BTREE ON `patient` (name);
CREATE UNIQUE INDEX unique_name USING BTREE ON `grouppatients` (name);