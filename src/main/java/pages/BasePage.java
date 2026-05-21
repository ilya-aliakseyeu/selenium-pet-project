package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;


public class BasePage {
  protected WebDriver driver;
  protected WaitUtils waitUtils;


  @Inject
  public BasePage(WebDriver driver, WaitUtils waitUtils) {
    this.driver = driver;
    this.waitUtils = waitUtils;
    PageFactory.initElements(driver, this);
  }

  public void open(String url) {
    driver.get(url);
  }
}
