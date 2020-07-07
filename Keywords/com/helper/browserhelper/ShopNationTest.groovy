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
import org.openqa.selenium.JavascriptExecutor
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import com.kms.katalon.core.mobile.driver.MobileDriverType
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
import java.util.concurrent.TimeUnit

import org.openqa.selenium.remote.*;
import io.restassured.*
import io.restassured.authentication.PreemptiveBasicAuthScheme
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.apache.commons.lang3.StringUtils
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
			println ("Exception is "+e)
			assert false
			e.printStackTrace()
		}
		return false
	}


	/*
	 *
	 * Get the exact environment specific url from the global variables
	 */
	@Keyword
	public  String getURL(String env,String urlToReplace){
		String envToExecuteReplacedURL=urlToReplace
		println ("Switching to Environment ->"+env+" for the url -> "+urlToReplace)
		switch(env.toLowerCase()){

			case 'prod':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "")
				println("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa1':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa1.")
				println("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa2':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa2.")
				println("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa3':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "qa3.")
				println("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'prod':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.", "")
				println("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break
			default:
				assert false
				println("ENVIRONMENT setting failed!!!. the value in Global Variable :: envType is->"+envToExecuteReplacedURL)
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


		try{
			String elems = elementsSepratedBySemiColon
			String [] arr= elems.split(";")
			for(int i = 0  ;i<arr.length;i++) {
				String keyValueFromJson=arr[i]
				String xpath = jsonReader(keyValueFromJson)
				WebUI.scrollToElement(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), 10, FailureHandling.STOP_ON_FAILURE)
				WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
			}
		}
		catch(Exception e) {
			println ("Exception in validateMultipleElements method ->>"+e);
			assert false
		}
	}


	/**
	 *
	 * Common json reader
	 */
	@Keyword
	public String  jsonReader(String keyValue) {
		try{
			String key =keyValue
			String projPath= System.getProperty("user.dir");
			CharSequence applicationName = GlobalVariable.applicationName
			if (applicationName.contains("gardens")) {
				applicationName="BetterHomesAndGardens"
			}
			String filePath=projPath+"\\Object Repository\\"+applicationName+"_Objects\\"+applicationName+"_Desktop\\Desktop.json"

			File f = new File(filePath)

			def InputJSON = new JsonSlurper().parseFile(f,"UTF-8")

			String dataToReturn = InputJSON.get(applicationName.toString().toLowerCase()).get(key)

			//println ("Parsed the data from->"+filePath+"\n Found the value for "+keyValue+" which is ->>"+dataToReturn)

			return dataToReturn
		}
		catch(Exception e ) {
			println ("Exception in jsonReader ->>"+e)
			assert false
			e.printStackTrace()
		}
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
			println("Error Occured in function navigateToScreen for page  "+ Page_Type);
			println("Error Occured in function navigateToScreen "+e.getMessage());
			println("Error Occured in function navigateToScreen");
			println("error occured in function navigateToScreen"+e.getMessage());
			println ("Exception in  navigateToScreen ->> "+e)
			assert false
		}
	}

	public void NavigateToPage(String xPath, String pageName) {
		navigateToScreen(xPath,pageName);
	}


	@Keyword
	public void verifyElementVisible(String element){
		try{
			String xpath = jsonReader(element)

			WebUI.scrollToElement(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), 30, FailureHandling.STOP_ON_FAILURE)
			println ("Scrolled till Element "+ element +" with xpath ->"+xpath+" Successfully.")
			WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
			println ("Element "+ element +" with xpath ->"+xpath+" is present and visible")
		}
		catch(Exception e ) {
			println ("Exception in verifyElementVisible method ->> "+e)
			assert false
			e.printStackTrace()
		}
	}


	@Keyword
	public void verifyElementVisible(String element,String elementName){
		verifyElementVisible(element)
		println ("Element "+ elementName +" is present and visible")
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
		capabilities.setCapability('platformName', "Android");
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		capabilities.setCapability("os_version", "7.0");
		capabilities.setCapability("browserstack.idleTimeout" , "900" );

		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(browserStackServerURL));
		String envToExecute='qa2'
		String platform = GlobalVariable.platformName
		String deviceType = GlobalVariable.deviceType
		applicationUrl=applicationUrl.replace("%env%", envToExecute)
		driver.get(applicationUrl)
		DriverFactory.changeWebDriver(driver)
	}


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

	@Keyword
	public void BrowserStackFireFox(String applicationUrl){
		String browserStackServerURL = "https://sundarsivaraman3:RxZop5AQyA9hMxborsMz@hub-cloud.browserstack.com/wd/hub";
		String envToExecute='qa2'
		applicationUrl=applicationUrl.replace("%env%", envToExecute)

		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("browserVersion", "52.0");
		ffprofile.setPreference("platformName", "WINDOWS");
		ffprofile.setPreference('browserstack.debug', true);
		ffprofile.setPreference('build', "Automation_ShopNation_Katalon_Firefox");
		ffprofile.setPreference('project', "Shopnation");
		ffprofile.setPreference('browserstack.video', true);
		ffprofile.setPreference('browserstack.local', true);
		ffprofile.setPreference('browserstack.ie.enablePopups', false);
		ffprofile.setPreference('browserstack.safari.enablePopups', true);
		ffprofile.setPreference('nativeWebTap', true);
		ffprofile.setPreference('acceptSslCerts', true);
		ffprofile.setPreference('browserstack.geoLocation', "US");
		ffprofile.setPreference('browserstack.console', "verbose");
		ffprofile.setPreference('browser', "Firefox");
		ffprofile.setPreference('browserstack.appium_version', "1.17.0");
		ffprofile.setPreference('browserstack.user', "sundarsivaraman3");
		ffprofile.setPreference('browserstack.key', "RxZop5AQyA9hMxborsMz");
		ffprofile.setAcceptUntrustedCertificates(true);
		ffprofile.setAssumeUntrustedCertificateIssuer(false);
		ffprofile.setPreference("browser.cache.disk.enable", false);
		ffprofile.setPreference("browser.cache.memory.enable", false);
		ffprofile.setPreference("browser.cache.offline.enable", false);
		ffprofile.setPreference("network.http.use-cache", false);
		ffprofile.setPreference("resolution", "1024x768");
		FirefoxOptions Options = new FirefoxOptions();
		Options.setProfile(ffprofile);
		//		chromeOptions.setCapability('os_version', "10");
		//		chromeOptions.setCapability('browserstack.idleTimeout', "900");

		WebDriver driver = new RemoteWebDriver(new URL(browserStackServerURL), Options);
		DriverFactory.changeWebDriver(driver)
		driver.get(applicationUrl);

	}


	@Keyword
	public void matchPhrase(String envType,String applicationName){
		String matchPhrase="";
		String indexName="_id";
		String value = null;
		try{
			ArrayList<String> ListOfValues =kibanacategory(applicationName);

			for(String indexValues:ListOfValues)
				matchPhrase=matchPhrase+"{\"match_phrase\":{\""+indexName+"\":"+indexValues.replaceAll(" ", "")+"}},";
			value=matchPhrase.substring(0, matchPhrase.length()-1);
			kibanacategoryQuery(value);
		}catch(Exception e) {
			println ("Exception is "+e)
			assert false
			println ("Exception in matchphrase method.Exception is ->> "+e)
		}
	}



	public String PDUfromkibana(boolean shouldExistOrNot,String pageURL,String xPathKey, String elementName){
		String appName= GlobalVariable.applicationName
		//String appnameOnKibana=propsObject.getProperty(applicationName+".kibana");
		//This is Search Query String is for es74 kibana
		//String searchString="{\"index\":\"product\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[{\"field\":\"dateChanged\",\"format\":\"date_time\"},{\"field\":\"dateCreated\",\"format\":\"date_time\"},{\"field\":\"dateImage\",\"format\":\"date_time\"},{\"field\":\"dateScraped\",\"format\":\"date_time\"},{\"field\":\"dateUpdated\",\"format\":\"date_time\"}],\"query\":{\"bool\":{\"must\":[],\"filter\":[{\"match_all\":{}},{\"match_phrase\":{\"available\":{\"query\":false}}},{\"match_phrase\":{\"monetization\":{\"query\":\"CPA\"}}},{\"match_phrase\":{\"image.valid\":{\"query\":true}}},{\"match_phrase\":{\"hierarchyIds\":{\"query\":\""+appnameOnKibana+"\"}}}],\"should\":[],\"must_not\":[]}}}\n";
		//This is Search Query String is for  es63 Kibana
		String searchString="{\"index\":\"product\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[\"dateChanged\",\"dateCreated\",\"dateImage\",\"dateScraped\",\"dateUpdated\"],\"query\":{\"bool\":{\"must\":[{\"match_all\":{}},{\"match_phrase\":{\"available\":{\"query\":false}}},{\"match_phrase\":{\"hierarchyIds\":{\"query\":\""+appName.toLowerCase()+"\"}}},{\"match_phrase\":{\"image.valid\":{\"query\":true}}},{\"match_phrase\":{\"monetization\":{\"query\":\"CPA\"}}}],\"filter\":[],\"should\":[],\"must_not\":[]}}}\n";
		String productvalue2 = null;
		String convertedKibanaSourceURL = GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)

		try{
			authenticationforkibana();
			RequestSpecification httpRequest = io.restassured.RestAssured.given();
			//GET request to find ResponseIds
			Response responseBuildId = httpRequest.request(io.restassured.http.Method.POST);
			println(responseBuildId.getHeader("kbn-xpack-sig").toString());
			// Response for kibana es74
			//			Response searchResult=given()
			//					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
			//					.header("kbn-version","7.4.2")
			//					.header("Content-Type","application/json; charset=utf-8")
			//					.body(searchString)
			//					.post(kibanaSourceURL+"/elasticsearch/_msearch?rest_total_hits_as_int=true&ignore_throttled=true");
			// Response for kibana es63
			Response searchResult=io.restassured.RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(convertedKibanaSourceURL+"/elasticsearch/_msearch");

			println(searchResult.asString());
			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			for(int i=0;i<=3;i++)
			{
				String productid=jsonPathEvaluator.getString("responses[0].hits.hits["+i+"]._id");
				productvalue2="p"+productid;
				println("kibana response productvalue2->>"+productvalue2);
				//HomePage homepage=new HomePage(driver, objectRepository, productid, softAssert, reportLogger);
				String navigation=productvalue(pageURL, productvalue2);
				println(navigation);
				WebUI.navigateToUrl(navigation, FailureHandling.STOP_ON_FAILURE);
				if(verifyElementVisible(xPathKey,xPathKey)){

					println("The navigated page is"+elementName);
					break;
				}
			}

		}catch(Exception e)
		{
			print ("Exception in PDUfromkibana method.Exception is ->> "+e)

			println(e.getMessage());
			e.printStackTrace();
			assert false
		}
		return productvalue2;
	}

	public ArrayList<String> kibanacategory(String appName) {
		//appName=appName+".kibana"
		//This is Search Query String is for es74 kibana
		//String searchString="{\"index\":\"product\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[{\"field\":\"dateChanged\",\"format\":\"date_time\"},{\"field\":\"dateCreated\",\"format\":\"date_time\"},{\"field\":\"dateImage\",\"format\":\"date_time\"},{\"field\":\"dateScraped\",\"format\":\"date_time\"},{\"field\":\"dateUpdated\",\"format\":\"date_time\"}],\"query\":{\"bool\":{\"must\":[],\"filter\":[{\"match_all\":{}},{\"match_phrase\":{\"hierarchyIds\":{\"query\":\""+appName+"\"}}},{\"match_phrase\":{\"editorsChoicesCount\":{\"query\":\"1\"}}},{\"match_phrase\":{\"hierarchyIdsCount\":{\"query\":\"1\"}}},{\"match_phrase\":{\"duplicate\":{\"query\":false}}}],\"should\":[],\"must_not\":[]}}}\n";
		//This is Search Query String is for es63 kibana
		String searchString="{\"index\":\"product\",\"ignore_unavailable\":true,\"timeout\":30000}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[\"dateChanged\",\"dateCreated\",\"dateImage\",\"dateScraped\",\"dateUpdated\"],\"query\":{\"bool\":{\"must\":[{\"match_all\":{}},{\"match_phrase\":{\"hierarchyIds\":{\"query\":\""+appName.toLowerCase()+"\"}}},{\"match_phrase\":{\"editorsChoicesCount\":{\"query\":1}}},{\"match_phrase\":{\"hierarchyIdsCount\":{\"query\":1}}},{\"match_phrase\":{\"duplicate\":{\"query\":false}}}],\"filter\":[],\"should\":[],\"must_not\":[]}}}\n";
		String[] strfinal=null;
		ArrayList<String>al = null;
		try{
			authenticationforkibana();
			String convertedKibanaSourceURL = GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)
			RequestSpecification httpRequest = io.restassured.RestAssured.given();
			//GET request to find ResponseIds
			Response responseBuildId = httpRequest.request(io.restassured.http.Method.POST);
			println(responseBuildId.getHeader("kbn-xpack-sig").toString());
			Response searchResult=io.restassured.RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(convertedKibanaSourceURL+"/elasticsearch/_msearch");
			println(searchResult.asString());
			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			String categoryid = jsonPathEvaluator.getString("responses[0].hits.hits[0]._source.categoryIds");
			println(categoryid);
			String  str=categoryid.replace("[","").replace("]","");
			strfinal=str.split(",");
			al=new ArrayList<String>(Arrays.asList(strfinal));
		}

		catch(Exception e)
		{
			println("Exception: ${e}")
			print ("Exception in matchphrase method.Exception is ->> "+e)
			println(e.getMessage());
			e.printStackTrace();
			assert false
		}
		return al;


	}


	public static void authenticationforkibana() {
		String convertedURL= GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)
		//		String convertedURL= getURL(GlobalVariable.envType.toString(),GlobalVariable.kibanaSourceURL.toString())


		RestAssured.baseURI = convertedURL+"/api/console/api_server";
		println(RestAssured.baseURI);
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(new String(Base64.getDecoder().decode("cGFuZGV5bA==")));
		authScheme.setPassword(new String(Base64.getDecoder().decode("QWF5YW5zaEAxNw==")));
		RestAssured.authentication = authScheme;

	}


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

			println(searchString);
			//GET request to find ResponseIds
			Response responseBuildId = httpRequest.request(io.restassured.http.Method.POST);
			Response searchResult=io.restassured.RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(convertedKibanaSourceURL+"/elasticsearch/_msearch");
			println(searchResult.asString());
			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			String categoryhome = jsonPathEvaluator.getString("responses[0].hits.hits[0]._id");
			println(categoryhome);
			categoryValue="-c"+categoryhome;

			String navigation=productvalue(getURL(GlobalVariable.envType, GlobalVariable.url), categoryValue);
			println("\n\n"+navigation);
			int index=navigation.lastIndexOf('/');
			String navigationFirst=navigation.substring(0,index);
			String navigationLast= navigation.substring(navigation.lastIndexOf("/") + 1);
			navigation= navigationFirst+navigationLast;
			println(navigation);
			WebUI.navigateToUrl(navigation , FailureHandling.STOP_ON_FAILURE)
			//homepage.navigateToDirectUrl(navigation);
		}
		catch(Exception e)
		{
			println ("Exception in matchphrase method.Exception is ->> "+e)
			//
			assert false
			//refApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
		}

	}

	public String productvalue(Object object,String pid){
		println ("returning ->>"+object+pid+".html")
		return (object+pid+".html");

	}


	public void FetchPagefromkibana(String page,String xPath, String elementName){
		String kibanaSourceURL = GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)
		String appnameOnKibana=GlobalVariable.applicationName.toString().toLowerCase();
		//Kibana Query for es74
		// String searchString="{\"index\":\"data-content-*\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[{\"field\":\"endDate\",\"format\":\"date_time\"},{\"field\":\"publishedDate\",\"format\":\"date_time\"},{\"field\":\"startDate\",\"format\":\"date_time\"},{\"field\":\"updateDate\",\"format\":\"date_time\"}],\"query\":{\"bool\":{\"must\":[],\"filter\":[{\"match_all\":{}},{\"match_phrase\":{\"contentType\":{\"query\":\""+page+"\"}}},{\"match_phrase\":{\"hierarchyId\":{\"query\":\""+appnameOnKibana+"\"}}},{\"match_phrase\":{\"published\":{\"query\":true}}}],\"should\":[],\"must_not\":[]}}}\n";
		//Kibana Query for es63
		String searchString= "{\"index\":\"data-content-*\",\"ignore_unavailable\":true}\n{\"version\":true,\"size\":5,\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{},\"docvalue_fields\":[\"endDate\",\"publishedDate\",\"startDate\",\"updateDate\"],\"query\":{\"bool\":{\"must\":[{\"match_all\":{}},{\"match_phrase\":{\"contentType\":{\"query\":\""+page+"\"}}},{\"match_phrase\":{\"published\":{\"query\":true}}},{\"match_phrase\":{\"hierarchyId\":{\"query\":\""+appnameOnKibana+"\"}}}],\"filter\":[],\"should\":[],\"must_not\":[]}}}\n";
		String CategorylistingArticle;
		try{
			authenticationforkibana();
			RequestSpecification httpRequest = RestAssured.given();
			//GET request to find ResponseIds
			Response responseBuildId = httpRequest.request(io.restassured.http.Method.POST);
			println(responseBuildId.getHeader("kbn-xpack-sig").toString());
			Response searchResult=RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(kibanaSourceURL+"/elasticsearch/_msearch");
			println(searchResult.asString());

			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			for(int i=0;i<=5;i++)
			{
				String pagevalue=jsonPathEvaluator.getString("responses[0].hits.hits["+i+"]._source.id");
				println(pagevalue);
				CategorylistingArticle="sc"+pagevalue;
				println(CategorylistingArticle);
				// HomePage homepage=new HomePage(driver, objectRepository, CategorylistingArticle, softAssert, reportLogger);
				String navigation=productvalue((getURL(GlobalVariable.envType, GlobalVariable.SlideshowPage)), CategorylistingArticle);
				println(navigation);
				WebUI.navigateToUrl(navigation)
				// homepage.navigateToDirectUrl(navigation);


				if(WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath', [('variable') : xPath]), FailureHandling.STOP_ON_FAILURE)){
					println("The navigated page is"+elementName);
					break;
				}
			}
		}
		catch(Exception e)
		{
			println ("Exception in FetchPagefromkibana->> "+e)
			assert false
			//refApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
		}
	}

	@Keyword
	public String getXpathValue(String xpathKey){
		try
		{
			String xpath = jsonReader(xpathKey)
			//WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
			println ("Xpath retrieved for->"+xpathKey +" from json is -> "+xpath)
			return xpath
		}
		catch(Exception e)
		{
			println ("Exception while getXpathValue method ->>"+e)
			assert false
		}

	}

	@Keyword
	public String get(String xpathKey){
		try
		{
			String xpath = jsonReader(xpathKey)
			//WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
			println ("Xpath retrieved for->"+xpathKey +" from json is -> "+xpath)
			return xpath
		}
		catch(Exception e)
		{
			println ("Exception while getXpathValue method ->>"+e)
			assert false
		}

	}

	public String getXpathValue(String xpathKey, String elementName){
		try{
			String xpath = jsonReader(xpathKey)
			//WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]))
			println ("Xpath retrieved for "+elementName+" from json is -> "+xpath)

			return xpath
		}
		catch(Exception e)
		{
			println ("Exception while getXpathValue method ->>"+e)
			assert false
		}
	}

	public void clickOnElement(String xpathKey, String elementName){
		try{
			String xpath = jsonReader(xpathKey)
			//						println ("Xpath retrieved for "+elementName+" from json is -> "+xpath)
			WebUI.click(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), FailureHandling.STOP_ON_FAILURE)
			println ("Clicked on "+elementName+" with xpath -> "+xpath+" Successfully")
		}
		catch(Exception e)
		{
			println ("Exception while clickOnElement method ->>"+e)
			assert false
		}
	}

	public void clickOnElementUsingByXpath(String xpath, String elementName){
		try{
			WebUI.click(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), FailureHandling.STOP_ON_FAILURE)
			println ("Clicked on "+elementName+" with By.xpath -> "+xpath+" Successfully")
		}
		catch(Exception e)
		{
			println ("Exception while clickOnElement method ->>"+e)
			assert false
		}
	}



	public String setElementValue(String xpathKey, String elementName, String elementValueToSet){
		try{
			String xpath = jsonReader(xpathKey)
			//			println ("Xpath retrieved for "+elementName+" from json is -> "+xpath)
			//clickOnElement(xpath, elementName)
			//			println ("Clicked on "+elementName+" with xpath -> "+xpath+" Successfully")
			WebUI.sendKeys(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), elementValueToSet, FailureHandling.STOP_ON_FAILURE)
			println ("SetElementValue on "+elementName+" with xpath -> "+xpath+" as -> "+elementValueToSet+" Successfully")
		}
		catch(Exception e)
		{
			println ("Exception while setElementValue method ->>"+e)
			assert false
		}
	}




	public void nosearch(){

		String applicationName= GlobalVariable.applicationName
		try{
			if(applicationName.equalsIgnoreCase("marthastewart")||applicationName.equalsIgnoreCase("more")||applicationName.equalsIgnoreCase("MyWedding")){
				//WebUI.click(null, FailureHandling.STOP_ON_FAILURE)
				clickOnElement(("HomePage.searchicon"), "search icon");
				setElementValue(("HomePage.searchtext"), "searchtext", "hgytwyw");
				clickOnElement(("HomePage.searchButton"), "searchButton");
			}else{
				//refApplicationGenericUtils.clickElement(getXpathValue("HomePage.searchtext"), "searchtext");
				//				if(applicationName.equalsIgnoreCase("parenting"))
				//					refApplicationGenericUtils.clickElement(getXpathValue("HomePage.Logo"), "Homepage Logo of the application");
				setElementValue(("HomePage.searchtext"), "searchtext", "hgytwyw");
				clickOnElement(("HomePage.searchButton"), "searchButton");
			}
		}catch(Exception e)
		{
			println ("Exception while nosearch method ->>"+e)
			assert false
		}
	}

	public openUrlBasedOnDevice(String urlToReplace)
	{
		String url = getURL(GlobalVariable.envType,urlToReplace)
		try{
			String deviceType = GlobalVariable.deviceType
			String local = GlobalVariable.local
			String desktop = GlobalVariable.Desktop
			String samsung = GlobalVariable.Samsung
			String ipad = GlobalVariable.iPad
			String iphone = GlobalVariable.iPhone
			String internetExplorer= GlobalVariable.InternetExplorer
			//			CharSequence url =getURL'(GlobalVariable.envType, GlobalVariable.url)'

			if (deviceType.equalsIgnoreCase('desktop') || local.equalsIgnoreCase("true")) {
				println ("Opening on deviceType -> local "+deviceType +"" )
				WebUI.openBrowser(url, FailureHandling.STOP_ON_FAILURE)
				println ("Maximizing the window.")
				WebUI.maximizeWindow()
			}
			else if(desktop.equalsIgnoreCase("true")){
				BrowserStackChrome(url)
				WebUI.maximizeWindow()
			}
			else if(samsung.equalsIgnoreCase("true")){
				BrowserStackSamsung(url)
			}
			else if(ipad.equalsIgnoreCase("true")){
				BrowserStackIpad(GlobalVariable.url)
			}
			else if(iphone.equalsIgnoreCase("true")){
				BrowserStackIphone(url)
			}
			else if(internetExplorer.equalsIgnoreCase("true")){
				BrowserStackInternetExplorer(url)
				WebUI.maximizeWindow()
			}

			else
			{
				println ("None of the flags are set in openUrlBasedOnDevice method  !!")
				assert false

			}
		}
		catch(Exception e)
		{
			println ("Exception in openUrlBasedOnDevice method ->>"+e)
			assert false
		}
	}

	@Keyword
	public CharSequence checkUrlContainsAndReturnURL(String TCName) {
		try{
			CharSequence  url = getURL(GlobalVariable.envType, GlobalVariable.url)
			println ("url -> "+url+"\nTCName -> "+TCName)
			if ((url.contains('parenting') || url.contains('realsimple')) || (url.contains('people') && TCName.equalsIgnoreCase('PDP'))) {
				url = (String)getURL(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
			}
			println ("Processed the url in checkUrlContainsAndReturn method and returning -> "+url)
			return url
		}

		catch(Exception e)
		{
			println ("Exception in checkUrlContainsAndReturn method ->>"+e)
			assert false
		}

	}


	public String fetchingTextvalueofElement(String xpath , String elementName)
	{
		try{
			//			String xpath = jsonReader(xpathKey)
			String text = WebUI.getText(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), FailureHandling.STOP_ON_FAILURE)
			println ("Text for element "+elementName+" whose xpath is  "+xpath+" is -> "+text+"  ")
			return text
		}
		catch(Exception e)
		{
			println ("Exception while fetchingTextvalueofElement method ->>"+e)
			assert false
		}
	}

	public String fetchingTextvalueofElement(String xpath )
	{
		try{
			//			String xpath = jsonReader(xpathKey)
			String text = WebUI.getText(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath',['variable':xpath]), FailureHandling.STOP_ON_FAILURE)
			println ("Text for xpath "+xpath+" is -> "+text+"  ")
			return text
		}
		catch(Exception e)
		{
			println ("Exception while fetchingTextvalueofElement method ->>"+e)
			assert false
		}
	}

	public String readProperties(String key){
		FileInputStream fis = null;
		Properties prop = null;
		try {
			String filePath=System.getProperty("user.dir")+"\\Object Repository\\applicationSpecificPropertiesFile\\applicationSpecificTextContent.properties";
			fis = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(fis);
			println ("properties file loaded successfully from the path ->"+filePath)
			println ("Returning value for "+key+" is ->"+ prop.get(key))
			return prop.get(key)
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//		finally {
		//			fis.close();
		//		}
		//		return prop;
	}

	/**
	 * Page title :Verfiying the page title of categorySelected and categorytile
	 * @param xpath of the element and is element name
	 */
	public void VerifyPageTitle(String xpath, String xpath1){

		String categoryselected = fetchingTextvalueofElement((xpath)).toLowerCase();
		String categorytitle = fetchingTextvalueofElement((xpath1)).toLowerCase();
		try{
			if(categorytitle.contains(categoryselected)){
				println(categorytitle+ " tile is displayed successfully");
				println(categorytitle+ " is displayed successfully");
			}else{
				println("The breadcrumb title "+categorytitle + " is not matching with "+ categoryselected);
			}
		}catch (Exception e) {

			e.printStackTrace();
			println("page is not displayed successfully");
			println("page tile is not matching with the selection");
			assert false
		}
	}

	public String deviceType()
	{
		try{
			if(GlobalVariable.Desktop.toString().equalsIgnoreCase("true"))
				return "chrome";
			else if(GlobalVariable.Samsung.toString().equalsIgnoreCase("true"))
				return "android";
			else if(GlobalVariable.iPad.toString().equalsIgnoreCase("true"))
				return "ipad";
			else if(GlobalVariable.iPhone.toString().equalsIgnoreCase("true"))
				return "iphone";
			else if(GlobalVariable.local.toString().equalsIgnoreCase("true"))
				return "desktop";
			else if(GlobalVariable.InternetExplorer.toString().equalsIgnoreCase("true"))
				return "internetexplorer";
			else{
				println ("Unknown device type!!")
				assert false
			}}
		catch(Exception e)
		{
			println("Exception in deviceType method->>"+e)
			assert false
		}
	}

	public boolean verifyingText(String xpath, String expectedText, String ElementName)
	{
		String actualText="";
		try {
			boolean flag;
			actualText= fetchingTextvalueofElement(xpath, ElementName)
			println("Fetched value for xpath "+xpath+" with elementName  " +ElementName+" is -> "+ actualText)
			if (actualText.equalsIgnoreCase(expectedText)){
				flag=true;
				println(actualText+" is equal to "+expectedText);
			}
			else{
				flag=false;
				println(actualText+" :does not match with -"+expectedText);
			}
			return flag;
		}

		catch(Exception e)
		{
			println(actualText+" :does not match with -"+expectedText);
			println("Exception in verifyingText method -> "+e)
			assert false
		}
	}

	public List<WebElement> getListOfElements(String xpath,String ElementName)
	{
		println("getting List Of Elements..")

		List<WebElement> li=new ArrayList<WebElement>();

		try {
			WebDriver driver = DriverFactory.getWebDriver()
			li=driver.findElements(By.xpath(xpath))


		} catch (Exception e) {
			println("Exception in getListOfElements method -> "+e)
			assert false
		}
		return li
	}



	public void waitUntilPageLoads(){
		try{
			long to = TimeUnit.SECONDS.toMillis(GlobalVariable.TimeOut)
			WebDriver driver = DriverFactory.getWebDriver()
			long t= System.currentTimeMillis();
			long end = t+to;
			while(System.currentTimeMillis() < end) {
				// do this
				if(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"))
				{
					println("Page load complete.")
					break;
				}
				// pause to avoid churning
				println("Waiting for Page to load.")

				Thread.sleep( 2000);
			}
		}
		catch(Exception E){
			E.printStackTrace();
			println("Timeout waiting for Page Load Request to complete.");}
	}

	public void validate_Directorypage(String searchkey){
		try {

			String directory_title=fetchingTextvalueofElement(getXpathValue("directorypage.title"), "Directory title");
			String currentURL = WebUI.getUrl()
			String titlecase= StringUtils.capitalize(directory_title);
			println ("titlecase after capitalizing -> "+titlecase )

			//String deviceTypeVal=refApplicationGenericUtils.getDeviceType(driver);

			if(directory_title.equals(titlecase))
				println ("Each word of the title is in Caps");
			else
				println("Each word of the title is not in Caps");
			VerifyPageTitle(getXpathValue("directorypage.title"), getXpathValue("directorypage.breadcrumb"));
			if(deviceType().equalsIgnoreCase("Desktop"))
			{
				println("Device type Desktop")
				verifyingText(getXpathValue("directorypage.LeftnavigationShopBy"), "Shop By", "Leftnavigationheader");
				List<WebElement> Directorieslist =   getListOfElements(get("directorypage.leftnavlinks"), "directorypage.leftnavlinks");
				for(WebElement directorylink: Directorieslist)
				{println(directorylink.getText());}
				String directoryname=fetchingTextvalueofElement(getXpathValue("directorypage.BrandorStorelink"), "Brand/Store link");
				clickOnElementUsingByXpath(get("directorypage.BrandorStorelink"), "directorypage.BrandorStorelink");
				waitUntilPageLoads();

								checkForElement(get("directorypage.TopBrandsorStores.title"), "TopBrandsorStorestitle");
				//				String TopBrandsorStores_title = fetchingTextvalueofElement(getXpathValue("directorypage.TopBrandsorStores.title"), "TopBrandsorStores_title");
				//				refApplicationGenericUtils.softAssertTheCondition(true, refApplicationGenericUtils.doActualTextContains(TopBrandsorStores_title, directoryname), "Brand/Store page is not loaded successfully");
				//				driver.navigate().to(currentURL);
				//			}String TopBrandsorStores_title = fetchingTextvalueofElement(getXpathValue("directorypage.TopBrandsorStores.title"), "TopBrandsorStores_title");
				//			refApplicationGenericUtils.checkForElement(objectRepository.get("directorypage.searchtext"), "searchtext box");
				//			//refApplicationGenericUtils.removeAttributeForElementInDOM(objectRepository.get("directorypage.searchbutton"), "disabled", "searchbutton");
				//			refApplicationGenericUtils.setElementValueEach(objectRepository.get("directorypage.searchtext"), "searchtext box", searchkey);
				//			//	refApplicationGenericUtils.setElementValue(objectRepository.get("directorypage.searchtext"), "searchtext box", searchkey);
				//			Thread.sleep(2000);
				//
				//			refApplicationGenericUtils.checkForElement(objectRepository.get("directorypage.searchbutton"), "searchbutton");
				//			if(deviceTypeVal.equalsIgnoreCase("iPad")||deviceTypeVal.equalsIgnoreCase("iPhone"))
				//			refApplicationGenericUtils.clickSpecialMobile(objectRepository.get("directorypage.searchbutton"), "searchbutton");
				//			else
				//			refApplicationGenericUtils.clickOnElementJS(objectRepository.get("directorypage.searchbutton"), "searchbutton");
				//
				//			TopBrandsorStores_title = fetchingTextvalueofElement(getXpathValue("directorypage.TopBrandsorStores.title"), "TopBrandsorStores_title");
				//
				//			String splitedword=refApplicationGenericUtils.splitingcharcter(TopBrandsorStores_title);
				//			//	String deviceTypeVal=refApplicationGenericUtils.getDeviceType(driver);
				//			if(!(deviceTypeVal.equalsIgnoreCase("iPhone")||deviceTypeVal.equalsIgnoreCase("iPad")||applicationName.equalsIgnoreCase("MyWedding")))
				//			refApplicationGenericUtils.softAssertTheCondition(true, refApplicationGenericUtils.doActualTextContains(splitedword,searchkey), "search functionality is not working");
				//			//validateSearchInDirectoryPage(searchkey);
				//			validateAlphabeticSegrigationOfPages();
			}
		}
		catch (InterruptedException e) {
			println(e.getMessage());
			e.printStackTrace();
		}
	}


}