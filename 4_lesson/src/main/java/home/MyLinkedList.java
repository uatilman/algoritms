package home;


// зделал все методы итератора, но не смог все проверить, т.к. использовал наследование от ListIterator
// а потом запутался как это сделать без наследования.

import java.util.*;

public class MyLinkedList<E> {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        //push test
        for (int i = 0; i < 5; i++) {
            list.push((int) (Math.random() * 100));
        }

        //toString test
        System.out.println(list);
        //size test
        System.out.println("size " + list.size());


        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //pushFirst test
        list.pushFirst(111);
        System.out.println("size " + list.size());


        //popLast test
        System.out.println(list.popLast());
        System.out.println(list);
        System.out.println("size " + list.size());


        //pop test
        for (int i = 0; i < 5; i++) {
            System.out.print(list.pop() + " -- ");
        }
        System.out.println(list);


//        List<Integer> l = new ArrayList<>()
//        l.iterator()
//        Iterator<Integer> it = l.iterator();
//       it.forEachRemaining();
    }

    private Node<E> first;
    private Node<E> last;
    private int size = 0;


    public MyLinkedList() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public E peek() {
        return first.element;
    }

    public E peekLast() {
        return last.element;
    }

    public void pushFirst(E element) {
        if (first == null) {
            push(element);
        } else {
            Node<E> newNode = new Node<>(null, element, first);
            first.previous = newNode;
            first = newNode;
        }
        size++;
    }

    public void push(E element) {
        if (first == null) {
            Node<E> newNode = new Node<>(null, element, null);
            first = newNode;
            last = newNode;
        } else {
            Node<E> newNode = new Node<>(last, element, null);
            last.next = newNode;
            last = newNode;

        }
        size++;
    }

    public E popLast() {
        Node<E> temp = last;
        if (temp == null) throw new NoSuchElementException();
        last = last.previous;
        last.next = null;
        size--;
        return temp.element;
    }

    public E pop() {
        Node<E> temp = first;
        if (temp == null) throw new NoSuchElementException();
        first = first.next;
        size--;
        return temp.element;
    }

    @Override
    public String toString() {
        Node<E> current = first;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current.toString());
            current = current.next;
            sb.append((current == null) ? "" : ", ");
        }
        sb.append("]");
        return sb.toString();
    }


    private static class Node<E> {
        Node<E> previous;
        Node<E> next;
        E element;

        Node(Node<E> previous, E element, Node<E> next) {
            this.next = next;
            this.previous = previous;
            this.element = element;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public MyIterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements ListIterator<E> {
        int current = 0;
        Node<E> currentNode = first;

        public E getCurrent() {
            return currentNode.element;
        }

        public void reset() {
            currentNode = first;
            current = 0;
        }

        public void insertAfter(E element) {
            Node<E> newNode = new Node<>(currentNode, element, currentNode.next);
            currentNode.next.previous = newNode;
            currentNode.next = newNode;
        }

        public void insertBefore(E element) {
            Node<E> newNode = new Node<>(currentNode.previous, element, currentNode);
            currentNode.previous.next = newNode;
            currentNode.previous = newNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        // atEnd() - is at the end
        // Не понял зачем этот метод, если есть hasNext


        @Override
        public E next() {
            currentNode = currentNode.next;
            current++;
            return currentNode.element;
        }


        @Override
        public boolean hasPrevious() {
            return currentNode.previous != null;
        }

        @Override
        public E previous() {
            return currentNode.previous.element;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
            currentNode = currentNode.next;
            currentNode.previous = null;
        }

        @Override
        public void set(E e) {
            if (currentNode != null) currentNode.element = e;
        }

        @Override
        public void add(E e) {
            push(e);
        }

    }
}
