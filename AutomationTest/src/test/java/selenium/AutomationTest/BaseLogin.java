package selenium.AutomationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLogin {
	
	WebDriver driver;
	String username = "John Doe";
	String password = "ThisIsNotAPassword";
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		
		driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
		driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btn-login']")).click();
		
	}
	
	@AfterTest
	public void teraDown() {
		
		driver.close();
	}
}
