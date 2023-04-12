package ru.job4j.collection;

public class SimpleStack<T> extends ForwardLinked<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return deleteFirst();
    }

    public void push(T value) {
        addFirst(value);
    }

}
