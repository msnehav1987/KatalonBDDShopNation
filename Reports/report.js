$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimpleProductUnavailable.feature");
formatter.feature({
  "name": "RealSimple Product Unavailable Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple Product Unavailable Smoke by validating the product unavailable text title",
  "description": "Given: Provided info about the product unavailable scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Product Unavailable Page where the store name is \u003cstorename\u003e and its respective url is \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "the product image is unavailable",
  "keyword": "When "
});
formatter.step({
  "name": "close the product unavailable session",
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
        "https://realsimple-shop.qa2.shopnation.com/shop/misook-misook-womens-spectator-striped-a-line-dress-black-new-ivory-size-large-p0a29d83a0f56eb5fd4a59106ed14df2d.html"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple Product Unavailable Smoke by validating the product unavailable text title",
  "description": "Given: Provided info about the product unavailable scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Navigate to Product Unavailable Page where the store name is RealSimple and its respective url is https://realsimple-shop.qa2.shopnation.com/shop/misook-misook-womens-spectator-striped-a-line-dress-black-new-ivory-size-large-p0a29d83a0f56eb5fd4a59106ed14df2d.html",
  "keyword": "When "
});
formatter.match({
  "location": "RSProductUnavailableValidation.realSimpleProductUnavailableLogin(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the product image is unavailable",
  "keyword": "When "
});
formatter.match({
  "location": "RSProductUnavailableValidation.productUnavailableImage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the product unavailable session",
  "keyword": "Then "
});
formatter.match({
  "location": "RSProductUnavailableValidation.realSimpelProductUnavailableCloseBrowser()"
});
formatter.result({
  "status": "passed"
});
});