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



class RsPdpPageValidation{
	WebDriver driver;
	WebElement element;
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("Provided info")
	def infoAboutScenario(){
		println("the product detail page provide the info about product and also lists the similar products")
	}
	
	@When("Navigate to PDP Page where the store name is (.*) and its respective with (.*)")
	def realSimplePdp(String storename, String url) {
		println("The RealSimple PDP Url is provided")
		WebUI.openBrowser(url)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()
	}

	@When("search and click the product card info")
	def clickProductCardInfoRS() {
		println("search for product card info")
		driver = DriverFactory.getWebDriver()
		WebUI.delay(10)
		element = driver.findElement(By.xpath("(//a[@class='c-product-card__description-link']/following-sibling::div[@class='c-product-card__product-info']//div)[1]"))
		Actions actions = new Actions(driver)
		actions.moveToElement(element)
		actions.click().build().perform()
		WebUI.delay(10)
	}

	@Then("close the browser")
	def validationRSPdpCompleted() {
		System.out.println('The product card info found')
		WebUI.delay(8)
		WebUI.closeBrowser()
		println("last step completed")
	}
}