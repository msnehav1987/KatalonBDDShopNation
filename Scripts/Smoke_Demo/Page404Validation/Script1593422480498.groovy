import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

String Samsung= GlobalVariable.Samsung
String Desktop= GlobalVariable.Desktop
String iPad= GlobalVariable.iPad
String iPhone= GlobalVariable.iPhone
String local= GlobalVariable.local
String InternetExplorer= GlobalVariable.InternetExplorer
String url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
String TCName = ""

public void Page404(){
CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable._404PageUrl)
String TCName = ""

Boolean isCanonical
if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
	url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
}
CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)



WebUI.navigateToUrl(url, FailureHandling.STOP_ON_FAILURE)

isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

println('isCanonical->' + isCanonical)


CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'("404Page.PageTitle", "Page Title for 404 page")


CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()

}


if (Samsung.contains('true')){
	if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable._404PageUrl)
		Page404()
	}
	
}

else if (Desktop.contains('true')){
	if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable._404PageUrl)
		Page404()
	}
}

else if (iPad.contains('true')){
	if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable._404PageUrl)
		Page404()
	}
}

else if (iPhone.contains('true')){
	if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable._404PageUrl)
		Page404()
	}
}

else if(local.contains('true')) {
		if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable._404PageUrl)
		Page404()
	}
}

else if (InternetExplorer.contains('true')){
	if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people')) && TCName.equalsIgnoreCase("PDP") ) {
		url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(url)
	Page404()
	}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable._404PageUrl)
		Page404()
	}
}
