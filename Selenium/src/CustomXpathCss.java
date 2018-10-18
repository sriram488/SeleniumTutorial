import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CustomXpathCss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.facebook.com/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		//driver.findElement(By.xpath("//*[@id ='email']")).sendKeys("sri");
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("sri");

		driver.findElement(By.xpath("//input[value ='Log In']")).click();
		System.out.println(driver.findElement(By.cssSelector("#error")).getText());
		driver.close();
		driver.quit();
	}

}
