package com.wangwenjun.juc.collections.custom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/18
 * QQ交流群:601980517，463962286
 ***************************************/
public class LockFreeQueue<E> {

    private AtomicReference<Node<E>> head, tail;

    private AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        Node<E> node = new Node<>(null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public void addLast(E e) {
        if (e == null) throw new NullPointerException("The null element not allow");
        Node<E> newNode = new Node<>(e);
        Node<E> previousNode = tail.getAndSet(newNode);
        previousNode.next = newNode;
        //size.incrementAndGet();
    }

    public E removeFirst() {

        Node<E> headNode, valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;
        } while (valueNode != null && !head.compareAndSet(headNode, valueNode));

        E value = (valueNode != null ? valueNode.element : null);
        if (valueNode != null) {
            valueNode.element = null;
        }

        //size.decrementAndGet();
        return value;
    }


    public boolean isEmpty() {
        synchronized (this) {
            return head.get().next == null;
        }
    }

    private static class Node<E> {
        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element == null ? "" : element.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<Long, Object> data = new ConcurrentHashMap<>();
        final LockFreeQueue<Long> queue = new LockFreeQueue<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 0;
            while ((counter++) <= 10) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.addLast(System.nanoTime());
            }
        }).forEach(service::submit);

        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 10;
            while (counter >= 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Long value = queue.removeFirst();
                if (value == null) continue;
                counter--;
                System.out.println(value);
                data.put(value, new Object());
            }
        }).forEach(service::submit);

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);

        System.out.println(data.size());
        System.out.println("=================");
        System.out.println(data.keys());
    }
}
