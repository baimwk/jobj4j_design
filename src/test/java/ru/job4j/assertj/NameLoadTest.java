package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkValidateNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Test"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol \"=\"")
                .hasMessageContaining("Test");
    }

    @Test
    void checkValidateNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=this", "name=One"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key")
                .hasMessageContaining("=this")
                .hasMessageNotContaining("name=One");
    }

    @Test
    void checkValidateNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("this=Kate", "name="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value")
                .hasMessageContaining("name=")
                .hasMessageNotContaining("this=Kate");
    }
}