# CathayTest
########## Request and response body log of all API ##########

1. API gets exchange rates
Method: GET
Request: http://localhost:8080/api/exchange?base=EUR&start_date=2025-10-27&end_date=2025-10-29
Response:
  	{
		"response": [
			{
				"updateTime": "2025/10/30 10:10:37",
				"base_currency": "EUR",
				"quote_currency": "USD",
				"close_time": "2025-10-27T23:59:59Z",
				"average_bid": "1.16362",
				"average_ask": "1.16379",
				"high_bid": "1.16538",
				"high_ask": "1.16553",
				"low_bid": "1.16170",
				"low_ask": "1.16186"
			},
			{
				"updateTime": "2025/10/30 10:10:37",
				"base_currency": "EUR",
				"quote_currency": "USD",
				"close_time": "2025-10-28T23:59:59Z",
				"average_bid": "1.16555",
				"average_ask": "1.16572",
				"high_bid": "1.16677",
				"high_ask": "1.16692",
				"low_bid": "1.16249",
				"low_ask": "1.16263"
			}
		]
	}


2. API creates new currency
Method: POST
Request: http://localhost:8080/api/currency
Body: 
	{
		"code": "VND",
		"name": "Viet Nam Dong",
		"country": "Viet Nam",
		"createdUser": "ADMIN"
	}
Response:
	{
		"code": "VND",
		"name": "Viet Nam Dong",
		"country": "Viet Nam",
		"createdUser": "ADMIN",
		"createdAt": "2025-10-30T10:14:02.106366",
		"updatedUser": null,
		"updatedAt": "2025-10-30T10:14:02.106366"
	}

3. API gets all currencies
Method: GET
Request: http://localhost:8080/api/currency
Response:
	[
    {
        "code": "JPY",
        "name": "Yen",
        "country": "Japan",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:44.240563",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:44.240563"
    },
    {
        "code": "SGD",
        "name": "Dollar Singapore",
        "country": "Singapore",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:51.459472",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:51.459472"
    },
    {
        "code": "USD",
        "name": "Dollar US",
        "country": "USA",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:38.273695",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:38.273695"
    },
    {
        "code": "VND",
        "name": "Viet Nam Dong",
        "country": "Viet Nam",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:02.106366",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:02.106366"
    }
]

4. API get currency by code
Method: GET
Request: http://localhost:8080/api/currency/VND
Response: 
	{
		"code": "VND",
		"name": "Viet Nam Dong",
		"country": "Viet Nam",
		"createdUser": "ADMIN",
		"createdAt": "2025-10-30T10:14:02.106366",
		"updatedUser": null,
		"updatedAt": "2025-10-30T10:14:02.106366"
	}

5. API updates data 
Method: PUT
Request: http://localhost:8080/api/currency
Body:
	{
		"code": "USD",
		"name": "US Dollar",
		"country": "The United States of America",
		"createdUser": "ADMIN",
		"updatedUser": "ADMIN 1"
	}
Response: 
	{
		"code": "USD",
		"name": "US Dollar",
		"country": "The United States of America",
		"createdUser": "ADMIN",
		"createdAt": "2025-10-30T10:14:38.273695",
		"updatedUser": "ADMIN 1",
		"updatedAt": "2025-10-30T10:18:34.822553"
	}

Call API get currencies list, get the response:
[
    {
        "code": "JPY",
        "name": "Yen",
        "country": "Japan",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:44.240563",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:44.240563"
    },
    {
        "code": "SGD",
        "name": "Dollar Singapore",
        "country": "Singapore",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:51.459472",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:51.459472"
    },
    {
        "code": "USD",
        "name": "US Dollar",
        "country": "The United States of America",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:38.273695",
        "updatedUser": "ADMIN 1",
        "updatedAt": "2025-10-30T10:18:34.822553"
    },
    {
        "code": "VND",
        "name": "Viet Nam Dong",
        "country": "Viet Nam",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:02.106366",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:02.106366"
    }
]

6. API deletes currency by code
Method: DELETE
Request: http://localhost:8080/api/currency/SGD
Response: null (with status 200)

Call API gets currencies list, ge the response:
[
    {
        "code": "JPY",
        "name": "Yen",
        "country": "Japan",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:44.240563",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:44.240563"
    },
    {
        "code": "USD",
        "name": "US Dollar",
        "country": "The United States of America",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:38.273695",
        "updatedUser": "ADMIN 1",
        "updatedAt": "2025-10-30T10:18:34.822553"
    },
    {
        "code": "VND",
        "name": "Viet Nam Dong",
        "country": "Viet Nam",
        "createdUser": "ADMIN",
        "createdAt": "2025-10-30T10:14:02.106366",
        "updatedUser": null,
        "updatedAt": "2025-10-30T10:14:02.106366"
    }
]





















