package tests;

import com.google.inject.Inject;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;

import java.util.List;

@Listeners(TestListener.class)
public class ManagerTest {

  @Inject
  public HomePage homePage;

  @Test
  public void testManagerLogin() {
    boolean isCentralBlock = homePage
        .open()
        .clickManagerLoginButton()
        .isCentralBlock();

    Assert.assertTrue(isCentralBlock);
  }

  @Test
  public void testAddCustomerOpen() {
    List<String> addCustomerElements = homePage
        .open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .getAddCustomerFields();
    List<String> elements = List.of("First Name :", "Last Name :", "Post Code :");
    Assert.assertEquals(addCustomerElements, elements);
  }

  @Test
  public void testAlertEmptyFirstField() {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .clickSubmitOperationButton()
        .getValidationMessageInput();

    Assert.assertFalse(message.isEmpty());
  }

  @Test
  public void testAlertEmptySecondField() {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .sendFNameField("TestName")
        .clickSubmitOperationButton()
        .getValidationMessageInput();

    Assert.assertFalse(message.isEmpty());
  }

  @Test()
  @Parameters({"fname", "lname", "pcode"})
  public void testAddCustomer(String fname, String lname, String pcode) {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .sendFNameField(fname)
        .sendLNameField(lname)
        .sendPCodeField(pcode)
        .clickSubmitOperationButton()
        .getAlertMessage();

    Assert.assertTrue(message.contains("Customer added successfully with customer id :"));
  }

  @Test(dependsOnMethods = "testAddCustomer")
  public void testOpenAddedAccount() {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickOpenAccountButton()
        .selectCustomer("Test Name" + " " + "TestName2")
        .selectCurrency("Dollar")
        .getAlertMessage();

    Assert.assertTrue(message.contains("Account created successfully with account Number :"));
  }

  @BeforeMethod(onlyForGroups = "requiresUser")
  @Parameters({"fname", "lname", "pcode"})
  public void createTestUser(String fname, String lname, String pcode) {
    homePage.open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .sendFNameField(fname)
        .sendLNameField(lname)
        .sendPCodeField(pcode)
        .clickSubmitOperationButton()
        .acceptAlert();
  }

  @Test(groups = "requiresUser")
  @Parameters({"fname", "lname", "pcode"})
  public void testOpenAddedAccount(String fname, String lname, String pcode) {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickOpenAccountButton()
        .selectCustomer(fname + " " + lname)
        .selectCurrency("Dollar")
        .clickSubmitOperationButton()
        .getAlertMessage();

    Assert.assertTrue(message.contains("Account created successfully with account Number :"));
  }

  @Test
  public void testEmptyFieldAddCustomer() {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickOpenAccountButton()
        .clickSubmitOperationButton()
        .getValidationMessageSelect();

    Assert.assertFalse(message.isEmpty());
  }

  @Test(groups = "requiresUser")
  @Parameters({"fname", "lname", "pcode"})
  public void testSearchAddedCustomer(String fname, String lname, String pcode) {
    List<String> elements = homePage
        .open()
        .clickManagerLoginButton()
        .clickCustomersButton()
        .sendSearchField(fname)
        .getUserElements();

    List<String> initialsUserParams = List.of(fname, lname, pcode);

    Assert.assertEquals(initialsUserParams, elements);
  }
}