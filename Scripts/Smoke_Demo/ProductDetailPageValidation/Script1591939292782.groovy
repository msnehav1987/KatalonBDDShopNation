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



//String url = GlobalVariable.applicationUrl
String Samsung= GlobalVariable.Samsung
String Desktop= GlobalVariable.Desktop
String iPad= GlobalVariable.iPad
String iPhone= GlobalVariable.iPhone
String local= GlobalVariable.local
String InternetExplorer= GlobalVariable.InternetExplorer
String PdpUrl= GlobalVariable.PDPpage


public void ProductDetailPage(){
String applicationName = GlobalVariable.applicationName
String deviceType = GlobalVariable.deviceType
Boolean isCanonical
String PdpUrl= GlobalVariable.PDPpage

if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))) {

	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.PDPpage)
	WebDriver driver = DriverFactory.getWebDriver()
	WebUI.delay(10)
	WebElement element = driver.findElement(By.xpath("(//a[@class='c-product-card__description-link']/following-sibling::div[@class='c-product-card__product-info']//div)[1]"))

	Actions actions = new Actions(driver)
	actions.moveToElement(element)
	actions.click().build().perform()
	WebUI.delay(10)
		}
else {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.PDPpage)
	WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+  '_Objects/'+applicationName+'_'+deviceType+'/HomePagecategoryhomepagae'), FailureHandling.STOP_ON_FAILURE)
	WebUI.delay(10)
	println ("The element is visible")
}

	isCanonical = CustomKeywords.'com.helper.common.CommonUtilities.validateCanonicalURL'()

	println('isCanonical->' + isCanonical)

	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)
WebUI.closeBrowser()

}

if (Samsung.contains('true')){
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable.PDPpage)
	ProductDetailPage()
}else{
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable.PDPpage)
		ProductDetailPage()
		}
}

else if (Desktop.contains('true')){
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable.PDPpage)
	ProductDetailPage()
	}else{
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable.PDPpage)
	ProductDetailPage()
}
	}

else if (iPad.contains('true')){
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable.PDPpage)
	ProductDetailPage()
}else{
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable.PDPpage)
ProductDetailPage()
}
}

else if (iPhone.contains('true')){
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable.PDPpage)
	ProductDetailPage()
}else{
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable.PDPpage)
ProductDetailPage()
}
}

else if(local.contains('true')) {
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.PDPpage)
		ProductDetailPage()
	}else {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.PDPpage)
	ProductDetailPage()
	}
}

else if (InternetExplorer.contains('true')){
	if ((PdpUrl.contains('parenting') || PdpUrl.contains('realsimple'))){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable.PDPpage)
	ProductDetailPage()
}else {
CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable.PDPpage)
ProductDetailPage()
}
}



