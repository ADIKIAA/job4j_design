package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int colum = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while ((data[row].length == 0 || colum > data[row].length - 1) && row < data.length - 1) {
            row++;
            colum = 0;
        }
        return  colum < data[row].length && data[row][colum] != 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][colum++];
    }
}
