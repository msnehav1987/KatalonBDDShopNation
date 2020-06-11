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

@com.kms.katalon.core.annotation.SetUp
void SetUp() {
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.loginIntoApplication'(GlobalVariable.ShapeStoryPageUrl)
}

WebUI.waitForElementVisible(findTestObject('Object Repository/MyWeddingStoryPagesectionbody'), GlobalVariable.SleepTime)

WebUI.waitForElementVisible(findTestObject('Object Repository/StoryHeroTitle'), GlobalVariable.SleepTime)

WebUI.waitForElementVisible(findTestObject('Object Repository/StoryHeroDescription'), GlobalVariable.SleepTime)

WebUI.waitForElementVisible(findTestObject('Object Repository/StoryHeroAreaAuthor'), GlobalVariable.SleepTime)

WebUI.click(findTestObject('Object Repository/StoryPageHeroImage'))

WebUI.delay(3)

WebUI.closeBrowser()
