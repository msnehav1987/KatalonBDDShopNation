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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.interactions.Actions as Actions

String url1 = GlobalVariable.url

println('url->>' + url1)

CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, url1)

String TCName = ''

//String url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url.toString())
String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType

Boolean isCanonical

if ((url.contains('parenting') || url.contains('realsimple')) || (url.contains('people') && TCName.equalsIgnoreCase('PDP'))) {
    url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
} else {
    url = url
}

CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)

if (applicationName.equalsIgnoreCase('more')) {
    WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.PDUpage //    WebUI.verifyElementVisible(findTestObject(((((('Object Repository/' + applicationName) + '_Objects/') + applicationName) + 
            ) //  WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.ResponseKibanaUrl))
        )
} else {
    String reducedPDupageUrl = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.PDUpage)

    CustomKeywords.'com.helper.browserhelper.ShopNationTest.PDUfromkibana'(false, reducedPDupageUrl,'ProductDetail.UnavailableImage', 'Product Unavailable Image')

    isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

    println('isCanonical->' + isCanonical)

    CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('ProductDetail.UnavailableImage', 'Product Unavailable Image')
}

CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()