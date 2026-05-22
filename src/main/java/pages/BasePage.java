package pages;

import com.google.inject.Injector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;


public class BasePage {
  protected WebDriver driver;
  protected WaitUtils waitUtils;

  protected Injector injector;



  public BasePage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    this.driver = driver;
    this.waitUtils = waitUtils;
    this.injector = injector;
    PageFactory.initElements(driver, this);
  }

  public void open(String url) {
    driver.get(url);
  }

  protected <T extends BasePage> T navigateTo(Class<T> pageClass) {
    return injector.getInstance(pageClass);
  }
}
