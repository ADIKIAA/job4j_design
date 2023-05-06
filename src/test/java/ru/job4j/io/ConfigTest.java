package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPair() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/app_with_comments_and_spaces.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("h"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/app_with_comments_and_spaces.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("hibernate.connection.driver_class"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithTwoEquals() {
        String path = "./data/app_with_comments_and_spaces.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.user"))
                .isEqualTo("word=1234");
    }
}