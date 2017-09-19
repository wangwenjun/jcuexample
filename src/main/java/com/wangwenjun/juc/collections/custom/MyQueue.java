package com.wangwenjun.juc.collections.custom;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/18
 * QQ交流群:601980517，463962286
 ***************************************/

/**
 * un-threadsafe
 *
 * @param <E>
 */
public class MyQueue<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return (element == null) ? "" : element.toString();
        }
    }

    private Node<E> first, last;
    private int size;


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E peekFirst() {
        return (first == null) ? null : first.getElement();
    }

    public E peekLast() {
        return (last == null) ? null : last.getElement();
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) {
            first = newNode;

        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = first.getElement();
        first = first.getNext();
        size--;
        if (size == 0)
            last = null;
        return answer;
    }

    /**
     * FIFO
     *
     * @param args
     */
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        queue.addLast("Hello");
        queue.addLast("World");
        queue.addLast("Java");

        System.out.println(queue.removeFirst());//Hello
        System.out.println(queue.removeFirst());//World
        System.out.println(queue.removeFirst());//Java
    }
}