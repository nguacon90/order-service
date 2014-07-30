- Build project 
*mvn clean install

- How to run

*'java -jar package.jar <port>'

if you don't type port parameter, default port is 8080

- URL statistic rest service:
 	* Get top order with biggest value: 'http://localhost:<port>/statistic/getTop/biggestordervalue'
 	* Get top account with biggest order value:'http://localhost:<port>/statistic/getTop/account'
 
- URL order rest service:
 	* placeOrder: 
 		* Accept method: POST
 		* Content-Type: 'application/json'
 		* url: 'http://localhost:<port>/orders/placeOrder'
 		* param: object json Order
 		* Use can using Rest Client plugin firefox to test.
 		
 