package driver;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import listeners.HighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.net.URI;
import java.util.Map;

public class DriverModule extends AbstractModule {

  @Provides
  @Singleton
  public WebDriver provideWebDriver() throws Exception {
    ChromeOptions options = new ChromeOptions();
    options.setBrowserVersion("128.0");
    options.setCapability("selenoid:options", Map.of(
            "enableVNC", true,
            "enableVideo", false));

    WebDriver driver = new RemoteWebDriver(
            URI.create("http://localhost:4444/wd/hub").toURL(),
            options
    );

    HighlightListener listener = new HighlightListener(driver);
    return new EventFiringDecorator(listener).decorate(driver);
  }
}