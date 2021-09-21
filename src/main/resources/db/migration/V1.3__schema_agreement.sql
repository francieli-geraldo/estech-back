CREATE TABLE `agreement` (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Table id',
	patient_id BIGINT NOT NULL COMMENT 'Patient id',
	program_id BIGINT NOT NULL COMMENT 'Program id',
	group_id BIGINT NOT NULL COMMENT 'Group id',
	status VARCHAR(60) NOT NULL COMMENT 'Status',
	starting_weight DECIMAL(6,3) NOT NULL COMMENT 'Starting weight',
	goal DECIMAL(6,3) COMMENT 'Goal',
	hiring_date DATE NOT NULL COMMENT 'Hiring date',
	start_date DATE NOT NULL COMMENT 'Start date',
	date_conclusion DATE COMMENT 'Date of the conclusion',
	cancellation_date DATE COMMENT 'Cancellation date',
    reason_cancellation varchar(255) COMMENT 'Reason for Cancellation',
	notes varchar(255) COMMENT 'Notes',
	tenant_id BIGINT NOT NULL COMMENT 'Tenant id',
	created_at DATETIME NOT NULL DEFAULT NOW() COMMENT 'Creation date',
	updated_at DATETIME COMMENT 'Updated date',
	active tinyint NOT NULL DEFAULT 1 COMMENT 'Indicates whether the record is active or not',
	CONSTRAINT pk_id PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE `agreement` ADD CONSTRAINT `fk_agreement_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient`(`id`);
ALTER TABLE `agreement` ADD CONSTRAINT `fk_agreement_program_id` FOREIGN KEY (`program_id`) REFERENCES `programs`(`id`);
ALTER TABLE `agreement` ADD CONSTRAINT `fk_agreement_group_id` FOREIGN KEY (`group_id`) REFERENCES `grouppatients`(`id`);