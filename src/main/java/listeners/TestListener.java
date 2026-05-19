package listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import driver.DriverModule;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestListener implements IInvokedMethodListener {
  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    Injector injector = Guice.createInjector(new DriverModule());
    injector.injectMembers(this);
  }

  @Override
  public void afterInvocation() {
    if (driver != null) {
      getDriver.quit();
    }
  }
}
