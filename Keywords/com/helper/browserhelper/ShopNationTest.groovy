package com.helper.browserhelper

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By as By
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.mobile.driver.MobileDriverType

public class ShopNationTest {
	AndroidDriver driver;
	@Keyword
	public void loginIntoPDP(String applicationUrlRealSimple){
		WebUI.openBrowser(applicationUrlRealSimple)
		WebUI.waitForPageLoad(10)
		WebUI.maximizeWindow()
	}


	@Keyword
	public void loginIntoApplication(String applicationUrl){
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		WebUI.openBrowser(applicationUrl)
		WebUI.waitForPageLoad(10)
		//		if (deviceType.equalsIgnoreCase("desktop")) {
		//	WebUI.maximizeWindow()
		//}
	}


	@Keyword
	public boolean validateCanonicalURL() {
		try{
			WebDriver webDriver = DriverFactory.getWebDriver()
			String sourceCode = webDriver.getPageSource()
			String[] codeLines = sourceCode.split(">")
			for(int i=0; i<codeLines.length; i++) {
				if(codeLines[i].contains("canonical")) {
					String desLine = codeLines[i]
					String curURL = webDriver.getCurrentUrl()
					String[] SplitURL = curURL.split(".com")
					return (sourceCode.contains("canonical") && desLine.contains(SplitURL[1]))
				}
			}
		}catch(Exception e) {
			e.printStackTrace()
		}
		return false
	}


	/*
	 *
	 * Get the exact environment specific url from the global variables
	 */
	@Keyword
	public String getURL(String env,String globalVariable){
		String envToExecuteReplacedURL=globalVariable
		switch(env.toLowerCase()){

			case 'prod':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa1':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa1.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa2':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa2.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa3':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa3.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'prod':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break
			default:
				printf("ENVIRONMENT setting failed!!!. the value in Global Variable :: envType is->"+envToExecuteReplacedURL)
				break
		}
	}


	/**
	 *
	 * Common Footer Validation
	 */
	@Keyword
	def footerValidation() {
		String elems = "HomePage.Logo;HomePage.Footer.Copyright;HomePage.Footer.Logo";
		validateMultipleElements(elems);
		//		String [] arr= elems.split(";")
		//		for(int i = 0  ;i<arr.length;i++) {
		//			String keyValueFromJson=arr[i]
		//			String xpath = jsonReader(keyValueFromJson)
		//			WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
		//		}
	}

	/**
	 *
	 * Common validateMultipleElements Validation
	 */
	@Keyword
	def validateMultipleElements(String elementsSepratedBySemiColon) {
		String elems = elementsSepratedBySemiColon
		String [] arr= elems.split(";")
		for(int i = 0  ;i<arr.length;i++) {
			String keyValueFromJson=arr[i]
			String xpath = jsonReader(keyValueFromJson)
			WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
		}
	}


	/**
	 *
	 * Common json reader
	 */
	@Keyword
	public String  jsonReader(String keyValue) {

		String key =keyValue
		String projPath= System.getProperty("user.dir");

		String filePath=projPath+"\\Object Repository\\"+GlobalVariable.applicationName+"_Objects\\"+GlobalVariable.applicationName+"_Desktop\\Desktop.json"

		File f = new File(filePath)

		def InputJSON = new JsonSlurper().parseFile(f,"UTF-8")

		String dataToReturn = InputJSON.get(GlobalVariable.applicationName.toString().toLowerCase()).get(key)

		println ("Parsed the data from->"+filePath+"\n Data ->>"+dataToReturn)

		return dataToReturn
	}

	@Keyword
	public void navigateToScreen(String navigationSequence,String Page_Type){
		try {
			String[] arrNavigationElement = navigationSequence.split("\\|");
			for (String navigationElement : arrNavigationElement) {
				//scrollToViewElement(objectRepository.get(navigationElement),navigationElement.toString());
				String keyValueFromJson=navigationElement
				String xPath = jsonReader(keyValueFromJson)
				WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xPath]))
				//	WebUI.verifyElementVisible(findTestObject(navigationElement), FailureHandling.STOP_ON_FAILURE)
				WebUI.delay(10)
				//	if(GlobalVariable.SlideshowPage ||GlobalVariable.Pageii||GlobalVariable.ProductDetailPage)
				WebUI.click(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xPath]), FailureHandling.STOP_ON_FAILURE)
				WebUI.delay(10)
			}
		}catch(Exception e){
			e.printStackTrace()
		}
	}

	public void NavigateToPage(String xPath, String pageName) {
		navigateToScreen(xPath,pageName);
	}


	@Keyword
	public void verifyElementVisible(String element){
		String xpath = jsonReader("HomePage.categoryHomepage.CategoryDependency.Subcategories")
		WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
		println ("Element "+ element +" is present and visible")
	}

	@Keyword
	public void BrowserStackSamsung(String applicationUrl){
		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("device", "Samsung Galaxy S8");

		//Set the app_url (returned on uploading app on Browserstack) in the 'app' capability
		capabilities.setCapability('browserstack.debug', true);
		capabilities.setCapability('build', "Automation_ShopNation_Katalon_Mob");
		capabilities.setCapability('project', "Shopnation");
		capabilities.setCapability('browserstack.video', true);
		capabilities.setCapability('browserstack.local', true);
		capabilities.setCapability('browserstack.ie.enablePopups', false);
		capabilities.setCapability('browserstack.safari.enablePopups', true);
		capabilities.setCapability('nativeWebTap', true);
		capabilities.setCapability('acceptSslCerts', true);
		capabilities.setCapability('browserstack.geoLocation', "US");
		capabilities.setCapability('browserstack.console', "verbose");
		capabilities.setCapability('browser', "android");
		capabilities.setCapability('platformName', "ANDROID");
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");


		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(browserStackServerURL));
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		WebUI.navigateToUrl(applicationUrl)
//		String sessionid= driver.sessionId
//		println(sessionid)
		WebUI.waitForPageLoad(10)
	}
}
