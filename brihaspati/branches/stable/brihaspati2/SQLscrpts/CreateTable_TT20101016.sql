# -----------------------------------------------------------------------
# events
# -----------------------------------------------------------------------
drop table if exists events;

CREATE TABLE events
(
		            event LONGBLOB
);

# -----------------------------------------------------------------------
# table_id
# -----------------------------------------------------------------------
drop table if exists table_id;

CREATE TABLE table_id
(
		            id INTEGER NOT NULL,
		            path VARCHAR (255),
		            user VARCHAR (255),
		            department VARCHAR (255),
		            date DATETIME,
    PRIMARY KEY(id)
);

# -----------------------------------------------------------------------
# fac_info
# -----------------------------------------------------------------------
drop table if exists fac_info;

CREATE TABLE fac_info
(
		            fac_id INTEGER,
		            name VARCHAR (255) NOT NULL,
		            department VARCHAR (255) NOT NULL,
		            institute VARCHAR (255) NOT NULL,
		            id VARCHAR (10) NOT NULL
);

# -----------------------------------------------------------------------
# faculty_course
# -----------------------------------------------------------------------
drop table if exists faculty_course;

CREATE TABLE faculty_course
(
		            course_code VARCHAR (10) NOT NULL,
		            faculty_id VARCHAR (10) NOT NULL
);

# -----------------------------------------------------------------------
# course_info
# -----------------------------------------------------------------------
drop table if exists course_info;

CREATE TABLE course_info
(
		            course_code VARCHAR (10) NOT NULL,
		            course_type VARCHAR (3) NOT NULL,
		            events_per_week INTEGER,
		            duration INTEGER,
		            computer INTEGER,
		            projector INTEGER,
		            venue_code VARCHAR (10),
		            scheduled INTEGER
);

# -----------------------------------------------------------------------
# batch
# -----------------------------------------------------------------------
drop table if exists batch;

CREATE TABLE batch
(
		            batch_code VARCHAR (10) NOT NULL,
		            strength INTEGER,
		            batch_name VARCHAR (255)
);

# -----------------------------------------------------------------------
# batch_course
# -----------------------------------------------------------------------
drop table if exists batch_course;

CREATE TABLE batch_course
(
		            course_code VARCHAR (10) NOT NULL,
		            batch_code VARCHAR (10) NOT NULL
);

# -----------------------------------------------------------------------
# venue
# -----------------------------------------------------------------------
drop table if exists venue;

CREATE TABLE venue
(
		            code VARCHAR (10) NOT NULL,
		            capacity INTEGER NOT NULL,
		            ncomputers INTEGER,
		            projector INTEGER,
		            type INTEGER
);
  
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (134, 'events', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (135, 'table_id', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (136, 'fac_info', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (137, 'faculty_course', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (138, 'course_info', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (139, 'batch', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (140, 'batch_course', 1000, 1);
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (141, 'venue', 1000, 1);
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
