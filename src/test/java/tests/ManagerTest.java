package tests;

import com.google.inject.Inject;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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

  @Test
  public void testAddCustomer() {
    String message = homePage
        .open()
        .clickManagerLoginButton()
        .clickAddCustomerButton()
        .sendFNameField("TestName")
        .sendLNameField("TestLName")
        .sendPCodeField("TestPCode")
        .clickSubmitOperationButton()
        .getAlertMessage();

    Assert.assertTrue(message.contains("Customer added successfully with customer id :"));
  }
}
