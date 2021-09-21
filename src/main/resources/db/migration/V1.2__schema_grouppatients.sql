CREATE TABLE `grouppatients` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	name VARCHAR(60) NOT NULL COMMENT 'Group name',
	description VARCHAR(255) COMMENT 'Description about the group',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_name USING BTREE ON `grouppatients` (name);