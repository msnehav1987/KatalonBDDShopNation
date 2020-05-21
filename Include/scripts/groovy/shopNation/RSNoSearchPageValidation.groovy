package shopNation
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.interactions.Actions



class RSNoSearchPageValidation{
	WebDriver driver;
	WebElement element;
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */

	@Given("Provided info and open the browser")
	def infoAboutNoSearchScenario(){
		println("Enter Invalid text in search box and validate the header")
	}
	@When("Navigate to No Search Page where the store name is (.*) and its respective with (.*)")
	def goHomePage(String storename, String url){
		println("The RealSimple Main page ,Url is provided")
		WebUI.openBrowser(url)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()
	}

	@When("enter the invalid text in the search text box")
	def enterInvalidText(){
		WebUI.waitForElementVisible(findTestObject('Object Repository/RSNoSearchResultTextBox'), GlobalVariable.timeOut)

		WebUI.setText(findTestObject('Object Repository/RSNoSearchResultTextBox'), 'gjgkdg')

		WebUI.click(findTestObject('Object Repository/RSSerchIcon'))

		WebUI.delay(3)

		WebUI.waitForElementVisible(findTestObject('Object Repository/RSNoResultHeader'), GlobalVariable.timeOut)

		System.out.println('The no result header is available')
	}

	@Then("close the session")
	def logoutTheSession() {
		System.out.println('The header found')
		WebUI.delay(8)
		WebUI.closeBrowser()
		println("last step completed")
	}
}