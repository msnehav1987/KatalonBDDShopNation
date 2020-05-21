$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimplePageNotFound.feature");
formatter.feature({
  "name": "RealSimple PageNotFound Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple PageNotFound Smoke by validating the Page title",
  "description": "Given: Provided info about the PageNotFound scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to PageNotFound where the store name is \u003cstorename\u003e and its respective with \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "search for the Page not found title",
  "keyword": "When "
});
formatter.step({
  "name": "close the PageNotFound session",
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
        "https://realsimple-shop.qa2.shopnation.com/shop/dfjhfjhjhfjd.html"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple PageNotFound Smoke by validating the Page title",
  "description": "Given: Provided info about the PageNotFound scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to PageNotFound where the store name is RealSimple and its respective with https://realsimple-shop.qa2.shopnation.com/shop/dfjhfjhjhfjd.html",
  "keyword": "When "
});
formatter.match({
  "location": "RSPageNotFoundValidation.PageNotFoundLogin(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "search for the Page not found title",
  "keyword": "When "
});
formatter.match({
  "location": "RSPageNotFoundValidation.PageNotFoundHeader()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the PageNotFound session",
  "keyword": "Then "
});
formatter.match({
  "location": "RSPageNotFoundValidation.pageNotFoundCloseBrowser()"
});
formatter.result({
  "status": "passed"
});
});