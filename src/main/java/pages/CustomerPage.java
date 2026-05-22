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
}
