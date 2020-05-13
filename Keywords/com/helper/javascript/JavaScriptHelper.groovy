package com.helper.javascript

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class JavaScriptHelper {

	WebDriver driver= DriverFactory.getWebDriver();
	JavascriptExecutor executor = ((JavascriptExecutor)driver)

	@Keyword
	public void executeScript(String script){
		executor.executeScript(script)
	}

	@Keyword
	public void executeScript(String string, TestObject testObject){
		WebElement element=	WebUiCommonHelper.findWebElement(testObject, GlobalVariable.TimeoutBugz)
		executor.executeScript(string, element)
	}

	@Keyword
	public Object executeAndReturnValue(String script, TestObject testobject) {
		WebElement element=	WebUiCommonHelper.findWebElement(testobject, GlobalVariable.TimeoutBugz)
		return executor.executeScript(script, element)
	}
}
