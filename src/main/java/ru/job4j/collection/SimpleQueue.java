package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    private int sizeIn;
    private int sizeOut;

    public T poll() {
       if (sizeIn == 0) {
           throw new NoSuchElementException("Queue is empty");
       }
       while (sizeIn != 0) {
           out.push(in.pop());
           sizeIn--;
           sizeOut++;
       }
       T temp = out.pop();
       sizeOut--;
       while (sizeOut != 0) {
           in.push(out.pop());
           sizeOut--;
           sizeIn++;
       }
       return temp;
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
