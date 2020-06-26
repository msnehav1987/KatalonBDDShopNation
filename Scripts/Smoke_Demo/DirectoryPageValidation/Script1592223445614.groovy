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
String DirectoryUrl= GlobalVariable.DirectoryBrandURL

public void DirectoryPage(){
	String applicationName = GlobalVariable.applicationName
	String deviceType = GlobalVariable.deviceType
	Boolean isCanonical
	String DirectoryUrl= GlobalVariable.DirectoryBrandURL


if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))) {

//	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.DirectoryBrandURL)
	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/directorypagealphabetlist'), FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(10)
	println ("The element is visible")
}else{
//CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.url)
CustomKeywords.'com.helper.browserhelper.ShopNationTest.NavigateToPage'("HomePage.BurgerMenu|HomePage.Burger.Stores","StoresPage")
WebUI.delay(10)
println ("The element is visible")
}

	isCanonical = CustomKeywords.'com.helper.commonutilities.CommonUtilities.validateCanonicalURL'()

	println('isCanonical->' + isCanonical)
	

	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)
WebUI.closeBrowser()


}

if (Samsung.contains('true')){
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable.DirectoryBrandURL)
	DirectoryPage()
}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable.url)
		DirectoryPage()
		}
}

else if (Desktop.contains('true')){
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable.DirectoryBrandURL)
	DirectoryPage()
	}else{
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable.url)
	DirectoryPage()
}
	}

else if (iPad.contains('true')){
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable.DirectoryBrandURL)
	DirectoryPage()
}else{
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable.url)
DirectoryPage()
}
}

else if (iPhone.contains('true')){
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable.DirectoryBrandURL)
	DirectoryPage()
}else{
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable.url)
DirectoryPage()
}
}

else if(local.contains('true')) {
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.DirectoryBrandURL)
		DirectoryPage()
	}else {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.url)
	DirectoryPage()
	}
}

else if (InternetExplorer.contains('true')){
	if ((DirectoryUrl.contains('parenting') || DirectoryUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable.DirectoryBrandURL)
	DirectoryPage()
}else {
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable.url)
DirectoryPage()
}
}

