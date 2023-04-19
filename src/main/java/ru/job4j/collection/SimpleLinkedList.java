package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.setNext(new Node<>(value, null));
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int expectedModCount = modCount;
            private Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount)  {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E temp = currentNode.item;
                currentNode = currentNode.next;
                return temp;
            }
        };
    }

    private static class Node<E> {

        private E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
