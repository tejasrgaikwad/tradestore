# Trade Store Application Version [0.0.1-SNAPSHOT]

## Introduction:

This is backend written in spring boot for storing trades.

### Requirements:
1. JDK 1.8^ (Runtime/compiler)
2. Maven 3.0^ (for running compilation script and creating artifact)
3. Intellij/Eclipse IDE(optional)


### How to start:
From cmd:
1. Go to spring boot project root directory from cmd [tradestore/]
2. Make sure you have java and maven executables in path
3. run command "mvn clean install" (to build the project and run test cases)
4. run below command to run the project:
   "java -jar target/tradestorage-0.0.1-SNAPSHOT.jar" from target directory.


### Documentation of API:

After running the project, hit below url to access swagger docs
http://localhost:8080/swagger-ui.html


### Frameworks Used:
1. Spring Boot for Rest API
2. Swagger for API documentation



### Request Body :
######(Note: You can pass multiple trades in one request using array):
```json
{
   "bookId": "B3",
   "counterParty": "CP-3",
   "createdDate": "26/06/2022",
   "expiredFlag": "N",
   "maturityDate": "27/06/2022",
   "tradeId": "T3",
   "version": 3
}
```

### Get All Trades Response example:
######(Note: Response will return all records):
```json
[
   {
      "tradeId": "T2",
      "version": 1,
      "counterParty": "CP-2",
      "bookId": "B2",
      "maturityDate": "27/06/2022",
      "createdDate": "26/06/2022",
      "expiredFlag": "N"
   },
   {
      "tradeId": "T1",
      "version": 2,
      "counterParty": "CP-1",
      "bookId": "B1",
      "maturityDate": "27/06/2022",
      "createdDate": "26/06/2022",
      "expiredFlag": "N"
   }
]
```