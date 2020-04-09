# programming-languages-api

Back-End API with Kotlin, Spring Boot, JWT, RestTemplate, H2, MockK and Swagger  

* For testing and view the code in the project I recommend using the IntelliJ IDE (free Community or Ultimate edition)
     
     https://www.jetbrains.com/idea/

* How to run : 
     
     Install Gradle
         
     In terminal execute: `./gradlew bootRun`

* For testing endpoints navigate to:

    http://localhost:8080/swagger-ui.html

* You can use Postman

* For view H2 embedded data navigate to:

    http://localhost:8080/h2-console/

    Driver Class: org.h2.Driver

    JDBC URL: jdbc:h2:mem:testdb

    User Name: sa

    Without password
    
* Security:

    Service uses JWT and Spring Security for user authentication and endpoints securing
    
    With swagger: 
    
    Endpoint: /user/register {POST} Save user with password
    
    Endpoint: /login {POST} Login with the previous user and password. In the response body copy the token
    and press Authorize button, then paste the token in value field with Bearer prefix (Bearer XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX)) 
    and press Authorize
    
    After the next requests to the endpoints enabled for this user will be available

    