package me.abetayev.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import me.abetayev.qa.utils.Utilities;

public class Base {

    WebDriver driver;
    public Properties prop;

    public Base() {
        prop = new Properties();
        File propFile = new File(
                System.getProperty("user.dir") + "\\src\\main\\java\\me\\abetayev\\qa\\config\\config.properties");
        try {
            FileInputStream data = new FileInputStream(propFile);
            prop.load(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver initializeBrowserAndOpenApplication() {

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
