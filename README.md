1. Build project 
  * mvn clean install

2. Run project
  * Start zookeeper server
  * Import maven dependency of project masterslave at: `https://github.com/hunghoang/masterslave.git`
  * `java -jar orderservice.jar <port>`
  * if you don't type port parameter, default port is 8080

3. URL statistic rest service:
  * Get top order with biggest value: `http://localhost:<port>/rest/statistic/getTop/biggestordervalue`
  * Get top account with biggest order value: `http://localhost:<port>/rest/statistic/getTop/account`

4. URL order rest service:
  * placeOrder: 
    * Accept method: POST
    * Content-Type: `application/json`
    * Url: `http://localhost:<port>/rest/orders/placeOrder`
    * Params: object json `Order`
    * You can using Rest Client plugin firefox to test.
 		
 