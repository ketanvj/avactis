package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
//This is the change I am doing?
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class HandlingMultipleElements {
	WebDriver driver;

	@Test
	public void testAllGoogleLinks() {
		//1. Get all the links in a list
//		WebElement myElement = driver.findElement(By.tagName("a"));
		List<WebElement> allGoogleLinks = driver.findElements(By.tagName("a"));
//		driver.findElements(By.xpath("//a"));
//		driver.findElements(By.cssSelector("a"));
//		driver.findElements(By.xpath("//a[@href]"));
		//2. In a for loop print one by one, 
		// link's visible text and href attribute value
		
		//Enhanced for loop, for each loop
		for (WebElement oneLink : allGoogleLinks) {
			System.out.println(oneLink.getText() + " - " + oneLink.getAttribute("href"));
		}
		
	}
	
	

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		// WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
	}

	@AfterMethod
	public void afterMethod() {
	}

}
