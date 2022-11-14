package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size;
    private int modCount;
    private Node<E> last;

    @Override
    public void add(E value) {
        size++;
        modCount++;
        Node<E> node = new Node<>(null, value);
        if (last == null) {
            last = node;
            return;
        }
        Node<E> tail = last;
        while (tail.prev != null) {
            tail = tail.prev;
        }
        tail.prev = node;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = last;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.item;
                node = node.prev;
                return value;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> prev;

        Node(Node<E> prev, E element) {
            this.item = element;
            this.prev = prev;
        }
    }

    private Node<E> node(int index) {
        Node<E> x = last;
        for (int i = 0; i < index; i++) {
            x = x.prev;
        }
        return x;
    }
}
