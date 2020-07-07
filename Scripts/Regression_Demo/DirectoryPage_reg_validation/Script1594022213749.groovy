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

//CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)
String applicationName = GlobalVariable.applicationName

String deviceType = GlobalVariable.deviceType

String TCName = 'DirectoryPage'

Boolean isCanonical
CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.checkUrlContainsAndReturnURL'(TCName)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.NavigateToPage'("HomePage.BurgerMenu|HomePage.Burger.Brands", "BrandPage");
String param = applicationName + '.brandHeader'

println('param to function->' + param)

String value = CustomKeywords.'com.helper.browserhelper.ShopNationTest.readProperties'(param)

CustomKeywords.'com.helper.browserhelper.ShopNationTest.validate_Directorypage'(value)


