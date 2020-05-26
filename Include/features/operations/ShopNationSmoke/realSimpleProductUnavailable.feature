Feature: RealSimple Product Unavailable Smoke
Scenario Outline: RealSimple Product Unavailable Smoke by validating the product unavailable text title
Given: Provided info about the product unavailable scenario
	When Navigate to Product Unavailable Page where the store name is <storename> and its respective url is <url>
	When the product image is unavailable
	Then close the product unavailable session
	
	Examples: 
      | storename  | url                                              |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/misook-misook-womens-spectator-striped-a-line-dress-black-new-ivory-size-large-p0a29d83a0f56eb5fd4a59106ed14df2d.html |