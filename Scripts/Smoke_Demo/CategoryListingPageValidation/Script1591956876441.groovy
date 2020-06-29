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

String applicationName = GlobalVariable.applicationName
String deviceType = GlobalVariable.deviceType
Boolean isCanonical
//String urlComp= GlobalVariable.urlContent

public void CategoryListingPage(){
	String url = GlobalVariable.url
	
	String applicationName = GlobalVariable.applicationName
	String deviceType = GlobalVariable.deviceType
	Boolean isCanonical

//	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.url)
	
	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/CategoryListingNavigation'), FailureHandling.STOP_ON_FAILURE)
	WebUI.click(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/CategoryListingNavigation'), FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagecategoryhomepagae'), FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(10)
	println ("The element is visible")


//	isCanonical = CustomKeywords.'com.helper.common.CommonUtilities.validateCanonicalURL'()
//
//	println('isCanonical->' + isCanonical)

	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)
WebUI.closeBrowser()

}

if (Samsung.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(url)
	CategoryListingPage()
}

else if (Desktop.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(url)
	CategoryListingPage()
	}

else if (iPad.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(url)
	CategoryListingPage()
}

else if (iPhone.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(url)
	CategoryListingPage()
}

else if(local.contains('true')) {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(url)
	CategoryListingPage()
}

else if (InternetExplorer.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(url)
	CategoryListingPage()
}




