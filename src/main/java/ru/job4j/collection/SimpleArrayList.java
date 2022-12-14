package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            doubleArrayLength();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T previous = get(index);
        container[index] = newValue;
        return previous;
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[--size] = null;
        modCount++;
        return removed;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int elementsCount = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return elementsCount < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[elementsCount++];
            }

        };
    }

    private void doubleArrayLength() {
        container = container.length > 0
                ? Arrays.copyOf(container, container.length * 2)
                : Arrays.copyOf(container, container.length + 1);
    }
}
