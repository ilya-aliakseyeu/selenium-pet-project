package utils;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    @Inject
    public WebDriver driver;

    public WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
}
}
