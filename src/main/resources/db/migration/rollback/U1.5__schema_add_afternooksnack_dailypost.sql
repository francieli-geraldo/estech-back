ALTER TABLE `dailyposting` DROP COLUMN afternoon_snack;

DELETE FROM flyway_schema_history WHERE script = 'V1.5__schema_add_afternooksnack_dailypost.sql';