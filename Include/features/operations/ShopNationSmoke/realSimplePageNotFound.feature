Feature: RealSimple PageNotFound Smoke
Scenario Outline: RealSimple PageNotFound Smoke by validating the Page title
Given: Provided info about the PageNotFound scenario
	When Navigate to PageNotFound where the store name is <storename> and its respective with <url>
	When search for the Page not found title
	Then close the PageNotFound session
	
	Examples: 
      | storename  | url                                            									 |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/dfjhfjhjhfjd.html |