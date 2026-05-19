package common;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import driver.DriverModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  @Inject
  protected WebDriver driver;

  public WebDriver getDriver() {
    return driver;
  }

  @BeforeMethod
  public void setUp() {
    Injector injector = Guice.createInjector(new DriverModule());
    injector.injectMembers(this);
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
