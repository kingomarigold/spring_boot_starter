DROP TABLE if exists test_table;

CREATE TABLE test_table 
(
	id UUID NOT NULL,
	name VARCHAR(100) NOT NULL,
	created_by UUID NOT NULL,
	last_modified_by UUID NOT NULL,
	created_date timestamp NOT NULL,
	last_modified_date timestamp NOT NULL,
	PRIMARY KEY(id)
);