package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        //Установка драйвера + путь к нему + запуск
        WebDriverManager.chromedriver().setup();
        //Показать что будем пользоваться хромом
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Сколько будет ждать загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //Сколько будет ждать элемент
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Чтоб применились настройки драйвера к каждой странице
        BaseSeleniumPage.setDriver(driver);
    }

    @After
    public void tearDown() {
        //Закрытие драйвера
        driver.close();
        //Закрытие окна браузера
        driver.quit();
    }
}
