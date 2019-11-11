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
### Add Shipping Address
Method: Post
url: localhost:8083/address
Authorization: Type = Bearer Token
supply the generated token
Body: {
	"city": "fairfield",
	"state": "IA",
	"country":"USA",
	"zipCode":"55227"
}

### Payment
##### Payment with bank
Method: Post
url: localhost:8084/payment
Authorization: Type = Bearer Token
supply the generated token
Header :  {"secretkey": "bank_secret_key"}
Body: 
{
    	"accountNumber":11125,
    	"routingNumber":222
    }
##### Payment with paypal
Method: Post
url: localhost:8084/payment
Authorization: Type = Bearer Token
supply the generated token
Header :  {"secretkey": "paypal_secret_key"}
Body: 
{
    	"email":"jp@example.com",
    	"password":"password"
    }
##### Payment with CC
Method: Post
url: localhost:8084/payment
Authorization: Type = Bearer Token
supply the generated token
Header :  {"secretkey": "cc_secret_key"}
Body: 
{
    	"cardNumber":12345566552,
    	"expiryDate":"1/28",
    	"securityCode":"123"
    }
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTcyNTU2MzkzMSwtNTkyNjA4MDkwLDE1Mj
I0ODA3NDddfQ==
-->