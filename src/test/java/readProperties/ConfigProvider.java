package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

public interface ConfigProvider {
    Config config = readConfig();

    /**
     * Если в пропертис есть testProfile то берем этот пропертис
     * Иначе чтение из conf файла
     * Далее извлечение значений переменных в интерфейс
     * @return
     */
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");
    Integer AGE = readConfig().getInt("age");
    String ADMIN_LOGIN = readConfig().getString("usersParams.admin.login");
    Boolean IS_DEMO_ADMIN = readConfig().getBoolean("usersParams.demo.isAdmin");
    List<String> DEMO_FRUITS = readConfig().getStringList("usersParams.demo.fruits");
}
