package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProductsPage extends BaseFactory {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='app_logo' and text()='Swag Labs']")
    private WebElement homePage;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//div[@class='shopping_cart_container']")
    private WebElement shoppingCartImage;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyHomePage() throws IOException {
        waitUntilVisible(driver, homePage);
    }

    public void verifyProductsListed() throws IOException {
        waitUntilAllElementsVisible(driver, productPrices);
    }

    public Double getHighestPriceProduct() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productPrices) {
            String value = element.getText().replace("$", "");
            Double d = Double.parseDouble(value);
            prices.add(d);
        }
        Double maxValue = Collections.max(prices, null);
        return maxValue;
    }

    public void addToCartHighestProduct(String value){
        waitAndClick(driver, By.xpath("(//div[@class='pricebar']/descendant::div[@class='inventory_item_price' and text()='"+value+"']/following::button[text()='Add to cart'])[1]"));
    }

    public void verifyProductAdded(String value){
        waitAndClick(driver,shoppingCartImage);
        waitAndClick(driver,By.xpath("(//div[@class='item_pricebar']/descendant::div[@class='inventory_item_price' and text()='"+value+"']/following::button[text()='Remove'])[1]"));
    }

}
