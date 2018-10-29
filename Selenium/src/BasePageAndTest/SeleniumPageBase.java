package BasePageAndTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SeleniumPageBase {
	
	protected final WebDriver driver;
	protected WebDriverWait wait;
/*
 * Default constructor that sets the wait timeout to 60 seconds
 */
public SeleniumPageBase(WebDriver driver)
{
	this(driver, 60);
}

public SeleniumPageBase(WebDriver driver, long timeoutInSeconds) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, timeoutInSeconds);
}

public SeleniumPageBase(WebDriver driver, long timeoutInSeconds, long sleepInMillis) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, timeoutInSeconds, sleepInMillis);
}

abstract public void waitToLoad();
}
