# Automation Framework Demo using - Selenium with Java , TestNG and Maven 

This project is created as a part of learning selenium to demonstrate the Page object model framework and how to use it in Selenium to automate application.
Guru99 bank application is used in this project for Automation. 

Framework Components : 

1. Maven Dependencies - POM.xml file. Dependencies added for Selenium, TestNG, Apache POI, Extent Reports, log4j 
2. TestNG framework - testng.xml file. Tests with proper TestNG annotations driven by TestBase class.
3. Implemented POM design pattern. Page object repository(PageFactory), Initialization of page objects and Actions performed on page objects.
4. Data driven mechanism using Excel sheet - Apache POI API 
5. Custom utilities like - takeScreenshot(), getDatafromExcel() etc.
6. Logging using log4j API - log4j2.xml
7. Reporting using Extent Reports. (avent.extentReport dependency) 
8. Listener class implementing ITestListener interface. 


![Project Structure](https://user-images.githubusercontent.com/99662873/155987256-3ef08eff-756d-48ea-8232-75c3c9ee0a58.png)
