# assignment4
## Book Management Exercise

### Description
  - Admin login:
      + Managing all information of users login ( add new, update, delete of user)
      + Managing all book of users (add new, update, delete,chang)
      + Searching info users book
      
  - User login
      + Edit infor 
      + Manage books created by that user (add new, update, delete)
  - Guest 
      + View all books enable of system
### Guide start projec
  - Clone this repository (link clone https://github.com/novahub-internship-2017-web/BE_2017_TUYEN.git)
  
  - Config connect database.
	+ Open file application.properties (../assignment4/src/main/resources/application.properties)
	+ Edit username, password
		+ spring.datasource.username=...
		+ spring.datasource.password=...
  - Import database assignment4.sql from ..assignment4/src/main/resources/assignment4.sql (use MySQL)

  - Run project by terminal: 
    + mvn install clean 
    + mvn spring-boot:run
  - Account:
      + account admin: username = admin@gmail.com , password = 123456
      + account user: username  =  a@gmail.com , password = 123456
