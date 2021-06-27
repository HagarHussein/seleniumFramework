# Selenium Framework
Selenium Java Testing framework with a Page Object Model design.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Maven
* Eclipse
* Java
* TestNG
* Allure report

### Installing

1. Download and install Maven
2. Download Eclips with Java JDK
3. Install TestNG
4. Install Allure
5. Pull the project into Eclipse on your machine
6. Download chromedriver.exe and place it inside a newly created "Resources" folder inside the main hierarchy of the project
7. Run testng.xml file
8. open cmd and type the following command "Allure serve [allure-results folder location on your disk]"

```
Notes: If the above steps didn't work, you can simply create a new maven project and replace the pages and tests files inside your project
If you want to see the execution report only, it is uploaded here. you can just download it and run the cmd in step 8.
```
![Project hierarchy sample](https://github.com/HagarHussein/seleniumFramework/blob/master/Capture.PNG)
## Features

* Data Driven Testing Framework - *Fetching data from a JSON file using TestNG @BeforeTest annotation and json file.*
* Object Oriendted Design - *Using Page Object Model design pattern.*
* Customized TestNG Report - *Using Allure report that includes a screenshot for the failed steps and a description for each test.*
* HTTP Interceptor for the sign up API request - *Response is saved in a HTML file.*
* Assertion errors recovery and exception handeling - *Using hard assertions, waits and try and catch*
* 23 automated test cases for the positive and negative scenarios 
* Nothing is hardcoded!


## Componenets

### Pages
Inside the "main" folder, each web page is represented by a Java class. 
* Each class consists of 2 man things: **Fileds**, which represnets the elements' locators. and **Methods** : to interact with those elements.
* All the pages need a driver to interact with the browser
* If the action inside each page changes it to another page, the method returns a handle to that new page with the current driver to keep interacting with the same browser session.
* All the pages inherit from a single AbstractPage which holds the WebDriver and WebDriverWait objects encapsulated. 


### Test
There are 3 main packages in this folder.
* data
  - *UserData.json:* input data
  - *JsonReaderData.java*: a class to parse the data from the json file
  - *testCaseNum.json*: a mapping between testcase name and the index to the UserData.json file to parse the correct data for each test case
* utilities
  - *Helper.java:* a helper class that contains a function to take a screenshot from the current browsing session to be used in the report generation
  - *HttpRequest.java:* has 2 functions for sending a request and saving the response of the API
* base
  - *BaseTest.java:* a parent class for all the test classes. Contains all the @Before and @After configuration methods
* Other packages for the test scenarios


## In Scope and Out of scope
* in scope:
All the fields validation are in scope
	- first Name which must start with capital letter
	- last Name which must start with capital letter and can’t be equal First Name
	- Enter valid password and check it must have capital letter, small letter, special character and there is no limit for password
	- Email should be unique for every user
	
* OOS
	- Enter valid Gmail:
		- can not verify whether the entered gmail is a valid gmail or randomly generated email (the software itself can not validate this. it can only validate the correct email format "@gmail.com")
	- After click Submit, Check verification mail sent. Click on verification link on Verification Mail sent
		- The verification is just a step to test the main feature, sign up. It will require connecting to a third party application which is not the scope of testing.
		- This step can be achieved in the automation script by setting the registered email's status in the database as "verified" to pypass this step.
		- The email verification step's testing can be done manually in a separate test case.
	


