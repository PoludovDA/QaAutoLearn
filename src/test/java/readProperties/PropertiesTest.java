package readProperties;

import core.BaseSelenideTest;
import org.junit.Test;

import java.io.IOException;

/**
 * файл properties: все данные типа String. Нельзя группировать. Все просто и примитивно
 * файл conf: поддерживает многие типы данных, удобен в использовании
 */
public class PropertiesTest extends BaseSelenideTest {

    @Test
    public void readProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String url = System.getProperty("url");
        System.out.println(url);
    }

    @Test
    public void readConfFile() {
        String url = ConfigProvider.URL;
        System.out.println(url);
        //Проверка что админ действительно админ
        if(ConfigProvider.readConfig().getBoolean("usersParams.admin.isAdmin"))
            System.out.println("Действительно админ");
        else
            System.out.println("Ошибка");
    }
}
