package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

  private final WebDriver driver;

  public HighlightListener(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  public void beforeClick(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'", element);
  }

  @Override
  public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].style.border='3px solid red'", element);
  }
}
