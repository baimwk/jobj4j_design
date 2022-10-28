package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 7);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void checkNumberOfVerticesPositive() {
        Box box = new Box(4, 8);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEqualTo(4);
    }

    @Test
    void checkNumberOfVerticesMinusOne() {
        Box box = new Box(3, 9);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isExist() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isEqualTo(true);
    }

    @Test
    void isNotExist() {
        Box box = new Box(3, 11);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isEqualTo(false);
    }

    @Test
    void getAreaForSphere() {
        Box box = new Box(0, 5);
        double result = box.getArea();
        assertThat(result).isPositive()
                .isCloseTo(314.15d, withPrecision(0.01d))
                .isCloseTo(314.15d, Percentage.withPercentage(1.0d))
                .isGreaterThan(314.15d)
                .isLessThan(314.16d);
    }

    @Test
    void getAreaForUnknown() {
        Box box = new Box(3, 11);
        double result = box.getArea();
        assertThat(result).isEqualTo(0.0d)
                .isLessThan(0.1d);
    }

    @Test
    void getTypeForTetrahedron() {
        Box box = new Box(4, 12);
        String result = box.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("ahe")
                .isEqualTo("Tetrahedron");

    }

    @Test
    void getTypeForUnknown() {
        Box box = new Box(3, 12);
        String result = box.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("obj")
                .startsWith("Unknown")
                .endsWith("object")
                .isEqualTo("Unknown object");
    }
}