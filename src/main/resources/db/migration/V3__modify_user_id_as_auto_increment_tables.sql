ALTER TABLE users DROP COLUMN id;
ALTER TABLE users ADD  COLUMN id BIGINT AUTO_INCREMENT PRIMARY KEY;