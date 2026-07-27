  CREATE TABLE events(

  id UUID PRIMARY KEY,
  name varchar(150) NOT NULL,
  description TEXT NOT NULL,
  organizer_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  event_status varchar(20) NOT NULL CHECK (event_status in ('DRAFT','PUBLISHED','CANCELLED')) DEFAULT 'DRAFT',

  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

  );