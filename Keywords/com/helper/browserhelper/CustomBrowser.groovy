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

public class CustomBrowser {


	@Keyword
	public void openBrowser(){
		WebUI.openBrowser("https://www.w3schools.com/html/")
		WebUI.waitForPageLoad(5)
		WebUI.maximizeWindow()
	}

	@Keyword
	public void openBrowserUrl(String url){
		WebUI.openBrowser(url)
		WebUI.waitForPageLoad(5)
		WebUI.maximizeWindow()
	}

	@Keyword
	public void closeBrowser(){

		WebUI.switchToDefaultContent()
		WebUI.switchToWindowIndex(0)
		WebUI.closeBrowser()
	}

	@Keyword
	public void loginIntoApplication(String applicationUrlBugz, String UsernameBugz, String PasswordBugz){
		WebUI.openBrowser(applicationUrlBugz)
		WebUI.waitForPageLoad(4)
		WebUI.maximizeWindow()

		WebUI.waitForElementVisible(findTestObject('Miscellinious/EnterABugLink'), 4)
		WebUI.click(findTestObject('Miscellinious/EnterABugLink'))

		WebUI.waitForElementVisible(findTestObject('Miscellinious/BugzLoginSubmit'), 4)

		WebUI.sendKeys(findTestObject('Miscellinious/BugzUserNameLogin'), UsernameBugz)
		WebUI.sendKeys(findTestObject('Miscellinious/BugzLoginPassword'), PasswordBugz)
		WebUI.click(findTestObject('Miscellinious/BugzLoginSubmit'))
		WebUI.delay(1)
	}

	@Keyword
	public void loginIntoApplicationWith(){
		loginIntoApplication(GlobalVariable.applicationUrlBugz, GlobalVariable.UsernameBugz, GlobalVariable.PasswordBugz)
	}

	@Keyword
	public void logoutFromApplication(){
		WebUI.waitForElementVisible(findTestObject('Miscellinious/BugzLogout'), GlobalVariable.TimeoutBugz)
		WebUI.click(findTestObject('Miscellinious/BugzLogout'))
		WebUI.waitForElementVisible(findTestObject('Miscellinious/EnterABugLink'), GlobalVariable.TimeoutBugz)
		WebUI.closeBrowser()
	}
}
