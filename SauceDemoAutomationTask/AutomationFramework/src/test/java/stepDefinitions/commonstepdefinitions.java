package stepDefinitions;

import DriverFactory.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProductsPage;
import pages.LoginPage;
import utilities.ExcelReader;

import java.io.IOException;
import java.util.HashMap;

public class commonstepdefinitions {
    private WebDriver driver = WebDriverFactory.getDriver();
    public ProductsPage homePage = new ProductsPage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    private ExcelReader reader = new ExcelReader();
    private HashMap<String, Double> highestValue = new HashMap<>();

    public commonstepdefinitions() throws IOException, InvalidFormatException {
    }

    @Given("User navigates to Swag Labs Url")
    public void userNavigatesToSwagLabsUrl() throws IOException {
        try {
            loginPage.navigateURL();
        } catch (Exception e) {
            Assert.fail("Failed to Navigate URL" + e.getMessage());
        }
    }

    @When("User enters username and password in Swag login page")
    public void loginToCareDxApplication() {
        try {
            loginPage.enterUserNameAndPassword();
        } catch (Exception e) {
            Assert.fail("Failed to Enter UserName and Password details" + e.getMessage());
        }

    }

    @And("Click on Login button")
    public void clickOnLoginButton() {
        try {
            loginPage.clickLoginButton();
        } catch (Exception e) {
            Assert.fail("Failed to click on Login button" + e.getMessage());
        }
    }

    @Then("Verify Swag Labs Home page is displayed")
    public void verifySwagLabsHomePageIsDisplayed() throws IOException {
        try {
            homePage.verifyHomePage();
        } catch (Exception e) {
            Assert.fail("Failed to verify home page :" + e.getMessage());
        }
    }

    @And("Verify products listed in Home page")
    public void verifyProductsListedInHomePage() {
        try {
            homePage.verifyProductsListed();
        } catch (Exception e) {
            Assert.fail("No products available in products page :" + e.getMessage());
        }
    }

    @And("Select the highest price product in Home page")
    public void selectTheHighestPriceProductInHomePage() {
        try {
            Double Value = homePage.getHighestPriceProduct();
            highestValue.put("HighestValue", Value);
        } catch (Exception e) {
            Assert.fail("Failed to fetch the highest value from products list" + e.getMessage());
        }
    }

    @Then("Add the selected highest price product to the cart")
    public void addTheSelectedHighestPriceProductToTheCart() {
        try {
            homePage.addToCartHighestProduct(highestValue.get("HighestValue").toString());
        } catch (Exception e) {
            Assert.fail("No products available in products page :" + e.getMessage());
        }
    }

    @Then("Verify product is added to shopping cart")
    public void verifyProductIsAddedToShoppingCart() {
        try {
            homePage.verifyProductAdded(highestValue.get("HighestValue").toString());
        } catch (Exception e) {
            Assert.fail("No products available in products page :" + e.getMessage());
        }
    }
}

