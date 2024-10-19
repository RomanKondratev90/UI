package tests.modalWindow;

import factory.WebDriverFactory;
import factory.impl.BrowserMode;
import factory.impl.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckingModalWindow {
        private String baseUrl = System.getProperty("base.url");
        private WebDriver driver;
        private WebDriverFactory factory;
        private static final Logger logger = LogManager.getLogger(CheckingModalWindow.class);

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
        @DisplayName("Проверка модального окна")
        public void shouldCheckModalWindow() {
            ChromeSettings settings = new ChromeSettings(BrowserMode.KIOSK);
            driver = factory.getDriver(settings);

            driver.get(baseUrl);
            WebElement modal = driver.findElement(By.id("myModal"));
            Assertions.assertFalse(modal.isDisplayed(), "Модальное окно необходимо закрыть");

            logger.info("Модальное окно закрыто перед тестом");
            driver.findElement(By.id("openModalBtn")).click();
            logger.info("Модальное окно открыто");
            WebElement element = driver.findElement(By.xpath("//div[@class='modal-content']//h2"));
            String text = element.getText();
            Assertions.assertEquals("Это модальное окно", text);
            logger.info("Текст в модальном окне: \"Это модальное окно\"");
        }
}

