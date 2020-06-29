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
import org.openqa.selenium.remote.*;
import io.restassured.*
import io.restassured.authentication.PreemptiveBasicAuthScheme
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

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
	public static String getURL(String env,String urlToReplace){
		String envToExecuteReplacedURL=urlToReplace
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
				assert false
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

			println ("Parsed the data from->"+filePath+"\n Found the value for "+keyValue+" which is ->>"+dataToReturn)

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
			println ("Exception is "+e)
			assert false
			e.printStackTrace()
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


		catch(Exception e)
		{
			println("Exception: ${e}")
			print ("Exception in matchphrase method.Exception is ->> "+e)
			System.out.println(e.getMessage());
			e.printStackTrace();
			assert false
		}
		return al;


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
			//
			assert false
			//refApplicationGenericUtils.extentReportLogFAIL(e.getMessage());
		}

	}

	public String productvalue(Object object,String pid){
		printf ("returning ->>"+object+pid+".html")
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
			System.out.println(responseBuildId.getHeader("kbn-xpack-sig").toString());
			Response searchResult=RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(kibanaSourceURL+"/elasticsearch/_msearch");
			System.out.println(searchResult.asString());

			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			for(int i=0;i<=5;i++)
			{
				String pagevalue=jsonPathEvaluator.getString("responses[0].hits.hits["+i+"]._source.id");
				System.out.println(pagevalue);
				CategorylistingArticle="sc"+pagevalue;
				System.out.println(CategorylistingArticle);
				// HomePage homepage=new HomePage(driver, objectRepository, CategorylistingArticle, softAssert, reportLogger);
				String navigation=productvalue((getURL(GlobalVariable.envType, GlobalVariable.SlideshowPage)), CategorylistingArticle);
				System.out.println(navigation);
				WebUI.navigateToUrl(navigation)
				// homepage.navigateToDirectUrl(navigation);


				if(WebUI.verifyElementVisible(findTestObject('Object Repository/ParameterizedXpath/ParameterizedXpath', [('variable') : xPath]), FailureHandling.STOP_ON_FAILURE)){
					System.out.println("The navigated page is"+elementName);
					break;
				}
			}
		}
		catch(Exception e)
		{
			println ("Exception is "+e)
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

	public String clickOnElement(String xpathKey, String elementName){
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
			//			CharSequence url =getURL'(GlobalVariable.envType, GlobalVariable.url)'

			if (deviceType.equalsIgnoreCase('desktop')) {
				println ("Opening on deviceType->"+deviceType )
				WebUI.openBrowser(url, FailureHandling.STOP_ON_FAILURE)
				println ("Maximizing the window")
				WebUI.maximizeWindow()
			}
			else
			{
				WebUI.navigateToUrl(url, FailureHandling.STOP_ON_FAILURE)
			}
		}
		catch(Exception e)
		{
			println ("Exception while openUrlBasedOnDevice method ->>"+e)
			assert false
		}
	}


}


