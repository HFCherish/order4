ALTER TABLE payments MODIFY type VARCHAR(255) DEFAULT "CASH";
ALTER TABLE payments ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;