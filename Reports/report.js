$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/ShopNationSmoke/realSimpleSitemap.feature");
formatter.feature({
  "name": "RealSimple Sitemap Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "RealSimple Sitemap Smoke by validating the Sitemap header",
  "description": "Given: Provided info about the scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Provided store and Url, Navigate to Sitemap Page where the store name is \u003cstorename\u003e and its respective with \u003curl\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "validate the Sitemap header",
  "keyword": "When "
});
formatter.step({
  "name": "close the sitemap browser",
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
        "https://realsimple-shop.qa2.shopnation.com/shop/sitemap.html"
      ]
    }
  ]
});
formatter.scenario({
  "name": "RealSimple Sitemap Smoke by validating the Sitemap header",
  "description": "Given: Provided info about the scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Provided store and Url, Navigate to Sitemap Page where the store name is RealSimple and its respective with https://realsimple-shop.qa2.shopnation.com/shop/sitemap.html",
  "keyword": "When "
});
formatter.match({
  "location": "RSSiteMapValidation.siteMapPageLogin(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "validate the Sitemap header",
  "keyword": "When "
});
formatter.match({
  "location": "RSSiteMapValidation.siteMapHeader()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the sitemap browser",
  "keyword": "Then "
});
formatter.match({
  "location": "RSSiteMapValidation.siteMapCloseBrowser()"
});
formatter.result({
  "status": "passed"
});
});