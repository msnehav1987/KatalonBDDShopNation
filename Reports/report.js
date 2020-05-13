$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/operations/RealSimplePDP.feature");
formatter.feature({
  "name": "RealSimple PDP Page Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple PDP Page Smoke by validating the product card",
  "description": "Given: The RealSimple PDP Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the product card",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimplePdpPage.ProductCardTitle()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation PDP pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimplePdpPage.validationPdpCompleted()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimplePageNoSearch.feature");
formatter.feature({
  "name": "RealSimple No Search Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple No Search Smoke by validating the invalid text title",
  "description": "Given: The RealSimple NoSearch Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the invalid text and verify the header",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimpleNoSearch.checkTheNoSearchTitleTitle()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation step is pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimpleNoSearch.validationStepCompleted()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimplePageNotFound.feature");
formatter.feature({
  "name": "PDP Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "PDP smoke test by validating the important elements",
  "description": "Given: The RealSimple 404 Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the valid url and close",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimplePageNotFound.checkThePageNotFoundTitle()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation is pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimplePageNotFound.validationCompleted()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimplePageTwo.feature");
formatter.feature({
  "name": "RealSimple Page Two",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple Page Two , validate the skyword content",
  "description": "Given: The RealSimple Page two url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for skyword section title",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimplePageTwo.checkTheSkywordTitle()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation of page two is pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimplePageTwo.validationPageTwo()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimpleProductUnavailable.feature");
formatter.feature({
  "name": "RealSimple Product Unavailable Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple Product Unavailable page image unavailable",
  "description": "Given: The RealSimple Product Unavailable Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the image unavailable",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimpleProductUnavailable.checkImageUnavailability()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation page unavailable is pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimpleProductUnavailable.validationPageUnavailableCompleted()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimpleSiteMap.feature");
formatter.feature({
  "name": "PDP Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "PDP smoke test by validating the important elements",
  "description": "Given: The RealSimple Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "loads the page with valid url",
  "keyword": "And "
});
formatter.match({
  "location": "RealSimpelSiteMap.pageLoadCheckElement()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "move to element",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimpelSiteMap.moveToElementInPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "click the element",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimpelSiteMap.clickTheElement()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimpleSlideShow.feature");
formatter.feature({
  "name": "RealSimple Slide Show Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple Slide Show Smoke by validating Hero section",
  "description": "Given: The RealSimple Slide Show Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the Hero Image section Author",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimpleSlideShow.checkTheHeroImageAuthorTitle()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation Slide Show pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimpleSlideShow.validationSlideShowCompleted()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("Include/features/operations/RealSimpleStory.feature");
formatter.feature({
  "name": "RealSimple Story Page Smoke",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "RealSimple Story Page Smoke by validating the Story Page Hero Image, title, author",
  "description": "Given: The RealSimple Story Page Url",
  "keyword": "Scenario"
});
formatter.step({
  "name": "search for the story page Hero Image, Author, Description header",
  "keyword": "When "
});
formatter.match({
  "location": "RealSimpleStory.checkTheStoryHeroSection()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validation story pass",
  "keyword": "Then "
});
formatter.match({
  "location": "RealSimpleStory.validationStoryCompleted()"
});
formatter.result({
  "status": "passed"
});
});