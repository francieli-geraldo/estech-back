DROP TABLE `user_role`;
DROP TABLE `role`;
DROP TABLE `user`;

DELETE FROM flyway_schema_history WHERE script = 'V1.6__schema_user.sql';