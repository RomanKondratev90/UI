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
            case headless:
                chromeOptions.addArguments("--headless");
                break;
            case kiosk:
                chromeOptions.addArguments("--kiosk");
                break;
            case fullscreen:
                chromeOptions.addArguments("--start-fullscreen");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser mode: " + mode);
        }
        return chromeOptions;
    }
}
