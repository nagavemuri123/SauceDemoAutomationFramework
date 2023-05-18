package stepDefinitions;

import DriverFactory.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utilities.ConfigReader;
import utilities.ExtentReader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ApplicationHooks {

    private WebDriverFactory driverFactory;
    public static WebDriver driver;
    private ConfigReader configReader;
    private ExtentReader extentReader;
    public static Scenario scenario;
    Properties properties;


    @Before(order = 0)
    public void intialization(Scenario scenario) throws IOException {
        configReader = new ConfigReader();
        extentReader = new ExtentReader();
        properties = configReader.init_Prop();
        driverFactory = new WebDriverFactory();
        extentReader.updateExtentPropertiesValue("basefolder.name", "Reports/reports-" +dateFormat()+ "/reports");
        deleteFiles("Reports");
        deleteFiles("Screenshots");
        this.scenario = scenario;
    }

    @Before(order = 1)
    public void launchBrowser() throws IOException {
        String browserName = configReader.getBrowserConfiguration();
        driver = driverFactory.init_driver(browserName);
    }


//    @Before(order = 2)
//    public void navigateURL() throws IOException {
//        String url = configReader.getUrlDetails();
//        driver = driverFactory.navigateURL(url);
//    }


    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 2)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }

    @After(order = 1)
    public void saveScreenshot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
            Date date = new Date();
            String date1 = dateFormat.format(date);
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "jpg", new File(".\\Screenshots\\" + screenshotName + "-" + date1 + ".jpg"));
        }
    }

    public void deleteFiles(String Directory) {
        long numDays = 1;
        String dir = ".\\" + Directory;
        File directory = new File(dir);
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                long diff = new Date().getTime() - file.lastModified();
                long cutoff = (numDays * (24 * 60 * 60 * 1000));
                if (diff > cutoff) {
                    try {
                        FileUtils.forceDelete(file);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public String scenarioName() {
        String scenarioName = scenario.getName();
        return scenarioName;
    }

    public String dateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
       String Date= dateFormat.format(date);
       return Date;
    }
}
