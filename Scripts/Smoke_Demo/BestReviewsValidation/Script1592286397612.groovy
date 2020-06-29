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
import com.kms.katalon.core.testobject.TestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath
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
String url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)


public void BestReviewPage(){
String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType



Boolean isCanonical

//WebUI.openBrowser('')

//WebUI.maximizeWindow()

//WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath', ['variable': '//*[@id=\"gbw\"]/div/div/div[1]/div[1]/a']), FailureHandling.STOP_ON_FAILURE)


//WebUI.navigateToUrl( CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType,GlobalVariable.BestReviewsUrl), FailureHandling.STOP_ON_FAILURE)


/**
 * 
 * Reads Desktop.json object repository 
 * finds the x-path value from the Desktop.json and returns it 
 * and the xpath value is parameterized for validation
 * 
 */
String keyValueFromJson="bestreview.sponsortitle";
String xpath = CustomKeywords.'com.helper.browserhelper.ShopNationTest.jsonReader'(keyValueFromJson)
WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
println ("-----------")


//WebUI.verifyElementVisible(findTestObject('Object Repository/'+applicationName+'_Objects/'+applicationName+'_'+deviceType+'/bestReviewSponserTitle'), FailureHandling.STOP_ON_FAILURE)

isCanonical = CustomKeywords.'com.helper.browserhelper.ShopNationTest.validateCanonicalURL'()

println('isCanonical->' + isCanonical)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.footerValidation'()

WebUI.closeBrowser()
}


if (Samsung.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackSamsung'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
}

else if (Desktop.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackChrome'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
	}

else if (iPad.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIpad'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
}

else if (iPhone.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackIphone'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
}

else if(local.contains('true')) {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
}

else if (InternetExplorer.contains('true')){
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.BrowserStackInternetExplorer'(GlobalVariable.BestReviewsUrl)
	BestReviewPage()
}


