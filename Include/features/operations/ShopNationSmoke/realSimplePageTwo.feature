Feature: RealSimple Pageii Smoke
Scenario Outline: RealSimple Pageii Smoke by validating the Pageii content
Given: Provided info about the Pageii scenario
	When Navigate to Pageii where the store name is <storename> and its respective with <url>
	When search for the Skyword Article content
	Then close the Pageii session
	
	Examples: 
      | storename  | url                                            															 |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/food-kitchen-ii296987354.html |