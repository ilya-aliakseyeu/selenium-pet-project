package listeners;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import driver.DriverModule;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestListener implements IInvokedMethodListener {

  @Inject
  private WebDriver driver;

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    Injector injector = Guice.createInjector(new DriverModule());
    injector.injectMembers(testResult.getInstance());
    injector.injectMembers(this);
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (driver != null) {
      driver.quit();
    }
  }
}
