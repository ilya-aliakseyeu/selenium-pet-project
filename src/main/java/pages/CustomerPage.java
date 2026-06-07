package pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class CustomerPage extends BasePage {

  @FindBy(xpath = "//strong[contains(text(), 'Welcome')]")
  private WebElement headerText;

  @FindBy(id = "accountSelect")
  private WebElement accountSelectMenu;

  @FindBy(xpath = "//div[@class =  'center']//button[normalize-space(text()) = 'Deposit']")
  private WebElement depositButton;

  @FindBy(xpath = "//button[@type = 'submit']")
  private WebElement submitOperationButton;

  @FindBy(xpath = "//input[@placeholder = 'amount']")
  private WebElement amountField;

  @FindBy(xpath = "(//div[@class = 'center']//strong[@class = 'ng-binding'])[2]")
  private WebElement balanceField;

  @FindBy(xpath = "//*[text() = 'Deposit Successful']")
  private WebElement successMessage;

  @FindBy(xpath = "//div[@class =  'center']//button[normalize-space(text()) = 'Withdrawl']")
  private WebElement withdrawButton;

  @FindBy(xpath = "//button[@ng-click = 'byebye()']")
  private WebElement logoutButton;

  @FindBy(id = "userSelect")
  private WebElement userSelectMenu;

  @Inject
  public CustomerPage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    super(driver, waitUtils, injector);
  }

  public String getUserName() {
    return waitUtils.waitForVisibility(headerText).getText();
  }

  public CustomerPage selectAccountNumber(int number) {
    waitUtils.waitForVisibility(accountSelectMenu);
    new Select(accountSelectMenu).selectByValue("number:" + number);
    return this;
  }

  public int getSelectedValue() {
    String value = new Select(accountSelectMenu)
        .getFirstSelectedOption()
        .getAttribute("value");
    return Integer.parseInt(value.replace("number:", ""));
  }

  public CustomerPage clickDepositButton() {
    depositButton.click();
    waitUtils.waitForVisibility(amountField);
    return this;
  }

  public CustomerPage clickSubmitOperationButton() {
    submitOperationButton.click();
    return this;
  }

  public CustomerPage sendAmount(int amount) {
    amountField.sendKeys(String.valueOf(amount));
    return this;
  }

  public int getBalance() {
    waitUtils.waitForVisibility(balanceField);
    String balanceString = balanceField.getText();
    return Integer.parseInt(balanceString);
  }


  public boolean isSuccessMessage() {
    try {
      waitUtils.waitForVisibility(successMessage);
      return successMessage.isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

  public CustomerPage clickWithdrawButton() {
    withdrawButton.click();
    waitUtils.waitForVisibility(amountField);
    return this;
  }

  public CustomerLoginPage clickLogoutButton() {
    logoutButton.click();
    waitUtils.waitForVisibility(userSelectMenu);

    return navigateTo(CustomerLoginPage.class);
  }
}
