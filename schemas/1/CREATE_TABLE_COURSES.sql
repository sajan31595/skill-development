CREATE TABLE IF NOT EXISTS COURSES(
	ID INT PRIMARY KEY NOT NULL,
	NAME TEXT NOT NULL,
	DESCRIPTION VARCHAR(50),
	TYPE VARCHAR(50),
	AUTHOR_ID INT,
	CREATED_BY INT,
	GROUP_LINK INT,
	START_DATE DATE,
	CREATED_ON DATE,
	MODIFIED_ON DATE,
	STATUS VARCHAR(2)
);