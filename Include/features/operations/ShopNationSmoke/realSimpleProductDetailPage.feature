Feature: RealSimple PDP Smoke
Scenario Outline: RealSimple PDP Smoke by validating the product card title
Given: Provided info
	When Navigate to PDP Page where the store name is <storename> and its respective with <url>
	When search and click the product card info
	Then close the browser
	
	Examples: 
      | storename  | url                                                                                                                                                           |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/kitchenaid-kitchenaid-empire-red-5-speed-ultra-power-hand-mixer-khm512-p3468e5635b1edbaaf361f050cf258af5.html |