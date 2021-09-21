CREATE TABLE `programs` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	name varchar(60) NOT NULL COMMENT 'Program name',
	description varchar(255) COMMENT 'Description about the program',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_name USING BTREE ON `programs` (name);

CREATE TABLE `reasoncancellation` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	reason varchar(60) NOT NULL COMMENT 'Reason cancellation name',
	description varchar(255) COMMENT 'Description about the reason cancellation',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE UNIQUE INDEX unique_reason USING BTREE ON `reasoncancellation` (reason);

insert into programs (name, description, tenant_id) values
    ('Advanced', 'Advanced program', 1),
    ('Turbo', 'Turbo program', 1)
;

insert into reasoncancellation (reason, description, tenant_id) values
    ('Arrependimento', 'Cliente se arrependeu', 1),
    ('Motivos de saúde', 'Motivo relacionado a saúde', 1),
    ('Mudança de endereço', 'Deve ser utilizado quando o cliente mudou-se de bairro/cidade', 1),
    ('Não se adaptou', 'Cliente não se adaptou ao método', 1),
    ('Congelou o programa', 'Programa congelado', 1)
;