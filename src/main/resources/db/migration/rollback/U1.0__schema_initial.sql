DROP TABLE IF EXISTS `programs`;
DROP TABLE IF EXISTS `reasoncancellation`;

DELETE FROM flyway_schema_history WHERE script = 'V1.0__schema_initial.sql';