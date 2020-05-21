Feature: RealSimple Slide Show Smoke
Scenario Outline: RealSimple Slide Show Smoke by validating the slide show content
Given: Provided info about the slide show scenario
	When Navigate to Slide Show Page where the store name is <storename> and its respective with <url>
	When search for the slide show content
	Then close the slide show session
	
	Examples: 
      | storename  | url                                            																																		  |
      | RealSimple | https://realsimple-shop.qa2.shopnation.com/shop/food-kitchen/maximize-your-small-pantry-houzz-sc281474979953844.html |