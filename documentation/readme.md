## How to Run Application

The whole project consists of multiple microservices. In order to get into the applicaiton fist a user needs to register:
Use Postman or curl to check
### Register Link 
Method post
url: localhost:8080/register 
Body: {
	"username":"saroj",
	"password":"12345"
}

After succesful registration, a user can login and authenticate himself.

### Login Link
Method post
url: localhost:8080/authenticate
Body: {
	"username":"saroj",
	"password":"12345"
}

After succesful login a jwt token is generated.
From now on for accessing all the resources, that jwt token is required.

### To view list of products
Method: post
url: localhost:8081/product
Authorization: Type = Bearer Token
supply the generated token


<!--stackedit_data:
eyJoaXN0b3J5IjpbMjA2ODE3Nzk4N119
-->