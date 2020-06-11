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

String url = GlobalVariable.url

String applicationName = GlobalVariable.applicationName
String deviceType = GlobalVariable.deviceType
Boolean isCanonical

if ((url.contentEquals('parenting') || url.contentEquals('realsimple')) || url.contentEquals('people')) {
    url = GlobalVariable.CategoryListingPageCPA
} else {
    url = GlobalVariable.url
}

WebUI.openBrowser('')
WebUI.maximizeWindow()
//WebUI.delay(4)
WebUI.navigateToUrl(url, FailureHandling.STOP_ON_FAILURE)



if (applicationName.equalsIgnoreCase('more')) {
    WebUI.openBrowser(GlobalVariable.PDUpage //String applicationName = GlobalVariable.applicationName.toString()
        )
} else {
    WebUI.navigateToUrl(GlobalVariable.ResponseKibanaUrl)

    isCanonical = CustomKeywords.'com.helper.commonutilities.CommonUtilities.validateCanonicalURL'()

    println('isCanonical->' + isCanonical)

    if (GlobalVariable.AlphabetNavigation) {
        WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName+ '_Objects/'+applicationName+'_'+deviceType+'/productUnavailableImage'),FailureHandling.STOP_ON_FAILURE)
    }
    
    WebUI.verifyElementVisible(findTestObject('Object Repository/' + applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/productUnavailableImage'),FailureHandling.STOP_ON_FAILURE)
}

WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageLogo'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject(applicationName + '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterLogo'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject(applicationName +  '_Objects/'+applicationName+'_'+deviceType+'/homePageFooterCopyright'), FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()



