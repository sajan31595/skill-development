CREATE TABLE IF NOT EXISTS ROLES(
	ID INT PRIMARY KEY NOT NULL,
	NAME TEXT NOT NULL,
	DESCRITPTION VARCHAR(50),
	CREATED_ON DATE,
	MODIFIED_ON DATE,
	STATUS VARCHAR(2)
);