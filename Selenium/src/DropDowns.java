import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDowns {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://spicejet.com/");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		Select sel = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		System.out.println(sel);
		//sel.selectByIndex(1);
		sel.selectByValue("USD");
		//sel.selectByVisibleText("INR");
		driver.findElement(By.id("highlight-addons")).click();
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
		driver.findElement(By.xpath("//a[@value ='HBX']")).click();
		driver.findElement(By.xpath("(//a[@value ='TCR'])[2]")).click();
		driver.close();
		driver.quit();
	}

}
