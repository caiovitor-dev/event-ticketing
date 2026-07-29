CREATE TABLE ticket_batches(

id UUID PRIMARY KEY,
ticket_sector_id UUID NOT NULL REFERENCES ticket_sectors(id) ON DELETE RESTRICT,
batch_number INT NOT NULL,
price NUMERIC(10,2) NOT NULL,
batch_status varchar(20) NOT NULL CHECK (batch_status in ('ACTIVE','SOLD_OUT','FINISHED','CANCELLED')),
total_available INT NOT NULL CHECK (total_available >= 0),
total_quantity  INT NOT NULL CHECK (total_quantity >=0) ,
sales_start_at TIMESTAMP NOT NULL,
sales_end_at  TIMESTAMP NOT NULL ,
version BIGINT NOT NULL DEFAULT 0,

CONSTRAINT uq_ticket_batch_sector_number UNIQUE (ticket_sector_id, batch_number),
CONSTRAINT chk_ticket_batch_available_quantity CHECK (total_available <= total_quantity),
CONSTRAINT chk_ticket_batch_sales_period CHECK (sales_start_at < sales_end_at)

created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

)