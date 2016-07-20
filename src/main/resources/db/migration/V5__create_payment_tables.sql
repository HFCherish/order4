CREATE TABLE payments (
  order_id BIGINT NOT NULL,
  amount DOUBLE NOT NULL,
  type INTEGER DEFAULT 0,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);