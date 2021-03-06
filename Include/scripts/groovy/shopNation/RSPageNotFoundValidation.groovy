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



class RSPageNotFoundValidation{
	WebDriver driver;
	WebElement element;
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("Provided info about the PageNotFound scenario")
	def infoAboutPageNotFound(){
		println("Search the Page not found header title")
	}

	@When("Navigate to PageNotFound where the store name is (.*) and its respective with (.*)")
	def PageNotFoundLogin(String storename, String url){
		println("login to the PageNotFound, the storename is: " +storename+"url is: "+ url)
		WebUI.openBrowser(url)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()
	}
	@When("search for the Page not found title")
	def PageNotFoundHeader(){
		WebUI.waitForElementVisible(findTestObject('Object Repository/RS404Page.PageTitle'), GlobalVariable.timeOut)
		System.out.println('The page title is available')
		WebUI.delay(3)
	}

	@Then("close the PageNotFound session")
	def pageNotFoundCloseBrowser(){
		WebUI.closeBrowser()
		println("Last step completed")
	}
}