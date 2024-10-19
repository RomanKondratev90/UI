package tests.inputFields;

import factory.WebDriverFactory;
import factory.impl.BrowserMode;
import factory.impl.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FieldValidation {
        private String baseUrl = System.getProperty("base.url");
        private WebDriver driver;
        private WebDriverFactory factory;
        private static final Logger logger = LogManager.getLogger(FieldValidation.class);

        @BeforeAll
        public static void init() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        public void setUp() {
            factory = new WebDriverFactory();
        }

        @AfterEach
        public void quitBrowser() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
        @DisplayName("Проверка текста в поле ввода")
        public void validateFashionWindowContent() {
            ChromeSettings settings = new ChromeSettings(BrowserMode.HEADLESS);
            driver = factory.getDriver(settings);

            driver.get(baseUrl);
            WebElement element = driver.findElement(By.id("textInput"));
            element.sendKeys("ОТУС");
            logger.info("Введен текст \"ОТУС\"");
            Assertions.assertEquals("ОТУС", element.getAttribute("value"));
            logger.info("Текст в поле: \"ОТУС\"");
        }
}


