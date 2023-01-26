ALTER TABLE ROLES ALTER COLUMN NAME TYPE varchar(1000);
ALTER TABLE ROLES ALTER COLUMN description TYPE varchar(1000);

CREATE SEQUENCE users_seq
INCREMENT 1
START 1;

CREATE SEQUENCE roles_seq
INCREMENT 1
START 1;

CREATE SEQUENCE roles_seq
INCREMENT 1
START 1;

CREATE SEQUENCE courses_seq
INCREMENT 1
START 1;

INSERT INTO USERS(ID, NAME, PASSWORD, AGE, EMAIL, PHONE, DOB, SEX, STATUS )
VALUES (nextval('users_seq'), 'Sajan', 'asdf', 26, 'sajan@sajan.com', '1234567',
		CURRENT_DATE, 'MALE', 'AC');

INSERT INTO ROLES(ID, NAME, description, created_on, modified_on, STATUS )
VALUES (nextval('roles_seq'), 'USER', 'A Stand Alone User', CURRENT_DATE, CURRENT_DATE, 'AC');

INSERT INTO ROLES(ID, NAME, description, created_on, modified_on, STATUS )
VALUES (nextval('roles_seq'), 'INSTRUCTOR', 'An instructor who can create courses and enroll the user', CURRENT_DATE, CURRENT_DATE, 'AC');

INSERT INTO ROLES(ID, NAME, description, created_on, modified_on, STATUS )
VALUES (nextval('roles_seq'), 'ADMIN', 'The administrator of the tenant.', CURRENT_DATE, CURRENT_DATE, 'AC');

insert into user_roles(id, user_id, role_id, created_on, modified_on, status)
values (nextval('user_roles_seq'), 1, 3,CURRENT_DATE, CURRENT_DATE, 'AC' );