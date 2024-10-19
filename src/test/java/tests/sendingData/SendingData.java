package tests.sendingData;

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

public class SendingData {
        private String baseUrl = System.getProperty("base.url");
        private WebDriver driver;
        private WebDriverFactory factory;
        private static final Logger logger = LogManager.getLogger(SendingData.class);

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
        @DisplayName("Проверка отправки формы")
        public void shouldSubmitForm() {
            ChromeSettings settings = new ChromeSettings(BrowserMode.FULLSCREEN);
            driver = factory.getDriver(settings);

            driver.get(baseUrl);
            driver.findElement(By.id("name")).sendKeys("Роман");
            driver.findElement(By.id("email")).sendKeys("1@mail.ru");
            logger.info("В поле ввода введено Роман и 1@mail.ru");
            driver.findElement(By.cssSelector("[type='submit']")).click();

            WebElement result = driver.findElement(By.id("messageBox"));
            String text = result.getText();
            Assertions.assertEquals("Форма отправлена с именем: Роман и email: 1@mail.ru", text);
            logger.info("Форма отправлена с именем: Роман и email: 1@mail.ru");
        }
}

