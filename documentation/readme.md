## How to Run Application
First of all deploy the microservices into the minicube
Steps:
Start the miniKube 
minikube start --cpus=4 --memory='6000mb'

For each microservices go to the root directory and hit the following command (all the commands are inside the makefile if you want to run manually each command)
sudo make k8s-all-create

check if the pod is running
kubectl get pods
kubectl logs {podname}

To get the url of the pod
minikube service {servicename} --url

The whole project consists of multiple microservices. In order to get into the applicaiton fist a user needs to register:
Use Postman or curl to check
### Register Link 
minikube service authsv --url
Method post
url: url/register 
Body: {
	"username":"saroj",
	"password":"12345"
}

After succesful registration, a user can login and authenticate himself.

### Login Link
Method post
url: url/authenticate
Body: {
	"username":"saroj",
	"password":"12345"
}

After succesful login a jwt token is generated.
From now on for accessing all the resources, that jwt token is required.

### To view list of products
minikube service productsv --url
(a few products are saved during initialization)
Method: post
url: url/product
Authorization: Type = Bearer Token
supply the generated token

### To view specific product
minikube service productsv --url
Method: get
url: url/product/id
Authorization: Type = Bearer Token
supply the generated token

### To add product into cart
minikube service ordersv --url
Method: post
url: url/cart/save
Authorization: Type = Bearer Token
supply the generated token
Body:  {
        "id": 2,
        "productId": 1,
        "quantity": 4    
    }
### To place order
minikube service ordersv --url
Method: post
url: url/order
Authorization: Type = Bearer Token
supply the generated token
Body: nothing to supply in body as data for order are generated from cart with rest calls after hitting the url
### Get preferred shipping address
minikube service shipsv --url
Method: Get
url: url/address
Authorization: Type = Bearer Token
supply the generated token
### Add Shipping Address
minikube service shipsv --url
Method: Post
url: url/address
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
minikube service paysv --url
Method: Post
url: url/payment
Authorization: Type = Bearer Token
supply the generated token
Header :  {"secretkey": "bank_secret_key"}
Body: 
{
    	"accountNumber":11125,
    	"routingNumber":222
    }
##### Payment with paypal
minikube service paysv --url
Method: Post
url: url/payment
Authorization: Type = Bearer Token
supply the generated token
Header :  {"secretkey": "paypal_secret_key"}
Body: 
{
    	"email":"jp@example.com",
    	"password":"password"
    }
##### Payment with CC
minikube service paysv --url
Method: Post
url: url/payment
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
