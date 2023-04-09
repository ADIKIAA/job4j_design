package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    private boolean isEvenNumber(int number) {
        return Math.abs(number) % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length - 1 && !isEvenNumber(data[index])) {
            index++;
        }
        return index >= 0 && index < data.length && isEvenNumber(data[index]);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
