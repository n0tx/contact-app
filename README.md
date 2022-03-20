# Simple Contact Application
SpringBoot  
JPA  
Hibernate  
PostgreSql  
Thymeleaf + SB Admin 2  

Groups Validated difference case when insert and updated  
Custom Annotation for phone-number, and unique email-address  
Pagination for list and search list  
Search list by multiple columns  
SoftDelete for contact deleted  

![image](https://user-images.githubusercontent.com/44139279/159170694-e1f09cc8-2e7e-4d27-a6c9-b13342ec9dd3.png)  


# How to run this application  

## Requirement
$java --version  
11

## Setup database
according to the configuration in application.properties  
postgre user: springboot  
postgre password: springboot  
postgre database: contact  

## Build And Create jar of project 
1. Clone Repository  
$git clone https://github.com/n0tx/contact-app.git
2. Import to IDE  
MenuFile -> Import -> Maven / Existing Maven Projects
3. Build the Project  
RightClick Project -> Run AS -> 5 Maven Build... -> Goals:clean package -DskipTests
4. Start PostgreSql service up
5. Run with cli  
a generated executable jar will exist in target directory in project folder  
$java --version  
11  
$java -jar contact-app-0.0.1-SNAPSHOT.jar  
6. Access Browser Url  
http://localhost:8080


## Auto build and run with IDE
1. Clone Repository  
$git clone https://github.com/n0tx/contact-app.git
2. Import to IDE  
MenuFile -> Import -> Maven / Existing Maven Projects  
3. Start Postgresql database Service Up
4. RightClick Project -> Run As -> Spring Boot App
5. Access Browser Url  
http://localhost:8080
