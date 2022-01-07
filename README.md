# Getting Started

### Reference Documentation

https://github.com/prasannabelvalkar17/bookstore.git

Description:
I have created Spring boot application for bookstore using in memory H2 database.
I have created few rest endpoints which are as follows,

1. /processBatch : This is used to read whole CSV file having data as per requirement and process data and result is being saved in CSV file in 					src/main/resources/ProceessedBatch.csv

2. /query/{isbn} : To to know how much copies of the book are in stock and what is the current price.

3. /buy/{isbn}/{quantity} : To place an order for a book.

4. /return/{isbn}/{quantity} : To return several copies of a book. Returned books can be resold at current price.

5. /receive : If book store receive new stock then new book is added to database. If book is already exist then price of book will be override and 				quanties added in database.

6. /updateBookPrice : book store updates the price of a book.

7. /multiBuy : Client places a bulk order for multiple books.

8. /multiReturn : Client return multiple books.

