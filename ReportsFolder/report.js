$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimplePageTwo.feature");
formatter.feature({
  "name": "RealSimple Pageii Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple Pageii Smoke by validating the Pageii content",
  "description": "Given: Provided info about the Pageii scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Pageii where the store name is \u003cstorename\u003e and its respective with \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "search for the Skyword Article content",
  "keyword": "When "
});
formatter.step({
  "name": "close the Pageii session",
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
        "https://realsimple-shop.qa2.shopnation.com/shop/food-kitchen-ii296987354.html"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple Pageii Smoke by validating the Pageii content",
  "description": "Given: Provided info about the Pageii scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Pageii where the store name is RealSimple and its respective with https://realsimple-shop.qa2.shopnation.com/shop/food-kitchen-ii296987354.html",
  "keyword": "When "
});
formatter.match({
  "location": "RSPageiiValidation.PageiiLogin(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "search for the Skyword Article content",
  "keyword": "When "
});
formatter.match({
  "location": "RSPageiiValidation.PageiiSkywordArticleHeader()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the Pageii session",
  "keyword": "Then "
});
formatter.match({
  "location": "RSPageiiValidation.pageiiCloseBrowser()"
});
formatter.result({
  "status": "passed"
});
});