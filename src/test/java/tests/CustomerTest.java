package tests;

import com.google.inject.Inject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import listeners.TestListener;


@Listeners(TestListener.class)
public class CustomerTest {

    @Inject
    public HomePage homePage;

    @Test
    public void testOpenCustomerLoginPage() {
        String actualName = homePage.open()
                .customerLoginButtonClick()
                .selectCustomer("Harry Potter")
                .clickLoginButton()
                .getUserName();

        Assert.assertEquals(actualName, "Welcome Harry Potter !!");
    }

    @Test
    public void testChangeAccountNumber() {
        int customerNum = homePage.open()
                .customerLoginButtonClick()
                .selectCustomer("Harry Potter")
                .clickLoginButton()
                .selectAccountNumber(1005)
                .getSelectedValue();

        Assert.assertEquals(customerNum, 1005);
    }
}