package pages;

import org.apache.logging.log4j.simple.SimpleLogger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.ExcelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BaseFactory {

    private WebDriver driver;

    private ExcelReader excelReader = new ExcelReader();
    private ConfigReader configReader = new ConfigReader();

    private static List<String> loginDetails = new ArrayList<String>();

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='login-button']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) throws IOException, InvalidFormatException {
        this.driver = driver;
        loginDetails = excelReader.getUserDetails(configReader.getUserDetails());
        PageFactory.initElements(driver, this);
    }

    public void navigateURL() throws IOException {
        driver.navigate().to(configReader.getUrlDetails());
    }

    public void enterUserName() {
        waitUntilVisible(driver, userNameField);
        sendKeys(driver, userNameField, loginDetails.get(0), true);
    }

    public void enterPassword() {
        waitUntilVisible(driver, passwordField);
        sendKeys(driver, passwordField, loginDetails.get(1), true);
    }

    public void enterUserNameAndPassword() {
        enterUserName();
        enterPassword();
    }

    public void clickLoginButton() {
        waitAndClick(driver, loginButton);
    }

}
