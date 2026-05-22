package pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class HomePage extends BasePage {

  private static final String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

  @FindBy(xpath = "//button[@ng-click = 'customer()']")
  private WebElement customerLoginButton;

  @FindBy(xpath = "//button[@ng-click = 'manager()']")
  private WebElement managerLoginButton;

  @Inject
  public HomePage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    super(driver, waitUtils, injector);
  }

  public HomePage open() {
    open(URL);

    return this;
  }

  public CustomerLoginPage customerLoginButtonClick() {
    waitUtils.waitForClickable(customerLoginButton).click();
    return navigateTo(CustomerLoginPage.class);
  }
}
