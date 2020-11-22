
# REST API bank account :
REST API for a bank account management : 
With this API, you can deposit and withdrawal operations on your bank account by providing the user name and account number in the headers parameters.

# Context :
Think of your personal bank account experience When in doubt, go for the simplest solution

# Requirements :
- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing
 
# User Stories descrition :
##### US 1:
**In order to** save money  
**As a** bank client  
**I want to** make a deposit in my account  
 
##### US 2: 
**In order to** retrieve some or all of my savings  
**As a** bank client  
**I want to** make a withdrawal from my account  
 
##### US 3: 
**In order to** check my operations  
**As a** bank client  
**I want to** see the history (operation, date, amount, balance)  of my operations  

# Getting Started

# Building

## What do you need
- Git
- Java 8
- Maven
- curl

## Install and run

Clone this repository:
```text
    git clone https://github.com/ryounsi/api-bank-account.git
```

Open the project with your IDE, Right click on the main class named ApiBankAccountApplication, and then press "run ApiBankAccountApplication".

You can also execute the project with maven:
```text
    mvn package && java -jar target/api-bank-account-0.0.1-SNAPSHOT.jar
```

## Automatic tests

To execute the unit and integration tests :
```text
    mvn test
```

## Manual tests

When the API is running, an embedded Apache Tomcat Server will be running at :  

`<link>` : <http://localhost:9080/mybank/>


1. To access the swagger documentation, please check :

`<link>` : <http://localhost:9080/mybank/swagger-ui/index.html>


2. To access the H2 embedded data base use the link below and the data base configuration declared in ***application.yml***:

`<link>` : <http://localhost:9080/mybank/h2-console>


3. To perform a deposit into the account number ***10000*** and user name ***dtrump*** using swagger :

`<link>` : <http://localhost:9080/mybank/swagger-ui/index.html#/API%20for%20a%20bank%20account%20management./performDepositOrWithdrawalUsingPOST>

+ Click on the button ***Try it out***.
+ Enter in the value ***10000*** the ***accountNumber*** input.
+ Enter in the value ***dtrump*** the ***userName*** input.
+ Enter in the body request below ***postRequest*** input.
```text
	{
		"amount": 1000,
		"description": "External Withdrawal"
	}
```
+ Click on the button ***Execute*** and see the response.


Or with CURL
```text
curl -X POST "http://localhost:9080/mybank/v1/operations" \
 -H "accept: */*" -H "accountNumber: 1000" -H "userName: dtrump"  \
 -H  "Content-Type: application/json" \
 -d "{ \"amount\": 1000, \"description\": \"External Withdrawal\"}" 
```


4. To get the ***2*** operations for the account number ***10000*** and user name ***dtrump*** order by ***date***, filter by ***operationId>0*** using swagger :

+ Go to
`<link>` : <http://localhost:9080/mybank/swagger-ui/index.html#/API%20for%20a%20bank%20account%20management./getAllOperationsHistoryUsingGET>
  
+ Click on the button ***Try it out***.
+ Enter in the value ***10000*** the ***accountNumber*** input.
+ Enter in the value ***dtrump*** the ***userName*** input.
+ Enter in the value ***0*** the ***page*** input.
+ Enter in the value ***2*** the ***size*** input.
+ Enter in the value ***date*** the ***sortBy*** input.
+ Enter in the value ***operationId>0*** the ***searchBy*** input.
+ Click on the button ***Execute*** and see the response.

Or with CURL

```text
 curl -X GET curl -X GET "http://localhost:9080/mybank/v1/operations?page=0&searchBy=operationId%3E0&size=2&sortBy=date"  \
 -H "accept: application/json" -H "accountNumber: 1000" -H "userName: dtrump"
```

5. To get the list of functional errors when using invalid account number ***999*** and user name ***userUnknown*** using swagger :

+ Go to
`<link>` : <http://localhost:9080/mybank/swagger-ui/index.html#/API%20for%20a%20bank%20account%20management./getAllOperationsHistoryUsingGET>
  
+ Click on the button ***Try it out***.
+ Enter in the value ***999*** the ***accountNumber*** input.
+ Enter in the value ***userUnknown*** the ***userName*** input.
+ Click on the button ***Execute*** and see the response.

Or with CURL

```text
 curl -X GET "http://localhost:9080/mybank/v1/operations?page=0&size=10&sortBy=date" \
  -H "accept: application/json" -H "accountNumber: 999" -H "userName: userUnknown"
```