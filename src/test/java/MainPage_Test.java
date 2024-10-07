import factory.WebDriverFactory;
import factory.impl.BrowserMode;
import factory.impl.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage_Test {

   private String baseUrl = System.getProperty("base.url");
   private WebDriver driver;
   private WebDriverFactory factory;

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
   @DisplayName("Проверка введеного текста в поле ввода")
   public void task1(){

      ChromeSettings settings = new ChromeSettings(BrowserMode.headless);
      driver = factory.getDriver(settings);

      driver.get(baseUrl);
      WebElement element = driver.findElement(By.id("textInput"));
      element.sendKeys("ОТУС");
      Assertions.assertEquals("ОТУС", element.getAttribute("value"));

   }
   @Test
   @DisplayName("Проверка модального окна")
   public void task2(){

      ChromeSettings settings = new ChromeSettings(BrowserMode.kiosk);
      driver = factory.getDriver(settings);

      driver.get(baseUrl);
      driver.findElement(By.id("openModalBtn")).click();
      WebElement element = driver.findElement(By.xpath("//div[@class='modal-content']//h2"));
      String text = element.getText();
      Assertions.assertEquals("Это модальное окно", text);
   }

   @Test
   @DisplayName("Проверка отправки данных")
   public void task3(){

      ChromeSettings settings = new ChromeSettings(BrowserMode.fullscreen);
      driver = factory.getDriver(settings);

      driver.get(baseUrl);
      driver.findElement(By.id("name")).sendKeys("Роман");
      driver.findElement(By.id("email")).sendKeys("1@mail.ru");
      driver.findElement(By.cssSelector("[type='submit']")).click();
      WebElement result = driver.findElement(By.id("messageBox"));
      String text = result.getText();
      Assertions.assertEquals("Форма отправлена с именем: Роман и email: 1@mail.ru", text);
   }
}


