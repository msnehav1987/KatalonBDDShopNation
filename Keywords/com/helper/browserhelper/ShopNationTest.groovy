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
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import sun.awt.datatransfer.ClipboardTransferable.DataFactory

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By as By
import groovy.json.JsonSlurper as JsonSlurper
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.mobile.driver.MobileDriverType
<<<<<<< HEAD
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.interactions.Actions
import io.appium.java_client.ios.IOSDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.ie.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.*;

=======

import io.restassured.*
import io.restassured.authentication.PreemptiveBasicAuthScheme
>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git

public class ShopNationTest {
	AndroidDriver driver=((RemoteWebDriver) driver);


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
	public static String getURL(String env,String globalVariable){
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
			WebUI.scrollToElement(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), 10, FailureHandling.STOP_ON_FAILURE)
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
<<<<<<< HEAD

=======

>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git
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
<<<<<<< HEAD
		capabilities.setCapability('platformName', "Android");
=======
		capabilities.setCapability('browserName', "android");
		capabilities.setCapability('platformName', "ANDROID");
>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
<<<<<<< HEAD
		capabilities.setCapability('os_version', "7.0");
		capabilities.setCapability('browserstack.idleTimeout', "900");



=======
		capabilities.setCapability("os_version", "7.0");
		capabilities.setCapability("browserstack.idleTimeout" , "900" );
		
>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git
		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(browserStackServerURL));
<<<<<<< HEAD
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		driver.get(applicationUrl)
		DriverFactory.changeWebDriver(driver)
=======
		DriverFactory.changeWebDriver(driver)
		//WebUI.navigateToUrl(applicationUrl)
>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git

	}
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git

	@Keyword
	public void BrowserStackIpad(String applicationUrl){
		//AppiumDriver<IOSDriver> driver = MobileDriverFactory.getDriver();
		IOSDriver driver=((RemoteWebDriver) driver);
		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("device", "iPad Mini 2019");

		//Set the app_url (returned on uploading app on Browserstack) in the 'app' capability
		capabilities.setCapability('browserstack.debug', true);
		capabilities.setCapability('build', "Automation_ShopNation_Katalon_iPad");
		capabilities.setCapability('project', "Shopnation");
		capabilities.setCapability('browserstack.video', true);
		capabilities.setCapability('browserstack.local', true);
		capabilities.setCapability('browserstack.ie.enablePopups', false);
		capabilities.setCapability('browserstack.safari.enablePopups', true);
		capabilities.setCapability('nativeWebTap', true);
		capabilities.setCapability('acceptSslCerts', true);
		capabilities.setCapability('browserstack.geoLocation', "US");
		capabilities.setCapability('browserstack.console', "verbose");
		capabilities.setCapability('browser', "iPad");
		capabilities.setCapability('platformName', "MAC");
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		capabilities.setCapability('os_version', "12");
		capabilities.setCapability('browserstack.idleTimeout', "900");



		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.IOS_DRIVER, capabilities, new URL(browserStackServerURL));
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		driver.get(applicationUrl)
		DriverFactory.changeWebDriver(driver)

	}

	@Keyword
	public void BrowserStackIphone(String applicationUrl){
		IOSDriver driver=((RemoteWebDriver) driver);
		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("device", "iPhone XS");

		//Set the app_url (returned on uploading app on Browserstack) in the 'app' capability
		capabilities.setCapability('browserstack.debug', true);
		capabilities.setCapability('build', "Automation_ShopNation_Katalon_iPad");
		capabilities.setCapability('project', "Shopnation");
		capabilities.setCapability('browserstack.video', true);
		capabilities.setCapability('browserstack.local', true);
		capabilities.setCapability('browserstack.ie.enablePopups', false);
		capabilities.setCapability('browserstack.safari.enablePopups', true);
		capabilities.setCapability('nativeWebTap', true);
		capabilities.setCapability('acceptSslCerts', true);
		capabilities.setCapability('browserstack.geoLocation', "US");
		capabilities.setCapability('browserstack.console', "verbose");
		capabilities.setCapability('browser', "iPhone");
		capabilities.setCapability('platformName', "MAC");
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		capabilities.setCapability('os_version', "12");
		capabilities.setCapability('browserstack.idleTimeout', "900");



		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.IOS_DRIVER, capabilities, new URL(browserStackServerURL));
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		driver.get(applicationUrl)
		DriverFactory.changeWebDriver(driver)

	}

	@Keyword
	public void BrowserStackChrome(String applicationUrl){
		//ChromeDriver driver=((RemoteWebDriver) driver);
		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";

		//		DesiredCapabilities capabilities = new DesiredCapabilities();

		//capabilities.setCapability("device", "iPhone XS");

		//Set the app_url (returned on uploading app on Browserstack) in the 'app' capability


		String envToExecute='qa2'
		applicationUrl=applicationUrl.replace("%env%", envToExecute)

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability("browserVersion", "74");
		chromeOptions.setCapability("platformName", "WINDOWS");
		chromeOptions.setCapability('browserstack.debug', true);
		chromeOptions.setCapability('build', "Automation_ShopNation_Katalon_Chrome");
		chromeOptions.setCapability('project', "Shopnation");
		chromeOptions.setCapability('browserstack.video', true);
		chromeOptions.setCapability('browserstack.local', true);
		chromeOptions.setCapability('browserstack.ie.enablePopups', false);
		chromeOptions.setCapability('browserstack.safari.enablePopups', true);
		chromeOptions.setCapability('nativeWebTap', true);
		chromeOptions.setCapability('acceptSslCerts', true);
		chromeOptions.setCapability('browserstack.geoLocation', "US");
		chromeOptions.setCapability('browserstack.console', "verbose");
		chromeOptions.setCapability('browser', "Chrome");
		chromeOptions.setCapability('browserstack.appium_version', "1.17.0");
		chromeOptions.setCapability('browserstack.user', "sundarsivaraman3");
		chromeOptions.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		//		chromeOptions.setCapability('os_version', "10");
		//		chromeOptions.setCapability('browserstack.idleTimeout', "900");

		WebDriver driver = new RemoteWebDriver(new URL(browserStackServerURL), chromeOptions);
		DriverFactory.changeWebDriver(driver)
		driver.get(applicationUrl);

	}


	@Keyword
	public void BrowserStackInternetExplorer(String applicationUrl){

		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";

		String envToExecute='qa2'
		applicationUrl=applicationUrl.replace("%env%", envToExecute)

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("browserVersion", "11.0");
//		capabilities.setCapability("platformName", "WINDOWS");
		capabilities.setCapability('browserstack.debug', true);
		capabilities.setCapability('build', "Automation_ShopNation_Katalon_InternetExplorer");
		capabilities.setCapability('project', "Shopnation");
		capabilities.setCapability('browserstack.video', true);
		capabilities.setCapability('browserstack.local', true);
		capabilities.setCapability('browserstack.ie.enablePopups', false);
		capabilities.setCapability('browserstack.safari.enablePopups', true);
		capabilities.setCapability('nativeWebTap', true);
		capabilities.setCapability('acceptSslCerts', true);
		capabilities.setCapability('browserstack.geoLocation', "US");
		capabilities.setCapability('browserstack.console', "verbose");
		capabilities.setCapability('browser', "IE");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("resolution", "1366x768");
//		capabilities.setCapability('os_version', "11.0");
		//		chromeOptions.setCapability('browserstack.idleTimeout', "900");
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.merge(capabilities);

		WebDriver driver = new RemoteWebDriver(new URL(browserStackServerURL), options);
		DriverFactory.changeWebDriver(driver)
		driver.get(applicationUrl);

	}
<<<<<<< HEAD
=======

	public String kibanacategoryQuery(String value){
		
		String categoryValue= null;
		//This is Search Query String is for es74 kibana
		//String searchString="{\"index\":\"data-category-*\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[],\"query\":{\"bool\":{\"must\":[],\"filter\":[{\"match_all\":{}},{\"bool\":{\"minimum_should_match\":1,\"should\":["+value+"]}},{\"match_phrase\":{\"homePage\":{\"query\":true}}}],\"should\":[],\"must_not\":[]}}}\n";
		//This is Search Query String is for es63 kibana
		String searchString="{\"index\":\"data-category-*\",\"ignore_unavailable\":true,\"timeout\":30000}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[],\"query\":{\"bool\":{\"must\":[{\"match_all\":{}},{\"bool\":{\"minimum_should_match\":1,\"should\":["+value+"]}},{\"match_phrase\":{\"homePage\":{\"query\":true}}}],\"filter\":[],\"should\":[],\"must_not\":[]}}}\n";
		try{
			authenticationforkibana();
			RequestSpecification httpRequest = io.restassured.RestAssured.given()
			String convertedKibanaSourceURL = GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)

			System.out.println(searchString);
			//GET request to find ResponseIds
			Response responseBuildId = httpRequest.request(io.restassured.http.Method.POST);
			Response searchResult=io.restassured.RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(convertedKibanaSourceURL+"/elasticsearch/_msearch");
			System.out.println(searchResult.asString());
			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			String categoryhome = jsonPathEvaluator.getString("responses[0].hits.hits[0]._id");
			System.out.println(categoryhome);
			categoryValue="-c"+categoryhome;
			
			String navigation=productvalue(getURL(GlobalVariable.envType, GlobalVariable.url), categoryValue);
			System.out.println("\n\n"+navigation);
			int index=navigation.lastIndexOf('/');
			String navigationFirst=navigation.substring(0,index);
			String navigationLast= navigation.substring(navigation.lastIndexOf("/") + 1);
			navigation= navigationFirst+navigationLast;
			System.out.println(navigation);
			WebUI.navigateToUrl(navigation , FailureHandling.STOP_ON_FAILURE) 
			//homepage.navigateToDirectUrl(navigation);
		}
		catch(Exception e)
		{
			println ("Exception in matchphrase method.Exception is ->> "+e)
			//refApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
		}

	}

	public String productvalue(Object object,String pid){

		return (object+pid+".html");

	}


>>>>>>> branch 'master' of https://github.com/pandeyl/KatalonBDDShopNation.git
}
