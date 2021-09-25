CREATE TABLE `user` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	name varchar(150) NOT NULL COMMENT 'User name',
	username varchar(150) NOT NULL COMMENT 'Username',
	password varchar(250) NOT NULL COMMENT 'Password',
	avatar blob COMMENT 'User avatar',
    change_password tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates need to change password',
    account_locked tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates whether the account is locked or not',
    password_reset_token varchar(250) COMMENT 'Password to reset token',
    password_reset_expires DATETIME COMMENT 'Expiry date password to reset token',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_username USING BTREE ON `user` (username);
CREATE UNIQUE INDEX unique_password_reset_token USING BTREE ON `user` (password_reset_token);

CREATE TABLE `role` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	name varchar(150) NOT NULL COMMENT 'Role name',
	description varchar(255) COMMENT 'Description about the role',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_name USING BTREE ON `role` (name);

CREATE TABLE `user_role` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	user_id BIGINT NOT NULL COMMENT 'User id',
	role_id BIGINT NOT NULL COMMENT 'Role id',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role`(`id`);
CREATE UNIQUE INDEX unique_user_role USING BTREE ON `user_role` (user_id, role_id);

insert into role (name, description, tenant_id) values
    ('ADMIN', 'Administrator', 1),
    ('USER', 'User', 1),
    ('PATIENT', 'Patient', 1)
;