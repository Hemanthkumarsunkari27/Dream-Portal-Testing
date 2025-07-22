# Dream Portal – QA Automation Testing

## Project Overview
InternShipProjectDream is an **automation testing framework** developed using **Selenium + Java** with the **Page Object Model (POM)** design pattern. This project focuses on functional test automation for the **Dream Portal** application, ensuring reliability, accuracy, and maintainability.

-------------------------------------------------
## Features Covered
## `index.html` – **Home Page**
✔️ Validate **loading animation visibility**  
✔️ Ensure **main content** and `"My Dreams"` button are visible  
✔️ Clicking `"My Dreams"` button opens **dreams-diary.html** and **dreams-total.html** in new tabs/windows  

## `dreams-diary.html` – **Dream Log Table**
✔️ Validate **exactly 10 dream entries** exist  
✔️ Ensure dream types are **only** `"Good"` or `"Bad"`  
✔️ Confirm **all three columns** (Dream Name, Days Ago, Dream Type) are filled  

## `dreams-total.html` – **Summary Page**
✔️ Validate **correct dream statistics**:
   - Good Dreams: **6**
   - Bad Dreams: **4**
   - Total Dreams: **10**
   - Recurring Dreams: **2**
✔️ Ensure recurring dream names include:
   - `"Flying over mountains"`
   - `"Lost in maze"`

------------------------------------------------

## Tech Stack
- **Programming Language: Java (JDK 8+)
- **Automation Tool: Selenium WebDriver
- **Test Framework: TestNG
- **Reporting: HTML Reports
- **Build Tool: Maven
- **Logging: Log4j2
- **Version Control: GitHub

## Project Structure

- com.Dream.pageObjects Packages contains all the Page object class, which has separate class for each page that contains the locator of that particular page in it.

    - src/main/java/com/Dream/pageObjects/ 
      - HomePage.java 
      - DreamsDiaryPage.java
      - DreamTotalPage.java
    
- com.Dream.base package contains a base test class, It has methods to open and close the browser which is common for all the tests.
     
    - src/test/java/com/Dream/base/ 
        - BaseTest.java 
     
- com.Dream.test package contains actual test cases

     - src/test/java/com/Dream/tests/ 
        - TC_001_VerifyHomePage.java 
        - TC_002_ValidateDiaryEntries.java 
        - TC_003_CheckSummaryStats.java 
     
- This src/test/resouces has the Log4J xml file that helps for creating the log files.

     - src/test/resources/
        - log4j2.xml 
     
- Logs are available in test-log.log file under the logs folder. 
      
      - logs/
       - test-log.log
     
- Reports are available in index.html/ emailable-reprt.html  file under the test-output folder. 

      - test-output/ 
       - emailable-report.html 
       - index.html 
 
- This file has all the dependencies that are required for this project. 
   
       - pom.xml 
 
     
 
## Installation & Setup

### **Prerequisites**
- Install **Java JDK 8+**
- Install **Maven**
- Install **Eclipse**
- Clone the repository.

### **Setting Up Dependencies**
- Run the following command to install dependencies: **mvn clean install**

### **Execute Tests**
- Navigate to the project directory.
   Run tests using TestNG: **mvn test** (or) **Run testng.xml as TestNG Suite** (or) **Run individual Test as TestNG Test**

### **Reporting & Logs**
- Test Reports: Automatically generated in test-output/ (HTML format).
- Logging: Log files are stored in the logs/ directory.
