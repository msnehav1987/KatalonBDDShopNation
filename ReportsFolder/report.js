$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimpleProductDetailPage.feature");
formatter.feature({
  "name": "RealSimple PDP Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple PDP Smoke by validating the product card title",
  "description": "Given: Provided info",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to PDP Page where the store name is \u003cstorename\u003e and its respective with \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "search and click the product card info",
  "keyword": "When "
});
formatter.step({
  "name": "close the browser",
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
        "https://realsimple-shop.qa2.shopnation.com/shop/kitchenaid-kitchenaid-empire-red-5-speed-ultra-power-hand-mixer-khm512-p3468e5635b1edbaaf361f050cf258af5.html"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple PDP Smoke by validating the product card title",
  "description": "Given: Provided info",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to PDP Page where the store name is RealSimple and its respective with https://realsimple-shop.qa2.shopnation.com/shop/kitchenaid-kitchenaid-empire-red-5-speed-ultra-power-hand-mixer-khm512-p3468e5635b1edbaaf361f050cf258af5.html",
  "keyword": "When "
});
formatter.match({
  "location": "RsPdpPageValidation.realSimplePdp(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "search and click the product card info",
  "keyword": "When "
});
formatter.match({
  "location": "RsPdpPageValidation.clickProductCardInfoRS()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the browser",
  "keyword": "Then "
});
formatter.match({
  "location": "RsPdpPageValidation.validationRSPdpCompleted()"
});
formatter.result({
  "status": "passed"
});
});