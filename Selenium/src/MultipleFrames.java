import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleFrames {

	@Test
	public void framestest() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\sriram\\\\Desktop\\\\Selenium\\\\chromedriver\\\\chromedriver.exe");
		// edge only works on windows 10 i have windows 7
		WebDriver driver = new ChromeDriver();
		driver.get("https://captchacoder.com/MyAccount/Login.aspx");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		int frameNumber = SwitchToFrame(driver, By.className("recaptcha-checkbox-checkmark"));
		driver.switchTo().frame(frameNumber);
		driver.findElement(By.className("recaptcha-checkbox-checkmark")).click();
		driver.close();
		driver.quit();
	}
	
	public static int SwitchToFrame(WebDriver driver, By by)
	{
		int i;
		int frameCount = driver.findElements(By.tagName("iframe")).size();
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		for(i=0;i<frameCount; i++)
		{
			driver.switchTo().frame(i);// switching to the frame and then find the element 
			int count = driver.findElements(by).size();
			if (count > 0)
			{
				break;
			}
			else
			{
				System.out.println("continue searching for the element in the loop");
			}
		}
		driver.switchTo().defaultContent();// important to get out of the frame 
		return i;
	}
}
