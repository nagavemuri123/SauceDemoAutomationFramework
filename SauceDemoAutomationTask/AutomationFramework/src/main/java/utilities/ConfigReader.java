package utilities;

import java.io.*;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private String path = "./src/test/resources/config.properties";

    public Properties init_Prop() {

        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream(path);
            properties.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public String getBrowserConfiguration() {
        String browser = properties.getProperty("browser");
        return browser;
    }

    public String getUrlDetails() throws IOException {
        String URL = properties.getProperty("url");
        return URL;
    }

    public Integer getPageLoadTime() throws IOException {
        int time = Integer.parseInt(properties.getProperty("pageLoadTime"));
        return time;
    }

    public String getUserDetails() {
        String details = properties.getProperty("userDetails");
        return details;
    }
}
