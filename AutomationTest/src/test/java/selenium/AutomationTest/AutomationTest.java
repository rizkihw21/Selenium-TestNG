package selenium.AutomationTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationTest {
	
	@Test
	public void login() {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
		driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys("John Doe");
		driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.xpath("//button[@id='btn-login']")).click();
		
		String expectedResult = "Make Appointment";
		String actualResult = driver.findElement(By.xpath("//body/section[@id='appointment']/div[1]/div[1]/div[1]")).getText();
		assertEquals(expectedResult, actualResult);
		
		driver.close();
	}
	
	@Test
	public void failedlogin() {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
		driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys("Test"); //Invalid username
		driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.xpath("//button[@id='btn-login']")).click();
		
		String expectedResult = "Login failed! Please ensure the username and password are valid.";
		String actualResult = driver.findElement(By.xpath("//body/section[@id='login']/div[1]/div[1]/div[1]/p[2]")).getText();
		assertEquals(expectedResult, actualResult);
		
		driver.close();
		driver.quit();
	}
	
}
