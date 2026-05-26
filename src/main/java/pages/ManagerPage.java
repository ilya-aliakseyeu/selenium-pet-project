package pages;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.checkerframework.checker.formatter.qual.Format;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerPage extends BasePage {

  @FindBy(className = "center")
  private WebElement centralBlock;

  @FindBy(xpath = "//div[@class =  'center']//button[normalize-space(text()) = 'Add Customer']")
  private WebElement addCustomerButton;

  @FindBy(xpath = "//div[@class = 'form-group']")
  private List<WebElement> formFields;

  @FindBy(xpath = "//button[@type = 'submit']")
  private WebElement submitOperationButton;

  @FindBy(xpath = "//input[@ng-model = 'fName']")
  private WebElement inputFName;

  @FindBy(xpath = "//input[@ng-model = 'lName']")
  private WebElement inputLName;

  @FindBy(xpath = "//input[@ng-model = 'postCd']")
  private WebElement inputPCode;

  @FindBy(xpath = "//button[@ng-click = 'openAccount()']")
  private WebElement openAccountButton;

  @FindBy(name = "userSelect")
  private WebElement userSelectMenu;

  @FindBy(name = "currency")
  private WebElement currencySelectMenu;

  @FindBy(xpath = "//button[normalize-space(text()) = 'Customers']")
  private WebElement customersButton;

  @FindBy(xpath = "//input[@ng-model = 'searchCustomer']")
  private WebElement searchField;

  @FindBy(className = "ng-binding")
  private List<WebElement> userParams;

  @Inject
  public ManagerPage(WebDriver driver, WaitUtils waitUtils, Injector injector) {
    super(driver, waitUtils, injector);
  }

  public boolean isCentralBlock() {
    waitUtils.waitForVisibility(centralBlock);
    return centralBlock.isDisplayed();
  }

  public ManagerPage clickAddCustomerButton() {
    waitUtils.waitForClickable(addCustomerButton).click();
    waitUtils.waitForClickable(submitOperationButton);
    return this;
  }

  public List<String> getAddCustomerFields() {
    return formFields.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public ManagerPage clickSubmitOperationButton() {
    submitOperationButton.click();
    return this;
  }

  public ManagerPage acceptAlert() {
    driver.switchTo().alert().accept();
    return this;
  }

  public String getValidationMessageInput() {
    return getValidationMessage(inputFName);
  }

  public ManagerPage sendFNameField(String symbols) {
    inputFName.sendKeys(symbols);
    return this;
  }

  public ManagerPage sendLNameField(String symbols) {
    inputLName.sendKeys(symbols);
    return this;
  }

  public ManagerPage sendPCodeField(String symbols) {
    inputPCode.sendKeys(symbols);
    return this;
  }

  public ManagerPage clickOpenAccountButton() {
    waitUtils.waitForClickable(openAccountButton).click();
    waitUtils.waitForVisibility(submitOperationButton);
    return this;
  }

  public ManagerPage selectCustomer(String option) {
    waitUtils.waitForVisibility(userSelectMenu);
    new Select(userSelectMenu).selectByVisibleText(option);
    return this;
  }

  public ManagerPage selectCurrency(String value) {
    waitUtils.waitForVisibility(currencySelectMenu);
    new Select(currencySelectMenu).selectByValue(value);
    return this;
  }

  public String getValidationMessageSelect() {
    return getValidationMessage(userSelectMenu);
  }

  public ManagerPage clickCustomersButton() {
    waitUtils.waitForClickable(customersButton).click();
    waitUtils.waitForVisibility(searchField);
    return this;
  }

  public ManagerPage sendSearchField(String symbols) {
    searchField.sendKeys(symbols);
    return this;
  }

  public List<String> getUserElements() {
    return userParams.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}
