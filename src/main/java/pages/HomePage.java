package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//button[@ng-click = 'customer()']")
  private WebElement customerLoginButton;

  @FindBy(xpath = "//button[@ng-click = 'manager()']")
  private WebElement managerLoginButton;



  public HomePage open() {
    driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

    return this;
  }

  public String getTitle() {
    return driver.findElement(By.tagName("h1")).getText();
  }

  public CustomerLoginPage customerLoginButtonClick() {
    customerLoginButton.click();

    return new CustomerLoginPage(driver);
  }
}
