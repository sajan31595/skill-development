CREATE TABLE IF NOT EXISTS COURSE_NOTIFICATIONS(
	ID INT PRIMARY KEY NOT NULL,
	COURSE_ID INT,
	NOTIFICATION_TYPE VARCHAR(50),
	NOTIFICATION_DESCRIPTION VARCHAR(5000),
	CREATED_ON DATE,
	STATUS VARCHAR(2),
	CONSTRAINT FK_COURSE_EVENTS_COURSE FOREIGN KEY(COURSE_ID) REFERENCES COURSES(ID)
);