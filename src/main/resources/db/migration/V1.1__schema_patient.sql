CREATE TABLE `patient` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	name varchar(150) NOT NULL COMMENT 'Client name',
	sex varchar(40) COMMENT 'Sex',
	birth_date date COMMENT 'Birth date',
	email varchar(100) COMMENT 'E-mail',
	phone varchar(20) COMMENT 'Phone',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_name USING BTREE ON `patient` (name);