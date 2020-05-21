Feature: RealSimple Story page Smoke
Scenario Outline: RealSimple Story Page Smoke by validating the Story Page content
Given: Provided info about the Story Page scenario
	When Navigate to Story Page where the store name is <storename> and its respective with <url>
	When search for the Story Page content
	Then close the Story session
	
	Examples: 
      | storename  | url                                            																								 |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/beauty/walmart-com-sc281474979898761.html?ins=1 |