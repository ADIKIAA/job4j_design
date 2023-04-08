package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isNotCube() {
        Box box = new Box(4, 12);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Cube");
    }

    @Test
    void thisIsValidateVertex() {
        Box box = new Box(4, 15);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4);
    }

    @Test
    void thisIsNotValidateVertex() {
        Box box = new Box(3, 17);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void isExistGiveTrue() {
        Box box = new Box(8, 12);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isExistGiveFalse() {
        Box box = new Box(2, 7);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void thisAreaIs600dot00() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isCloseTo(600.00d, withinPercentage(0.01d));
    }

    @Test
    void thisAreaIs00dot00() {
        Box box = new Box(0, 2);
        double result = box.getArea();
        assertThat(result).isCloseTo(50.265d, withinPercentage(0.001d));
    }
}