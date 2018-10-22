import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// http://qaclickacademy.com/practice.php
public class PrintAndCountLinks {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://qaclickacademy.com/practice.php");
		driver.getTitle();
		System.out.println(driver.getTitle());
		System.out.println(driver.findElements(By.tagName("a")).size());
		WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
		System.out.println(footerDriver.findElements(By.tagName("a")).size());
		int count = footerDriver.findElements(By.tagName("a")).size();
		// for each of the footer links click on CTRL+ENTER and open in new tabs and
		// check for the title.
		for (int i = 1; i < count; i++) {
			String clickOnTabs = Keys.chord(Keys.CONTROL, Keys.ENTER);
			footerDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnTabs);
			Thread.sleep(5000L);
		}
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}

	}
}
