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
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)

String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType
String TCName =""
Boolean isCanonical
if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people'))  && TCName.equalsIgnoreCase("PDP") ) {
	url = GlobalVariable.CategoryListingPageCPA
} 

if (deviceType.equalsIgnoreCase('desktop')) {
    WebUI.openBrowser(url, FailureHandling.STOP_ON_FAILURE)
    WebUI.maximizeWindow()
}
else
{
	WebUI.navigateToUrl(url, FailureHandling.STOP_ON_FAILURE)
}


String xpath = CustomKeywords.'com.helper.browserhelper.ShopNationTest.jsonReader'('SlideShow.heroarea.author')

CustomKeywords.'com.helper.browserhelper.ShopNationTest.FetchPagefromkibana'('SHOPPING_GUIDE', xpath, 'heroarea.author')

isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

println('isCanonical->' + isCanonical)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()