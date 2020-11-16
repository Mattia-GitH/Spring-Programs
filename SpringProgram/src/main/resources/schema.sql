DROP TABLE TBL_BOOK IF EXISTS;
CREATE TABLE PUBLIC.TBL_BOOK(
    ID int not null primary key auto_increment,
    TITLE varchar(20),
    AUTHOR varchar(20),
    ISBN varchar(25)
);
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

