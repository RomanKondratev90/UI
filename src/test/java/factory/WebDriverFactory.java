package factory;

import exception.BrowserNotFoundException;
import factory.impl.iWebDriverSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class WebDriverFactory {
    private String browserName = System.getProperty("browser","chrome");
    private static final int ImplicitWait = 10;

    public WebDriver getDriver(iWebDriverSettings settings) {
        WebDriver driver;
        switch (browserName) {
            case "chrome": {
                driver = new ChromeDriver((ChromeOptions) settings.setting());
                break;
            }
            default: {
                throw new BrowserNotFoundException(browserName);
            }
        }
        //Установка неявного ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ImplicitWait));
        return driver;
    }
}




