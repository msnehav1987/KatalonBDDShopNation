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

String url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)

String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType

Boolean isCanonical

WebUI.openBrowser(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url))

if (deviceType.equalsIgnoreCase("desktop")) {
	WebUI.maximizeWindow()
}



if (applicationName.equalsIgnoreCase('More') || applicationName.equalsIgnoreCase('Shape')) {
    String keyValueFromJson = 'HomePage.categoryhomepagae'

    String xpath = CustomKeywords.'com.helper.browserhelper.ShopNationTest.jsonReader'(keyValueFromJson)

    WebUI.click(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath', [('variable') : xpath]), FailureHandling.STOP_ON_FAILURE)
}
else
{
	WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CatagoryHomePageValidationKibanaURL), 
    FailureHandling.STOP_ON_FAILURE)
}
 
isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

println('isCanonical->' + isCanonical)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'("HomePage.categoryHomepage.CategoryDependency.Subcategories")

CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()