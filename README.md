# HighRiseResidentialBuildingManagementSystem-Backend
# Description
The aim of this project is to improve the management and maintenance of high rise residential buildings in Malaysia. The two user types are admin and residents.

# System Architecture
The backend of the HRRBMS is developed using Spring Boot and Hibernate. JWT Tokens (Spring Security) are used for authentication and authorization of the system to enhance the security of the system. Stanford Core NLP is installed for categorisation of complaints and to perform sentiment analysis to anlyse the priority of the complaint.

# Steps
1. Open MySQL(MySQL only) and create database with the name of hrrbms_database

CREATE DATABASE hrrbms_database

2. Use a java idle to open the "hrrbms fyp" project file

3. Go to src/main/resources/application.properties to change the following to the information of the database
a)spring.datasource.url= connection string of database
b)spring.datasource.username = username for accessing database
c)spring.datasource.password = password for accessing database

4. Run the project
