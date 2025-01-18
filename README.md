database driven authentication
------------------------------
database setup
	h2 database 
	spring data jpa 
	h2 database properties 

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
#spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

get the schema - https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl
create a file in resource folder, schema.sql and paste the content here 
