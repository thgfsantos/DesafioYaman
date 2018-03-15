package br.com.desafioYaman.Yaman;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeSearchPage {
	private WebDriver driver;

	public HomeSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public SearchResultPage doSearch(String nameProduct) {

		driver.findElement(By.name("as_word")).click();
		driver.findElement(By.name("as_word")).clear();
		driver.findElement(By.name("as_word")).sendKeys(nameProduct);
		driver.findElement(By.cssSelector("button.nav-search-btn")).click();

		return new SearchResultPage(driver);
	}
}
