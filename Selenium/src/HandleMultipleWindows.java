import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HandleMultipleWindows {
	@Test
	public void main() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		WebElement element = driver.findElement(By.xpath("//a[@id ='privacy-link']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform(); // right click and new tab
		Set<String> ids= driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentWindow = it.next();
		String childWindow = it.next();
		driver.switchTo().window(childWindow); 
		System.out.println(driver.getTitle());
		driver.switchTo().window(parentWindow); 
		System.out.println(driver.getTitle());
}
}