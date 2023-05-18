package pages;

import DriverFactory.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utilities.ConfigReader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class BaseFactory extends WebDriverFactory {

    public static void waitUntilClickable(WebDriver driver, WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilClickable(WebDriver driver, By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilVisible(WebDriver driver, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilAllElementsVisible(WebDriver driver, List<WebElement> element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void sendKeys(WebDriver driver, WebElement element, CharSequence value, boolean clickElement) {
        waitUntilVisible(driver, element);
        if (clickElement) {
            element.click();
        }
        element.clear();
        if (value != null) {
            element.sendKeys(value);
        }
    }

    public static void customDropDownSelect(WebElement element, WebElement ele) {
        waitAndClick(driver, element);
        waitAndClick(driver, ele);
    }

    public static void customDropDownSelect(WebElement element, By ele) {
        waitAndClick(driver, element);
        waitAndClick(driver, ele);
    }

    public void selectValueInSelector(WebDriver driver, WebElement element, String value) {
        waitUntilVisible(driver, element);
        Select select = new Select(element);
        if (value != null) {
            select.selectByVisibleText(value);
        }
    }

    public static void waitAndClick(WebDriver driver, WebElement element) {
        waitUntilClickable(driver, element);
        element.click();
    }

    public static void waitAndClick(WebDriver driver, By element) {
        waitUntilClickable(driver, element);
        WebElement ele=driver.findElement(element);
        ele.click();
    }

    public void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hardWait(WebDriver driver, int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomAccessionNo(){
        return "AT"+((new Random().nextInt(99999999)));
    }
}
