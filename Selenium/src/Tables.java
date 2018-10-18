import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tables {
	
	public static void main(String[] args){
		int sum = 0;
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sriram\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.cricbuzz.com/live-cricket-scorecard/18970/pak-vs-sl-2nd-t20i-pakistan-v-sri-lanka-in-uae-2017");
		driver.getTitle();
		System.out.println(driver.getTitle());
		driver.getCurrentUrl();
		System.out.println(driver.getCurrentUrl());
		WebElement table = driver.findElement(By.cssSelector("div [class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']"));
		int count = table.findElements(By.cssSelector("div [class= 'cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();
		for(int i = 0; i<count-2; i++)
		{
			// parent and child nodes
			//System.out.println(table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText());
			String value = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText();
			int valueInteger= (char) Integer.parseInt(value);
			sum=sum+valueInteger;
			
		}
		//System.out.println("the sum is" +sum);
		// following sibling next to an element
		String extras = driver.findElement(By.xpath("(//div[text()='Extras']/following-sibling::div)[1]")).getText();
		int extrasValue=(char) Integer.parseInt(extras);
		int total=sum+extrasValue;
		System.out.println("the total is" +total);


		//System.out.println(driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText());
		System.out.println(driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText());
		driver.close();
		driver.quit();
	}

}
