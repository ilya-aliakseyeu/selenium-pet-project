package tests;

import com.google.inject.Inject;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners(TestListener.class)
public class ManagerTest {

  @Inject
  public HomePage homePage;

  @Test
  public void testManagerLogin() {
    boolean isCentralBlock = homePage.open().clickManagerLoginButton()
  }
}
