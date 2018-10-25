import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {
	@Test
	public void actionsTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.com");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		Actions actions = new Actions(driver);
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement element = driver.findElement(By.cssSelector("a[id ='nav-link-accountList']"));
		
		//actions.moveToElement(element).build().perform(); //moves mouse focus to that element 
		actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform(); 
		actions.contextClick(element).build().perform(); //right click

	}
}
