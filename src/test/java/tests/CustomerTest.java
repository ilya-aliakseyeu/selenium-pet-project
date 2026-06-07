package tests;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CustomerLoginPage;
import pages.CustomerPage;
import pages.HomePage;
import listeners.TestListener;

import java.util.Random;


@Listeners(TestListener.class)
public class CustomerTest {

  @Inject
  public HomePage homePage;

  @Inject
  public CustomerPage customerPage;

  @Inject
  public CustomerLoginPage customerLoginPage;

  int amount = new Random().nextInt(1000) + 1;
  int amountNegative = -(new Random().nextInt(1000) + 1);
  int amountNull = 0;

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
  @Parameters({"HPotter"})
  public void testMakeDeposit() {
    int perviosBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickDepositButton()
        .sendAmount(amount)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertTrue(customerPage.isSuccessMessage());
    Assert.assertEquals(amount, perviosBalanceCount);
  }

  @Test
  public void testNegativeDeposit() {
    int newBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickDepositButton()
        .sendAmount(amountNegative)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertFalse(customerPage.isSuccessMessage());
    Assert.assertEquals(newBalanceCount, 0);
  }

  @Test
  public void testNullDeposit() {
    int newBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickDepositButton()
        .sendAmount(amountNull)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertFalse(customerPage.isSuccessMessage());
    Assert.assertEquals(newBalanceCount, 0);
  }

  @Test
  public void testWithdraw() {
    int newBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickWithdrawButton()
        .sendAmount(amount - 1)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertTrue(customerPage.isSuccessMessage());
    Assert.assertEquals(newBalanceCount, 1);
  }

  @Test
  public void testNegativeWithdraw() {
    int newBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickWithdrawButton()
        .sendAmount(amountNegative)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertFalse(customerPage.isSuccessMessage());
    Assert.assertEquals(newBalanceCount, 0);
  }

  @Test
  public void testNullWithdraw() {
    int newBalanceCount = homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickWithdrawButton()
        .sendAmount(amountNull)
        .clickSubmitOperationButton()
        .getBalance();

    Assert.assertFalse(customerPage.isSuccessMessage());
    Assert.assertEquals(newBalanceCount, 0);
  }

  @Test
  public void testLogout() {
    homePage.open()
        .customerLoginButtonClick()
        .selectCustomer("Harry Potter")
        .clickLoginButton()
        .clickLogoutButton();

    Assert.assertTrue(customerLoginPage.isSelectMenuDisplayed());
  }
}