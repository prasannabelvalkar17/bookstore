--drop table IF EXISTS book;
CREATE TABLE book (
  isbn          VARCHAR2(50) PRIMARY KEY,
  price Number(10,2) NOT NULL,
  quantity Number(10) NOT NULL
 );