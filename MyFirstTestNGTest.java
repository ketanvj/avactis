package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.List;

//import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class MyFirstTestNGTest {
	WebDriver driver;

	@Test (description = "This test is to test the login functionality", groups = "Regression", expectedExceptions = NoSuchElementException.class)
	public void loginToNicheThyselfTourism() throws InterruptedException {
		driver.get("https://nichethyself.com/tourism/home.html");
		WebElement userName = driver.findElement(By.name("username"));
		List<WebElement> allElements = driver.findElements(By.tagName("input"));
		userName.sendKeys("stc123");
		WebElement password = driver.findElement(By.name("password1"));
		password.sendKeys("12345");
		password.submit();
		Thread.sleep(3000);
		String expectedPageTitleAfterLogin = "My account";
		String actualPageTitleAfterLogin = driver.getTitle();
		assertEquals(actualPageTitleAfterLogin, expectedPageTitleAfterLogin);		
		assertEquals(actualPageTitleAfterLogin, expectedPageTitleAfterLogin, "Test Case Failed");
	}
	
	//@Test (groups = {"Smoke", "RegressionCH"}, dependsOnMethods = "loginToNicheThyselfTourism")
	public void googleSearchTest() {
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium Training");
	}

	
	//@Test (groups = {"Smoke"})
	public void yahooSearchTest() {
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium Training");
	}
	
	
	//@Test (groups = {"RegressionFF"})
	public void bingSearchTest() {
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium Training");
	}
	
	
	//@Test (groups = {"RegressionMac"}, dependsOnGroups = "Smoke")
	public void chatGPTSearchTest() {
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium Training");
	}
	//Test chatGPT will execute only if all the smoke test exectes successfully. If even a single smoke test fails, chatGPT will be skipped.
	
	
	@BeforeMethod (alwaysRun = true)
	public void beforeMethod() {
		//driver = new ChromeDriver();// driver is a local variable.
		driver = new FirefoxDriver();// driver is a local variable.
		driver.manage().window().maximize();
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}

}
