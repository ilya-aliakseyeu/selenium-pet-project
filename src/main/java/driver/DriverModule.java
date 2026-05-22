package driver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.HighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverModule extends AbstractModule {
  @Provides
  @Singleton
  public WebDriver provideWebDriver() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();

    HighlightListener listener = new HighlightListener(driver);
    return new EventFiringDecorator(listener).decorate(driver);

  }
}
