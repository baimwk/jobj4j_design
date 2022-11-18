package ru.job4j.collection;


import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>(); // обратный
    private final SimpleStack<T> out = new SimpleStack<>();  //прямой
    private int inCount;
    private int outCount;

    public T poll() { // возвращать первое значение и удалять его
        if (inCount == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }
        if (outCount == 0) {
            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }
        }
        outCount--;
        return out.pop();
    }

    public void push(T value) { //помещать значение в конец
        in.push(value);
        inCount++;
    }
}
