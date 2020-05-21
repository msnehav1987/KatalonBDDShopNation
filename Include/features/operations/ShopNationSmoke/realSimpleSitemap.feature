Feature: RealSimple Sitemap Smoke
Scenario Outline: RealSimple Sitemap Smoke by validating the Sitemap header
Given: Provided info about the scenario
	When Provided store and Url, Navigate to Sitemap Page where the store name is <storename> and its respective with <url>
	When validate the Sitemap header
	Then close the sitemap browser
	
	Examples: 
      | storename  | url                                             							|
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/sitemap.html |