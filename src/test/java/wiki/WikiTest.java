package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.BaseSelenideTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends BaseSelenideTest {
    private static final String URL = "https://ru.wikipedia.org/wiki/Java";

    /**
     * Получить href ссылки нужного блока
     * Перейти по каждой ссылке и сравнить текущий url с атрибутом href
     */
    @Test
    public void getHrefTest() {
        Selenide.open(URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a");
        List<String> links = new ArrayList<>();
//        // способ для лохов
//        for (int i = 0; i < hrefs.size(); i++)
//            links.add(hrefs.get(i).getAttribute("href"));
//        // способ сложнее
//        for (SelenideElement element : hrefs)
//            links.add(element.getAttribute("href"));
        // нормальный способ
        hrefs.forEach(x-> links.add(x.getAttribute("href")));

        /**
         * Получить список с длиной всех ссылок
         */
        // средний способ
//        List<Integer> linksLength = new ArrayList<>();
//        links.forEach(x-> linksLength.add(x.length()));
        // продвинутый способ
        List<Integer> linksLength = hrefs.stream().map(x-> x.getAttribute("href").length()).collect(Collectors.toList());

        //открыть все ссылки
        links.forEach(Selenide :: open);

        for (String link : links) {
            Selenide.open(link);
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, link);
        }

        /**
         * Проверка ссылок в рандомном порядке, когда ссылка проверена, она удаляется
         * из списка и рандом работает лишь на оставшихся
         */

        for(int i = 0; i < links.size(); i++) {
            int randomNumber = new Random().nextInt(links.size());
            String listUrl = links.get(randomNumber);
            Selenide.open(links.get(randomNumber));
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assert.assertEquals(listUrl, currentUrl);
            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
        }
    }
}
