package pages;

import com.google.inject.Injector;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class ManagerPage extends BasePage{

  public ManagerPage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    super(driver, waitUtils, injector);
  }
}
