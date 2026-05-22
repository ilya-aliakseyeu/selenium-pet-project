package pages;


import com.google.inject.Inject;
import com.google.inject.Injector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;


public class CustomerLoginPage extends BasePage {

  @FindBy(name = "userSelect")
  private WebElement userSelectMenu;

  @FindBy(xpath = "//button[@type = 'submit']")
  private WebElement loginButton;

  @Inject
  public CustomerLoginPage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    super(driver, waitUtils, injector);
  }

  public CustomerLoginPage selectCustomer(String option) {
    waitUtils.waitForVisibility(userSelectMenu);
    new Select(userSelectMenu).selectByVisibleText(option);
    return this;
  }

  public CustomerPage clickLoginButton() {
    loginButton.click();
    waitUtils.waitForVisibility(loginButton);
    return navigateTo(CustomerPage.class);
  }
}
