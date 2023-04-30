package Base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.LogUtils.logInfo;
public final class Config {
    public static final String BROWSER_NAME;

    static {
        BROWSER_NAME = getBrowserName();
    }
    private static String getBrowserName() {
        String browser = System.getProperties().getProperty("browser");
        if(browser == null) {
            browser = loadProperties().getProperty("browser");
            logInfo("No browser option is set, please set -Dbrowser. Now default environment 'CHROME' will be set");
        }
        return browser;
    }

    private static Properties loadProperties() {
        String configFileName = "config.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(configFileName);
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException ioe) {
            throw new IllegalStateException("Exception on loading {" + configFileName + "} conf file from classpath", ioe);
        }
        return properties;
    }
}