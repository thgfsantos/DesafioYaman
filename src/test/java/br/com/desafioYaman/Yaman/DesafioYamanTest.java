package br.com.desafioYaman.Yaman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DesafioYamanTest {
	private WebDriver driver;
	private HomeSearchPage home;
	private SearchResultPage hresult;
	private Helper help;

	@Before
	public void setUp() throws Exception {
		this.help = new Helper();
		System.setProperty("webdriver.gecko.driver", help.getValueConfigProperties("PATH_GECKO_DRIVER"));
		driver = new FirefoxDriver();
		// driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.get(help.getValueConfigProperties("URL_PATH"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.home = new HomeSearchPage(driver);
		this.hresult = new SearchResultPage(driver);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		Object verificationErrors = "";
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
/*
	@Test
	public void testSearchAndPrintFiveProductAndPrice() {
		List<String> listProductPrice = new ArrayList<String>();
		hresult = home.doSearch("motorola g3");// parameter for to do a search in webpage.
		listProductPrice = hresult.showProductNameAndPrice();// return list with 5 items of the product and price
		assertFalse(listProductPrice.isEmpty());
		assertEquals(5, listProductPrice.size());

		// Print product name and price from ListProductPrice
		for (int i = 0; i < listProductPrice.size(); i++) {
			System.out.println(listProductPrice.get(i).toString());
		}
	}
*/
	@Test
	public void testPrintItemNextPage() {
		hresult = home.doSearch("motorola g3");// parameter argument for to do search in webpage
		hresult.doGetNextPage();// to click page number 2.
		boolean isPrinted = hresult.print("/tmp/", "Print_yaman_test"); // parameter for to save screenshot in a folder
																		// with name and extension default PNG
		assertTrue(isPrinted);
	}
}
