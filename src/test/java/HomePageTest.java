import com.google.inject.Inject;
import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

  @Inject
  private HomePage homePage;

  @Test
  public void testMainScreen() {
    String title = homePage.open().getTitle();

    Assert.assertEquals(title, "Все ключевые навыки\n" +
        "для роста в IT\n" +
        "от профессионалов");
  }
}
