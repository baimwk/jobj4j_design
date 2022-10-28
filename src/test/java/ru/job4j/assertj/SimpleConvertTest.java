package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "fourth", "fifth");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(list).hasSize(5)
                .contains("fourth")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("test", "six", "fourth");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> stringSet = simpleConvert.toSet("first", "first", "second", "third", "third");
        assertThat(stringSet).hasSize(3)
                .containsOnly("second", "first", "third")
                .containsOnlyOnce("first")
                .containsOnlyOnce("third");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second");
        assertThat(map).hasSize(2)
                .containsKeys("first", "second")
                .containsValues(0, 1)
                .doesNotContainKey("three")
                .doesNotContainValue(2)
                .containsEntry("first", 0);
    }
}