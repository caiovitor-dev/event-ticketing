CREATE TABLE ticket_sectors (

id UUID PRIMARY KEY,
name varchar(100) NOT NULL,
event_session_id UUID NOT NULL REFERENCES event_sessions(id) ON DELETE RESTRICT,
capacity INT NOT NULL CHECK (capacity > 0),
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT uq_ticket_sectors_name_session UNIQUE (name,event_session_id)

);