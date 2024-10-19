package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModalWindowVerifier {
    private WebDriver driver;

    public ModalWindowVerifier(WebDriver driver) {
        this.driver = driver;
    }

    public static void verifyModalIsClosed(WebDriver driver, String modalId) {
        WebElement modal = driver.findElement(By.id(modalId));
        Assertions.assertFalse(modal.isDisplayed(), "Ошибка: модальное окно открыто");
    }
}

