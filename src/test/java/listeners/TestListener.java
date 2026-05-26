package listeners;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import driver.DriverModule;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class TestListener implements IInvokedMethodListener {

  @Inject
  private WebDriver driver;

  private final Map<Object, Injector> injectors = new HashMap<>(); // - хранит тестовый класс и значение injector Guice = для каждого класса свой ижектор

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    Object instance = testResult.getInstance(); // - testResult - хранит инф. о текущем тесте. Мы получаем Instanse - т.е. конкретный экземпляр тестового класса, который будет выполняться
    Injector injector = injectors.computeIfAbsent(instance, // - computeIfAbsent - проверка: если для данного тетсового класса (instance)в нашей мапе не созданного ижектора - то сохраняем в новую мапу, если есть - возвращаем существующий
        k -> Guice.createInjector(new DriverModule())); // - DriverModule - прописаны опции WebDriver
    injector.injectMembers(instance); // - берем текущий класс и передаем зависимости
    injector.injectMembers(this); // - внедряем зависимости в сам слушатель
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isTestMethod()) { // - проверяем, является ли метод аннотацией с @Test, а не вспомогательным вроде @AfterMethod или @BeforeMethod
      injectors.remove(testResult.getInstance()); // - если это @test - удаляем инжектор для этого экземпляра, тк тест завершен
      if (driver != null) { // - тут проверяем завершен ли тест и нужен ли драйвер
        driver.quit();
      }
    }
  }
}
