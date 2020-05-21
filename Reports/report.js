$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimpleStory.feature");
formatter.feature({
  "name": "RealSimple Story page Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple Story Page Smoke by validating the Story Page content",
  "description": "Given: Provided info about the Story Page scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Story Page where the store name is \u003cstorename\u003e and its respective with \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "search for the Story Page content",
  "keyword": "When "
});
formatter.step({
  "name": "close the Story session",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "storename",
        "url"
      ]
    },
    {
      "cells": [
        "RealSimple",
        "https://realsimple-shop.qa2.shopnation.com/shop/beauty/walmart-com-sc281474979898761.html?ins\u003d1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple Story Page Smoke by validating the Story Page content",
  "description": "Given: Provided info about the Story Page scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Story Page where the store name is RealSimple and its respective with https://realsimple-shop.qa2.shopnation.com/shop/beauty/walmart-com-sc281474979898761.html?ins\u003d1",
  "keyword": "When "
});
formatter.match({
  "location": "RSStoryPageValidation.goToStoryPage(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "search for the Story Page content",
  "keyword": "When "
});
formatter.match({
  "location": "RSStoryPageValidation.searchStoryContents()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the Story session",
  "keyword": "Then "
});
formatter.match({
  "location": "RSStoryPageValidation.logoutStoryPageSession()"
});
formatter.result({
  "status": "passed"
});
});