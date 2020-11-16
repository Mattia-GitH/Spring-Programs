DROP TABLE TBL_BOOK IF EXISTS;
DROP TABLE TBL_STUDENT IF EXISTS;

CREATE TABLE PUBLIC.TBL_BOOK(
    ID int not null primary key auto_increment,
    TITLE varchar(20),
    AUTHOR varchar(20),
    ISBN varchar(25)
);

CREATE TABLE PUBLIC.TBL_STUDENT(
    ID int not null primary key auto_increment,
    NAME varchar(20),
    SURNAME varchar(20),
    AGE int
);
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
