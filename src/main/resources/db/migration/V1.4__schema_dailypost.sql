CREATE TABLE `dailyposting` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	agreement_id BIGINT NOT NULL COMMENT 'Agreement id',
	release_date DATE NOT NULL COMMENT 'Release date',
    balance tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the weight was informed',
	previous_weight DECIMAL(6,3) COMMENT 'Previous weight',
	current_weight DECIMAL(6,3) COMMENT 'Current weight',
	evolution DECIMAL(6,3) COMMENT 'Evolution',
	accumulated_evolution DECIMAL(6,3) COMMENT 'Accumulated evolution',
    breakfast tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the breakfast was informed',
    morning_snack tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the morning snack was informed',
    lunch tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the lunch was informed',
    dinner tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the dinner was informed',
    hiit tinyint NOT NULL DEFAULT 0 COMMENT 'Indicates if the hiit was informed',
	notes varchar(255) COMMENT 'Notes',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE `dailyposting` ADD CONSTRAINT `fk_dailyposting_agreement_id` FOREIGN KEY (`agreement_id`) REFERENCES `agreement`(`id`);
