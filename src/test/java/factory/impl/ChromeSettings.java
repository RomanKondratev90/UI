package factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements iWebDriverSettings {

    private BrowserMode mode;

    public ChromeSettings(BrowserMode mode) {
        this.mode = mode;
    }

    @Override
    public AbstractDriverOptions setting() {
        ChromeOptions chromeOptions = new ChromeOptions();
        switch (mode) {
            case HEADLESS:
                chromeOptions.addArguments("--headless");
                break;
            case KIOSK:
                chromeOptions.addArguments("--kiosk");
                break;
            case FULLSCREEN:
                chromeOptions.addArguments("--start-fullscreen");
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый режим браузера: " + mode);
        }
        return chromeOptions;
    }
}
