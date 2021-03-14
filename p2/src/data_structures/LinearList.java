/*  Arthur Vo
    cssc4015
*/
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

@SuppressWarnings("unchecked")

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
    private int size, modCounter;
    private Node<E> head, tail;

    //Node object inner class
    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    //no arg constructor
    public LinearList(){
        head = tail = null;
        size = modCounter = 0;
    }

    public boolean addFirst(E obj){
        Node<E> newNode = new Node<>(obj);

        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }

        size++;
        modCounter++;

        return true;
    }

    public boolean addLast(E obj){
        Node<E> newNode = new Node<>(obj);

        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        modCounter++;

        return true;
    }

    public E removeFirst(){
        if(isEmpty()){
            return null;
        }
        E temp = head.data;

        head = head.next;

        if(size == 1)
            clear();
        else{
            head.prev = null;
            size--;
            modCounter++;
        }

        return temp;
    }

    public E removeLast(){
        if(isEmpty()){
            return null;
        }
        E temp = tail.data;

        tail = tail.prev;

        if(size == 1)
            clear();
        else{
            tail.next = null;
            size--;
            modCounter++;
        }

        return temp;
    }

    public E remove(E obj){
        Node<E> temp = head;

        //traverse list
        while(temp != null && (obj).compareTo(temp.data) != 0){
            temp = temp.next;
        }

        //case handling
        if(temp == null)
            return null;
        if(temp == head)
            removeFirst();
        else if(temp == tail)
            removeLast();
        else{
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            size--;
            modCounter++;
        }
        if(head == null)
            clear();

        return temp.data;
    }

    public E peekFirst(){
        if(isEmpty())
            return null;
        return head.data;
    }

    public E peekLast(){
        if(isEmpty())
            return null;
        return tail.data;
    }

    public boolean contains(E obj){
        Node<E> temp = head;
        //traverse list
        while(temp != null){
            if((obj).compareTo(temp.data) == 0)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public E find(E obj){
        if(contains(obj))
            return obj;
        return null;
    }

    public void clear(){
        head = tail = null;
        size = modCounter = 0;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public boolean isFull(){ return false; }

    public int size(){ return size; }

    /* Returns an Iterator of the values in the list, presented in
     * the same order as the underlying order of the list. (front first, rear
     * last).
     */
    public Iterator<E> iterator(){
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E>{
        private int changeCounter;
        private Node<E> current;

        public IteratorHelper(){
            current = head;
            changeCounter = modCounter;
        }

        @Override
        public boolean hasNext(){
            if(changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return current != null;
        }

        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            else{
                E temp = current.data;
                current = current.next;
                return temp;
            }
        }
    }
}