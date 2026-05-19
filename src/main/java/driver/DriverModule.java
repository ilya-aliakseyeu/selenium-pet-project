package driver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverModule extends AbstractModule {
    @Provides
    @Singleton
    public WebDriver provideWebDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
