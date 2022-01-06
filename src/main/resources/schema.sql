--drop table IF EXISTS book;
CREATE TABLE book (
  id Number(10) PRIMARY KEY AUTO_INCREMENT,
  isbn          VARCHAR2(50) NOT NULL,
  price Number(10,2) NOT NULL,
  quantity Number(10) NOT NULL
 );