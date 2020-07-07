import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.List

import org.openqa.selenium.WebElement

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

public class StoryPage_reg_validation{
String TCName = ''

CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.url)
String applicationName = GlobalVariable.applicationName
String deviceType = GlobalVariable.deviceType
Boolean isCanonical
//if((url.contains('parenting') || url.contains('realsimple')) || (url.contains('people') && TCName.equalsIgnoreCase('PDP'))) {
//url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.CategoryListingPageCPA)
//}  else {
//url = url
//}

CharSequence url = CustomKeywords.'com.helper.browserhelper.ShopNationTest.checkUrlContainsAndReturnURL'(TCName)

//CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)
//
//WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.StoryPageUrl))

public void ValidateStoryPage(boolean ImgClickable){
	String deviceType = GlobalVariable.deviceType
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)
	WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.StoryPageUrl))
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.herotitle')
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.heroimage')
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.herodescription')
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.heroarea.author')
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.section.image')
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('SlideShow.section.title')
String elementTextValue= CustomKeywords.'com.helper.browserhelper.ShopNationTest.fetchingTextvalueofElement'('SlideShow.herotitle')
println("The hero title is -> " + elementTextValue)

	CustomKeywords.'com.helper.browserhelper.ShopNationTest.clickOnElement'('StoryPage.productcard.Instabutton', "Insta Button")

	

//Go To New Tab
if(deviceType.equalsIgnoreCase("Desktop")||deviceType.equalsIgnoreCase("Samsung")){
	int currentWindow = WebUI.getWindowIndex()
	println("the current window index is ->"+ currentWindow)
	WebUI.switchToWindowIndex(currentWindow + 1)
WebUI.delay(10)
String Title=WebUI.getWindowTitle()
println("the title is ->"+ Title)
}else{
int currentWindow =WebUI.getWindowIndex()
println("the current window index is ->"+ currentWindow)
String Title=WebUI.getWindowTitle()
println("the title is ->"+ Title)

}

List<WebElement> sectionbody= CustomKeywords.'com.helper.browserhelper.ShopNationTest.getListOfElements'('StoryPage.section.body',"section body")
int sectionbody_size= sectionbody.size()
if(sectionbody_size > 0)
CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Story section body is displayed with total count of "+ sectionbody_size);
else
CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Story section body is not displayed ");


List<WebElement> article = CustomKeywords.'com.helper.browserhelper.ShopNationTest.getListOfElements'('StoryPage.leftnavigation.articles', "leftnavigation.articles")
int article_size= article.size()

if(article_size>0)
{
	System.out.println("Left Navigation Article section is displayed");
	String header = CustomKeywords.'com.helper.browserhelper.ShopNationTest.fetchingTextvalueofElement'('StoryPage.leftnavigation.articlesheader', "article header")
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Total count of articles is : "+ article_size);
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Article grid header is : "+ header);
}
else{
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("No Article section found");
}

}



public void validateParaImageAndBody(){
	String applicationName = GlobalVariable.applicationName
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)
	WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.StoryPageUrl))
try{	
	int paraImage_size= CustomKeywords.'com.helper.browserhelper.ShopNationTest.getListOfElements'('StoryPage.para.Image',"para Image").size()
	if(paraImage_size > 0)
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Story section paragraph image is displayed with total count of "+ paraImage_size);
else
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("No Paragraph Image found");
	
	if(applicationName.equalsIgnoreCase("Shape"))
	{
		int para_size= CustomKeywords.'com.helper.browserhelper.ShopNationTest.getListOfElements'('StoryPage.para.body', "para.body").size();
		if(para_size>0)
		{
			System.out.println("Paragraph section is displayed");
			String header = CustomKeywords.'com.helper.browserhelper.ShopNationTest.fetchingTextvalueofElement'('StoryPage.para.header', "para header");
			System.out.println("Paragraph grid header is : " + header);
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Total count of paragraph is : "+ para_size);
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("Paragraph header is : "+ header);
		}
		else
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportLogINFO'("No Paragraph body found");
	}
	
	}
catch (InterruptedException e) {
	println(e.getMessage());
	e.printStackTrace();
}
}


public void ValidateStoryFooter()
{
	String applicationName = GlobalVariable.applicationName
	CustomKeywords.'com.helper.browserhelper.ShopNationTest.openUrlBasedOnDevice'(url)
	WebUI.navigateToUrl(CustomKeywords.'com.helper.browserhelper.ShopNationTest.getURL'(GlobalVariable.envType, GlobalVariable.StoryPageUrl))
	try
	{
		if(applicationName.equalsIgnoreCase("MyWedding")||applicationName.equalsIgnoreCase("Shape"))
		{
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('StoryPage.productcard.LeftArrow')
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('StoryPage.productcard.RightArrow')
			CustomKeywords.'com.helper.browserhelper.ShopNationTest.verifyElementVisible'('StoryPage.productcard.ShopAll')
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		CustomKeywords.'com.helper.browserhelper.ShopNationTest.extentReportFail'(e.getMessage());
	}
}

}

