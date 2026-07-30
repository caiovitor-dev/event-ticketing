CREATE TABLE order_items (

id UUID PRIMARY KEY,
order_id UUID NOT NULL REFERENCES orders(id) ON DELETE RESTRICT,
ticket_batch_id UUID NOT NULL REFERENCES ticket_batches(id) ON DELETE RESTRICT,
quantity INT NOT NULL  CHECK (quantity > 0),
subtotal NUMERIC(10,2) NOT NULL CHECK (subtotal > 0),
unit_price NUMERIC(10,2) NOT NULL CHECK (unit_price >0),

created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

)