# RoomReservationRESTService
Silent Features of Implementation:

[1] Used Best Practices:

Following tutorial is the best reference:
https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/

[2] Secured REST Web Service Using OAuth2:
Secured the Spring REST Service Using OAuth2 with in-memory token store
and resource owner password credentials grant type.

To Access any resource we need to request access token:
e.g. http://localhost:8000/oauth/token?grant_type=password&username=bill&password=abc123
	 With Authrization Header: Authorization: Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0
   
In Response We will get access token and refresh token. We will use this access token as:
	http://localhost:8000/reservation/api/v1/guests?access_token=8d3291c4-0e8a-42ca-b3d5-666e0ebe9653
  
Reference: http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/	

[3] Added REST Exception handling with Spring:
Instead of showing default, "500: Internal Server Error" message on any exception , we can configure
custom messages and responses using Spring GlobalExceptionHandler at central location.
Messages can be configured using custom message source. General practice is to create new class 
for each type of exception and extend it from Runtime Exception.

Reference: http://fruzenshtein.com/spring-rest-exception-handling-2/
		       http://www.baeldung.com/properties-with-spring
		   
[4] Used Spring Data Repository and H2 DB:
Spring Repository Pattern provides easy and convinient way to access database. We don't have implement 
any class like DTO Pattern. We can implement either CRUDRepository or PagingAndSortingRepository. Most 
of the common data access methods are available out of the box. We can create additionl methods and 
provide query on top of it.

Reference: https://thinkinginobjects.com/2012/08/26/dont-use-dao-use-repository/
		       https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/
Used in-memory database for testing purpose. 

More details at: https://memorynotfound.com/spring-boot-spring-data-jpa-hibernate-h2-web-console/

[5] Added Paging And HATEOAS Support:
We can add pagination support using PagingAndSortingRepository. We just need to pass PageRequest object containing 
common pagination parameters such as page size and page number. We can also pass sort parameters such as sort 
column and sort direction.
Discoverabilty can be provided using simple ApplicationListener and creating ApplicationEvent whenever some action
takes place. We will build 'rel' header to pass more discoverability information to REST client.

Reference: http://www.baeldung.com/rest-api-pagination-in-spring
		
[6] Added Profile Support:

Reference: http://www.springboottutorial.com/spring-boot-profiles

[7] Added Bean Validations:
https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/bean-validation.html

[8] Added Swagger API Documentation
https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger

References:

[1] https://www.linkedin.com/learning/spring-boot-essential-training
