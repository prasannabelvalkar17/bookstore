# Getting Started

### Reference Documentation

https://github.com/prasannabelvalkar17/bookstore.git

Description:

I have created a Spring boot application for a bookstore using an in memory H2 database.
I have created few rest endpoints which are as follows,

1. /processBatch : This is used to read the whole CSV file having data as per requirement and process data and result is being saved in CSV file in src/main/resources/ProceessedBatch.csv as well as returned as response.

2. /query/{isbn} : To know how many copies of the book are in stock and what is the current price.

3. /buy/{isbn}/{quantity} : To place an order for a book.

4. /return/{isbn}/{quantity} : To return several copies of a book. Returned books can be resold at current price.

5. /receive : If a book store receives new stock then a new book is added to the database. If a book already exists then the price of the book will be overridden and quantities added in the database.

6. /updateBookPrice : book store updates the price of a book.

7. /multiBuy : Client places a bulk order for multiple books.

8. /multiReturn : Client returns multiple books.
