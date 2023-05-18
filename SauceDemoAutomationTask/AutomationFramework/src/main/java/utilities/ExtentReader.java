package utilities;

import org.apache.logging.log4j.core.config.properties.PropertiesConfiguration;

import java.io.*;
import java.util.Properties;

public class ExtentReader {

    private static Properties properties;
    private static String path = "./src/test/resources/extent.properties";

    public ExtentReader() {
        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream(path);
            properties.load(ip);
            ip.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateExtentPropertiesValue(String key, String value) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(path);
            properties.setProperty(key, value);
            properties.store(out, null);
            out.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {

        }
    }

}
