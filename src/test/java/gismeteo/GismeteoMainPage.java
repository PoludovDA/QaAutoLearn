package gismeteo;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта Gismeteo
 */
public class GismeteoMainPage {
    private final SelenideElement searchForm = $x("//input");
    private final SelenideElement news = $x("//a[text()='Новости']");

    public GismeteoMainPage(String url) {
        Selenide.open(url);
    }

    public void searchTown(String townName) {
        searchForm.sendKeys(townName);
        searchForm.sendKeys(Keys.ENTER);
    }

    public NewsPage goToNews() {
        news.click();
        return new NewsPage();
    }
}
