import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestCase3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.ie.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\MicrosoftWebDriver.exe");
		// edge only works on windows 10 i have windows 7
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.com/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		driver.close();
		driver.quit();
	}

}
