import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestiveDropDowns {

	@Test
	public void autoDropDownTest() throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://ksrtc.in/oprs-web/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		WebElement element = driver.findElement(By.id("fromPlaceName"));
		element.sendKeys("BENG");
		element.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		// Javascript DOM can extract hidden elements

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String valueinField = "return document.getElementById(\"fromPlaceName\").value;";
		String textValue = (String) js.executeScript(valueinField);
		System.out.println(textValue);
		int i = 0;
		while (!textValue.equalsIgnoreCase("BENGALURU INTERNATION AIPORT")) {
			i++;
			element.sendKeys(Keys.ARROW_DOWN);
			textValue = (String) js.executeScript(valueinField);
			System.out.println(textValue);
			if (i > 10) {
				break;
			}
		}
		if (i > 10) {
			System.out.println("text not found in dropdown");
		} else {
			System.out.println("text found in dropdown options");
		}

		// driver.close();
		// driver.quit();
	}

}
