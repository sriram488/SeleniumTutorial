import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RadioButtons {
	@Test
	public void radioButtonTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.echoecho.com/htmlforms10.htm");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		//driver.findElement(By.xpath("//input[@value = 'Cheese']")).click();
		//driver.findElement(By.xpath("(//input[@type = 'radio'])[7]")).click();
		int count = driver.findElements(By.xpath("//input[@type = 'radio']")).size();
		
		for(int i=0; i< count; i++)
		{
			driver.findElements(By.xpath("//input[@type = 'radio']")).get(i).click();
		}

	}

}
