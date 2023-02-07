import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Quiz {

	static WebDriver driver;
	static List<WebElement> States;
	static Actions action;
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/chromedriver.exe");
		driver=new ChromeDriver();
		action = new Actions(driver);
		driver.get("https://petdiseasealerts.org/forecast-map#/");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		driver.switchTo().frame(0);
		States = driver.findElements(By.xpath("//*[name()='svg'][@id='map-svg']//*[local-name()='g']//*[@class='region']"));
		int i=0;
		for(WebElement ele:States) {
			action.moveToElement(ele).build().perform();
			Thread.sleep(100);
			String StateName=ele.getAttribute("id");
			System.out.println(++i+". "+ StateName);
		}
		//clickOnStates("california");
		//clickOnStates("new-york");
		clickOnStates("maryland");
		driver.close();
	}
	
	public static void clickOnStates(String State) throws InterruptedException {
		for(WebElement ele:States) {
			action.moveToElement(ele).build().perform();
			Thread.sleep(100);
			String StateName=ele.getAttribute("id");
			if(StateName.equalsIgnoreCase(State)) {
				ele.click();
				break;
			}
		}
	}

}
