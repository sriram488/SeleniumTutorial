import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegularExpression {

	@Test
	public void regExTest() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.rediff.com/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.cssSelector("a[title*='Sign in']")).click();

		driver.findElement(By.xpath("//input[contains(@id, 'login')]")).sendKeys("testid");
		driver.findElement(By.xpath("//input[contains(@id, 'password')]")).sendKeys("testpwd");
		//driver.findElement(By.cssSelector("input[type*='submit']")).click();
		driver.findElement(By.xpath("//input[contains(@type, 'submit')]")).click();

		driver.close();
		driver.quit();
	}

}
