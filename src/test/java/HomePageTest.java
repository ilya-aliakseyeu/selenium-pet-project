import com.google.inject.Inject;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerLoginPage;
import pages.HomePage;

public class HomePageTest extends BaseTest {

  @Inject
  private HomePage homePage;

  @Inject
  private CustomerLoginPage customerLoginPage;


  @Test
  public void testOpenCustomerLoginPage() {
    boolean isUserSelect = homePage.open().customerLoginButtonClick().userSelectIsDisplayed();

    Assert.assertTrue(isUserSelect);
  }
}
