Feature: RealSimple No Search Smoke
Scenario Outline: RealSimple No Search Smoke by validating the the invalid text title
Given: Provided info and open the browser
	When Navigate to No Search Page where the store name is <storename> and its respective with <url>
	When enter the invalid text in the search text box
	Then close the session
	
	Examples: 
      | storename  | url                                              |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/ |