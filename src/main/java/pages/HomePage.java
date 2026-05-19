package pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
  @Inject
  private WebDriver driver;

  public HomePage open() {
    driver.get("https://otus.ru");

    return this;
  }

  public String getTitle() {
    return driver.findElement(By.tagName("h1")).getText();
  }
}
