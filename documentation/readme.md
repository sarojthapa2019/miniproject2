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
(a few products are saved during initialization)
Method: post
url: localhost:8081/product
Authorization: Type = Bearer Token
supply the generated token

### To view specific product
Method: get
url: localhost:8081/product/id
Authorization: Type = Bearer Token
supply the generated token

### To add product into cart
Method: post
url: localhost:8082/cart/save
Authorization: Type = Bearer Token
supply the generated token
Body:  {
        "id": 2,
        "productId": 1,
        "quantity": 4    
    }
### To place order
Method: post
url: localhost:8082/order
Authorization: Type = Bearer Token
supply the generated token
Body: nothing to supply in body as data for order are generated from cart with rest calls after hitting the url
### Get preferred shipping address
Method: Get
url: localhost:8083/address
Authorization: Type = Bearer Token
supply the generated token
### Add Ahipping Address
Method: Post
url: localhost:8083/address
Authorization: Type = Bearer Token
supply the generated token
Body: {
	"city": "fairfield",
	"state": "IA",
	"country":"USA",
	"zipCode":"
}

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTExMjI0ODg0NDJdfQ==
-->