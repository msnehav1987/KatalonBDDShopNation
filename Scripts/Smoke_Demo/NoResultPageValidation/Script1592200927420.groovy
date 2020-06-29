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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.interactions.Actions

String Samsung= GlobalVariable.Samsung
String Desktop= GlobalVariable.Desktop
String iPad= GlobalVariable.iPad
String iPhone= GlobalVariable.iPhone
String local= GlobalVariable.local
String InternetExplorer= GlobalVariable.InternetExplorer
String url = GlobalVariable.url


public void NoResultPage(){


CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)

String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType
String TCName =""

Boolean isCanonical
if (((url.contains('parenting') || url.contains('realsimple')) || url.contains('people'))  && TCName.equalsIgnoreCase("PDP") ) {
	url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
}


CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.nosearch'()

isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

println('isCanonical->' + isCanonical)


CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'("NoResult.Title","Page Title for No Search Result page")


CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()




//String url = GlobalVariable.url
//
//String applicationName = GlobalVariable.applicationName
//String deviceType = GlobalVariable.deviceType
//Boolean isCanonical
//String urlComp= GlobalVariable.urlContent
//
//if (url.contains(urlComp)) {
//
//	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.url)
//	
//	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchtext'), FailureHandling.STOP_ON_FAILURE)
//	WebUI.delay(10)
//	WebUI.setText(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchtext'), 'gjgkdg')
//	WebUI.click(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchButton'))
//	WebUI.delay(10)
//	
//	}
//
//
//	isCanonical = CustomKeywords.'com.helper.common.CommonUtilities.validateCanonicalURL'()
//
//	println('isCanonical->' + isCanonical)
//
//	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)
//	
//	WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)
//	
//	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)
//WebUI.closeBrowser()

