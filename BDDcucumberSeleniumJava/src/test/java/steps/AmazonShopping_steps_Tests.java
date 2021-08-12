package steps;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonShopping_steps_Tests {
	static WebDriver driver;

	static String name_2 = "";
	static String price_2 = "";
	static String name_1 = "";
	static String price_1 = "";
	

	@Given("I am on Amazon Homepage {string}")
	public void i_am_on_amazon_homepage(String Url) {
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Url);
		

	}

	@When("I am clicking on signin")
	public void i_am_clicking_on_signin() { //
		System.out.println("i_am_clicking_on_signin");

		driver.findElement(By.xpath(
				"//a[@href='https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&']"))
				.click();

	}

	@Then("provide email {string}")
	public void provide_email(String Email) {
		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys(Email);

	}

	@Then("click continue")
	public void click_continue() {
		driver.findElement(By.id("continue")).click();

	}

	@Then("provide password {string}")
	public void provide_password(String pass_word) {
		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys(pass_word);
	}

	@Then("click signin")
	public void click_signin() {
		driver.findElement(By.id("signInSubmit")).click();
	}

	@Given("I am in signed in Homepage")
	public void i_am_in_signed_in_homepage() {

		String actSignedUrl = driver.getCurrentUrl();
		System.out.println(actSignedUrl);
		System.out.println("successfully_navigated_to_Signed_In_page");

	}

	@When("Searching for camera {string}")
	public void searching_for_camera(String camera_name) {

		WebElement searchArea = driver.findElement(By.id("twotabsearchtextbox"));
		searchArea.sendKeys(camera_name);
		driver.findElement(By.id("nav-search-submit-button")).click();
	}

	@Then("Navigate to camera page")
	public void navigate_to_camera_page() {
		String expCamerapageUrl = "https://www.amazon.in/s?k=Fujifilm+Instax+Mini+9+Instant+Camera+%28Ice+Blue%29&ref=nb_sb_noss";
		String actCamerapageUrl = driver.getCurrentUrl();
		Assert.assertEquals(expCamerapageUrl, actCamerapageUrl);
		System.out.println("camera page");

	}

	@Then("Select camera")
	public void select_camera() {
                String s = "Fujifilm Instax Mini 9 Instant Camera (Ice Blue)";
		driver.findElement(By.linkText(s)).click();

	}

	@Given("I am in selected camera page")
	public void i_am_in_selected_camera_page() {

		driver.getTitle();

	}

	@When("Click Add to cart")
	public void click_add_to_cart() {

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		price_1 = driver.findElement(By.id("priceblock_ourprice")).getText();
		price_1 = price_1.replaceAll("₹", "");
		System.out.println(price_1);
		name_1 = driver.findElement(By.id("productTitle")).getText();
		System.out.println(name_1);
		WebElement d = driver.findElement(By.id("add-to-cart-button"));
		d.click();

	}

	@Then("click on refresh and cart symbol")
	public void click_on_refresh_and_cart_symbol() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		driver.navigate().refresh();

		WebElement d = driver.findElement(By.id("nav-cart-count-container"));
		d.click();

	}

	@Given("I am in cart page")
	public void i_am_in_cart_page() {
		System.out.println(driver.getTitle());
	}

	@When("getting its name and price")
	public void getting_its_name_and_price() {

		name_2 = driver.findElement(By.className("a-truncate-cut")).getText();
		System.out.println("Camera Name in cart :" + name_2);
//price_2=driver.findElement(By.xpath("//*[@id='sc-item-C9b9cda37-492c-4466-91c1-d1f1eae2cddd']/div[4]/div/div[2]/p/span")).getText().trim();
		price_2 = driver.findElement(By.xpath("//p[@class='a-spacing-mini']/span")).getText().trim();
		System.out.println("Price in cart : " + price_2);

	}

	@Then("validating the camara name and price")
	public void validating_the_camara_name_and_price() throws InterruptedException {

		
		Assert.assertEquals(price_1, price_2);
		Assert.assertEquals(name_1, name_2);
		System.out.println("Both Selected product name and price are same as the product in cart");
		Thread.sleep(5000);
		driver.quit();

	}
}
