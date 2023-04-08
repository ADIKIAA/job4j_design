package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void namesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void nameDoesNotContainSymbolEqually() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("52345"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: 52345 does not contain the symbol \"=\"");
    }

    @Test
    void nameDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=4353"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =4353 does not contain a key");
    }

    @Test
    void nameDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("3245="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: 3245= does not contain a value");
    }
}