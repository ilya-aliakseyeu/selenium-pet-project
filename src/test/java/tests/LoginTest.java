package tests;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.HomePage;
import listeners.TestListener;


@Listeners(TestListener.class)
public class LoginTest {

    @Inject
    public HomePage homePage;

    @Inject
    public CustomerPage customerPage;

    @Test
    public void testOpenCustomerLoginPage() {
        String isUserSelect = homePage.open()
                .customerLoginButtonClick()
                .selectCustomer("Harry Potter")
                .clickLoginButton()
                .getUserName();


        Assert.assertEquals(isUserSelect, "Welcome Harry Potter !!");
    }

    @Test(dependsOnMethods = "testOpenCustomerLoginPage")
    public void testChangeAccountNumber() {
      int value = customerPage.selectAccountNumber(1005).


    }
}
