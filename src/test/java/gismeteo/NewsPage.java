package gismeteo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class NewsPage {
    private final ElementsCollection articles = $$x("//a//div[@class='card-text']//div");

    public void seaArticles() {
        for(SelenideElement el : articles) {
            System.out.println(el.getText());
        }
    }

    public String getFirstArticle() {
        return articles.first().getText();
    }
}
