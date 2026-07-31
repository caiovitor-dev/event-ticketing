CREATE TABLE payments (

id UUID PRIMARY KEY,
order_id UUID NOT NULL REFERENCES orders(id) ON DELETE RESTRICT UNIQUE,
payment_value NUMERIC(10,2) NOT NULL CHECK (payment_value > 0),
payment_status varchar (20) NOT NULL CHECK (payment_status in ('APPROVED','FAILED','CANCELLED','PENDING','REFUNDED')),
payment_method varchar (20) NOT  NULL CHECK(payment_method in ('PIX','CARD','BANK_SLIP')),
gateway_transaction_id varchar (100) UNIQUE,

created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

)