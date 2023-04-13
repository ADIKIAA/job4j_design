package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    private int size;

    public T poll() {
       if (size == 0) {
           throw new NoSuchElementException("Queue is empty");
       }
       for (T i : in) {
           out.addFirst(in.deleteFirst());
       }
       T temp = out.deleteFirst();
       for (T i : out) {
           in.addFirst(out.deleteFirst());
       }
       size--;
       return temp;
    }

    public void push(T value) {
        in.addFirst(value);
        size++;
    }
}
