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
        String path = "./data/app_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/app_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithTwoEquals() {
        String path = "./data/app_with_two_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.Postgre=SQLDialect");
    }

    @Test
    void whenPairWithoutEqual() {
        String path = "./data/app_without_equal.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class);
    }
}