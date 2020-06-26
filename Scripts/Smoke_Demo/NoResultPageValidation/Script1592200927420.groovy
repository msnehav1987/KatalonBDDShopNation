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

String url = GlobalVariable.url

String applicationName = GlobalVariable.applicationName
String deviceType = GlobalVariable.deviceType
Boolean isCanonical
String urlComp= GlobalVariable.urlContent

//if (url.contains(urlComp)) {

//	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.url)
	
	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchtext'), FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(10)
	WebUI.setText(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchtext'), 'gjgkdg')
	WebUI.click(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagesearchButton'))
	WebUI.delay(10)
	isCanonical = CustomKeywords.'com.helper.common.CommonUtilities.validateCanonicalURL'()
	
		println('isCanonical->' + isCanonical)
	
		WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)
	WebUI.closeBrowser()
	
	}


if (Samsung.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(url)
	NoResultPage()
}

else if (Desktop.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(url)
	NoResultPage()
	}

else if (iPad.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(url)
	NoResultPage()
}

else if (iPhone.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(url)
	NoResultPage()
}

else if(local.contains('true')) {
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(url)
		NoResultPage()
}

else if (InternetExplorer.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(url)
	NoResultPage()
}





	
