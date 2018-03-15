package br.com.desafioYaman.Yaman;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchResultPage {
	private WebDriver driver;
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
	}

	public SearchResultPage doGetNextPage() {
		driver.findElement(By.linkText("2")).click();
		return new SearchResultPage(driver);
	}

	public List<String> showProductNameAndPrice() {
		
		return searchResultElement(driver);
	}
	
 private List<String> searchResultElement(WebDriver driver) {
	 List<String> listProductPrice = new ArrayList<String>(4);
	 List<WebElement> allElements = driver.findElements(By.xpath("//ol[@id='searchResults']"));

		for (int i = 1; i <= 5; i++) {
			String productName = allElements.get(0).findElement(By.xpath("//li[" + i + "]/div/div[2]/div/h2")).getText();
			String productPrice = allElements.get(0).findElement(By.xpath("//li[" + i + "]/div/div[2]/div/div/div/span")).getText()+
					allElements.get(0).findElement(By.xpath("//li[" + i + "]/div/div[2]/div/div/div/span[2]")).getText();
			
			 listProductPrice.add(i-1, "Product Name: "+productName +" ==> "+" Price: "+productPrice);
		}	
		return listProductPrice;
 }
	
	public boolean print(String destination, String namePicture) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yPosition = searchElementToPrint(driver).getLocation().getY();
		js.executeScript("window.scroll (0, " + yPosition + ") ");
		
		return takeScreenShot(driver, destination,namePicture);
	}
	
	private WebElement searchElementToPrint(WebDriver driver) {
		WebDriverWait wait = (new WebDriverWait(driver, 20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol[@id='searchResults']")));
		
		List<WebElement> allElements = driver.findElements(By.xpath("//ol[@id='searchResults']"));
		//List<WebElement> allElementsLi = allElements.get(0).findElements(By.tagName("li"));
		//int totalElementLi = allElementsLi.size() -1;
		WebElement element = allElements.get(0).findElement(By.xpath("//li[52]/div"));
		return element;
	}
	
	private boolean takeScreenShot(WebDriver driver, String destination, String namePicture) {
		try {
			File scrFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile1, new File(destination+namePicture+".png"));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
