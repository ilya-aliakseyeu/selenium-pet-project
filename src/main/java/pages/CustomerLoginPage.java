package pages;


import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;


@Listeners(TestListener.class)
public class CustomerLoginPage {

  public CustomerLoginPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(name = "userSelect")
  private WebElement userSelectMenu;

  public boolean userSelectIsDisplayed() {
    return userSelectMenu.isDisplayed();
  }
}
