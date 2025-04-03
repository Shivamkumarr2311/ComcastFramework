package practice.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Mock {

	@Test

	public void Practice() throws Throwable {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[@class='desktop-searchBar']")).sendKeys("shoes", Keys.ENTER);
		String priceshoes = driver.findElement(By.xpath("//h4[text()='Boys Colourblocked Sneakers']/../div/span"))
				.getText();
		System.out.println(priceshoes);

		driver.findElement(By.xpath("//input[@class='desktop-searchBar']")).sendKeys("shirts", Keys.ENTER);
		String priceshirts = driver.findElement(By.xpath("//h4[text()='Women Solid Cotton Shirt']/../div/span"))
				.getText();
		System.out.println(priceshirts);

		driver.findElement(By.xpath("//input[@class='desktop-searchBar']")).sendKeys("jeans", Keys.ENTER);
		String pricejeans = driver.findElement(By.xpath("//h4[text()='Men Light Fade Skater Jeans']/../div/span"))
				.getText();
		System.out.println(pricejeans);

		FileInputStream fis = new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);

		wb.getSheet("").getRow(0).createCell(0).setCellValue(priceshoes);
		wb.getSheet("").getRow(0).createCell(0).setCellValue(priceshirts);
		wb.getSheet("").getRow(0).createCell(0).setCellValue(pricejeans);

		FileOutputStream fos = new FileOutputStream("./testData/testdata.xlsx");
		wb.write(fos);
		wb.close();

	}
}
