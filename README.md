# Selenium Framework
Selenium Java Testing framework with a Page Object Model design.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Maven
* Eclipse
* Java
* TestNG

### Installing

1. Download and install Maven
2. Download Eclips with Java JDK
3. Install TestNG
4. Create a new maven project inside Eclipse
5. Replace the main and test folders with the equivalent seleniumFramework folders from this project as well as the pom.xml file
6. Download chromedriver.exe and place it inside a newly created "Resources" folder inside the main hierarchy of the project
7. Create "Reports", "Screenshots" and "Responses" to have the outputs generated inside of them
8. Run "SignUpAndLogin.java" class as a "TestNG Test"

```
Notes: After the test case execution, the API response will be saved in the "Responses" folder.
```
![Project hierarchy sample](https://github.com/HagarHussein/seleniumFramework/blob/master/Capture.PNG)
## Features

* Data Driven Testing Framework - *Fetching data from a JSON file using TestNG @BeforeTest annotation.*
* Object Oriendted Design - *Using Page Object Model design pattern.*
* Customized TestNG Report - *Include a screenshot for the failed steps.*
* HTTP Interceptor for the sign up API request - *Response is saved in a HTML file.*
* Assertion errors recovery and exception handeling - *Using hard assertions, waits and try and catch*


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
* utilities
  - *Helper.java:* a helper class that contains a function to take a screenshot from the current browsing session to be used in the report generation
  - *HttpRequest.java:* has 2 functions for sending a request and saving the response of the API
* base
  - *BaseTest.java:* a parent class for all the test classes. Contains all the @Before and @After configuration methods
* Other packages for the test scenarios


## Limitations

* The customized output report contains only the failed steps with a screenshot to the page that failed.
* The existing test cases passes. To make it fail to be able to see the customized report functionality, modify any web element with a wrong locator.
* Not all the cases are handeled to recover from the exceptions.


