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

import io.restassured.*
import io.restassured.authentication.PreemptiveBasicAuthScheme

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
	public static String getURL(String env,String globalVariable){
		String envToExecuteReplacedURL=globalVariable
		switch(env.toLowerCase()){

			case 'prod':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.|%env%", "prod.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa1':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.|%env%", "qa1.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa2':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.|%env%", "qa2.")
				printf("Environment Execution Environament URL is ->->"+envToExecuteReplacedURL)
				return envToExecuteReplacedURL
				break

			case 'qa3':
				envToExecuteReplacedURL=envToExecuteReplacedURL.replaceAll("%env%.|qa3.|qa2.|qa1.|%env%", "qa3.")
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
		capabilities.setCapability('browserName', "android");
		capabilities.setCapability('platformName', "ANDROID");
		capabilities.setCapability('realMobile', "true");
		capabilities.setCapability('deviceOrientation', "portrait");
		capabilities.setCapability('browserstack.appium_version', "1.17.0");
		capabilities.setCapability('browserstack.user', "sundarsivaraman3");
		capabilities.setCapability('browserstack.key', "RxZop5AQyA9hMxborsMz");
		capabilities.setCapability("os_version", "7.0");
		capabilities.setCapability("browserstack.idleTimeout" , "900" );
		
		driver=AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, capabilities, new URL(browserStackServerURL));
		DriverFactory.changeWebDriver(driver)
		//WebUI.navigateToUrl(applicationUrl)

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
			println ("Exception in matchphrase method.Exception is ->> "+e)
		}
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
			System.out.println(responseBuildId.getHeader("kbn-xpack-sig").toString());
			Response searchResult=io.restassured.RestAssured.given()
					.header("kbn-xpack-sig",responseBuildId.getHeader("kbn-xpack-sig").toString())
					.header("kbn-version","6.3.2")
					.header("Content-Type","application/json; charset=utf-8")
					.body(searchString)
					.post(convertedKibanaSourceURL+"/elasticsearch/_msearch");
			System.out.println(searchResult.asString());
			JsonPath jsonPathEvaluator =JsonPath.from(searchResult.asString());
			String categoryid = jsonPathEvaluator.getString("responses[0].hits.hits[0]._source.categoryIds");
			System.out.println(categoryid);
			String  str=categoryid.replace("[","").replace("]","");
			strfinal=str.split(",");
			al=new ArrayList<String>(Arrays.asList(strfinal));
		}

		catch(Exception e)
		{
			println("Exception: ${e}")
			print ("Exception in matchphrase method.Exception is ->> "+e)
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return al;


	}


	public static void authenticationforkibana() {
		String convertedURL= GlobalVariable.kibanaSourceURL.toString().replaceAll("%env%", GlobalVariable.envType)
		//		String convertedURL= getURL(GlobalVariable.envType.toString(),GlobalVariable.kibanaSourceURL.toString())


		RestAssured.baseURI = convertedURL+"/api/console/api_server";
		System.out.println(RestAssured.baseURI);
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


}
