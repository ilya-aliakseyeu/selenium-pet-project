package tests;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import listeners.TestListener;


@Listeners(TestListener.class)
public class CustomerTest {

  @Inject
  public HomePage homePage;

  @DataProvider(name = "customer")
  public Object[][] customer() {
    return new Object[][] {
        {"Harry Potter"},
        {"Ron Weasly"},
        {"Hermoine Granger"}
    };
  }

  @Test(dataProvider = "customer")
  public void testOpenCustomerLoginPage(String customerName) {
    String actualName = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer(customerName)
        .clickLoginButton()
        .getUserName();

    Assert.assertEquals(actualName, "Welcome " + customerName + " !!");
  }

  @DataProvider(name = "customerNum")
  public Object[][] customerNum() {
    return new Object[][] {
        {"Harry Potter", 1005},
        {"Ron Weasly", 1007},
        {"Hermoine Granger", 1003}
    };
  }

  @Test(dataProvider = "customerNum")
  public void testChangeAccountNumber(String customerName, int accountNumber) {
    int customerNum = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer(customerName)
        .clickLoginButton()
        .selectAccountNumber(accountNumber)
        .getSelectedValue();

    Assert.assertEquals(customerNum, accountNumber);
  }


  @Test
  public void testMakeDeposit() {
    int perviosBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .getBalance();

    int changedBalance = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .sendAmount("1000")
        .clickDepositButton()
        .getBalance();

    Assert.assertEquals(perviosBalanceCount, changedBalance - perviosBalanceCount);




  }
}