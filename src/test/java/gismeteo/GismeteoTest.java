package gismeteo;

import core.BaseSelenideTest;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class GismeteoTest extends BaseSelenideTest {

    private final static String BASE_URL = "https://www.gismeteo.ru/";
    private final static String TOWN_SEARCH = "Петербург";
    private final static String NEWS_TAG = "Рождество";

    @Test
    @DisplayName("Проверить что первая новость на сайте Gismeteo содержит 'Новый год'")
    public void checkTownSearch() {
        GismeteoMainPage gismeteoMainPage = new GismeteoMainPage(BASE_URL);
        gismeteoMainPage.goToNews();
        NewsPage newsPage = new NewsPage();
        Assert.assertTrue(newsPage.getFirstArticle().contains(NEWS_TAG));
    }

    @Test
    @DisplayName("Кейс сверху одной строчкой")
    public void coolCheckTownSearch() {
        Assert.assertTrue(new GismeteoMainPage(BASE_URL).goToNews().getFirstArticle().contains(NEWS_TAG));

    }
}
