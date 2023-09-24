package selenium.AutomationTest;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class MakeAppointmentTest extends BaseLogin {
	@Test
	public void inputValidAppointment() {
				
		for (int i=0; i<3; i++) {
			WebElement facilitysDropdown = driver.findElement(By.xpath("//select[@id='combo_facility']"));
			Select facility = new Select(facilitysDropdown);
			int dropdownValueCount = driver.findElements(By.tagName("option")).size();
			Random randomFacilitys = new Random();
			int indexFacilitys = randomFacilitys.nextInt(dropdownValueCount);
			facility.selectByIndex(indexFacilitys);
			
			driver.findElement(By.xpath("//input[@id='chk_hospotal_readmission']")).click();
			
			List<WebElement> option = driver.findElements(By.xpath("(//label[@class='radio-inline'])"));
			Random random = new Random();
			int index = random.nextInt(option.size());
			option.get(index).click();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String dateNow = dateFormat.format(date);
			driver.findElement(By.xpath("//input[@id='txt_visit_date']")).click();
			driver.findElement(By.xpath("//input[@id='txt_visit_date']")).sendKeys(dateNow);
			driver.findElement(By.xpath("//textarea[@id='txt_comment']")).sendKeys("Test " + dateNow);
			driver.findElement(By.xpath("//button[@id='btn-book-appointment']")).click();
			
			String expectedResult = "Appointment Confirmation";
			String actualResult = driver.findElement(By.xpath("//body/section[@id='summary']/div[1]/div[1]/div[1]/h2[1]")).getText();
			assertEquals(expectedResult, actualResult);
			
			System.out.println("Facility		: " + driver.findElement(By.xpath("//p[@id='facility']")).getText());
			System.out.println("Healthcare Program	: " + driver.findElement(By.xpath("//p[@id='program']")).getText());
			
			driver.findElement(By.xpath("//a[normalize-space()='Go to Homepage']")).click();
			
		}
	}
}
