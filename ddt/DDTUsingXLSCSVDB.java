package ddt;

import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class DDTUsingXLSCSVDB {
	WebDriver driver;

	/*
	 * @Test(dataProvider = "dp") public void f(Integer n, String s) {
	 * System.out.println("First para is " + n + "\nAnd second parameter is " + s);
	 * }
	 */
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviderFunctions.class)
	public void loginToNT(String user, String pass, String role) {
		driver.get("https://nichethyself.com/tourism/home.html");
		WebElement userName = driver.findElement(By.name("username"));
		userName.sendKeys(user);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pass);
		password.submit();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, new Object[] { 3, "c" },
				new Object[] { 4, "d" }, };
	}

	

}
