package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;
    private Node<T> head;
    private int expectedModCount;
    private Node<T> currentNode;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.setNext(new Node<>(value, null));
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T temp;
        Node<T> t;
        if (head.next == null) {
            temp = head.item;
            head = null;
        } else {
            temp = head.item;
            t = head;
            head = head.next;
            t.setNext(null);
        }
        return temp;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    @Override
    public Iterator<T> iterator() {
        this.expectedModCount = modCount;
        this.currentNode = head;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T temp = currentNode.item;
                currentNode = currentNode.next;
                return temp;
            }
        };
    }

    private static class Node<T> {

        private T item;
        private Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
