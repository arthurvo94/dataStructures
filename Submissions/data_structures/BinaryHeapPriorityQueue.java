/*  Arthur Vo
    cssc4015
*/
package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    //instance variables
    public static final int DEFAULT_MAX_CAPACITY = 1000;
    private Wrapper<E>[] heap;
    private int size, entryNumber, modCount;

    //constructors
    public BinaryHeapPriorityQueue(){
        this(DEFAULT_MAX_CAPACITY);
    }

    public BinaryHeapPriorityQueue(int n){
        heap = new Wrapper[n];
        size = entryNumber = modCount = 0;
    }

    //methods

    public boolean insert(E obj){
        if(isFull())        //returns false if heap is full
            return false;

        heap[size++] = new Wrapper<>(obj);  //adds object to end of heap, trickles up if necessary
        trickleUp();

        modCount++;
        return true;
    }

    public E remove(){
        if(isEmpty())
            return null;

        E temp = heap[0].data;
        trickleDown();
        size--;

        modCount++;
        return temp;
    }

    public boolean delete(E obj){
        if(!contains(obj))
            return false;

        while(contains(obj)) {
            for (int i = 0; i < size - 1; i++) {
                if (obj.compareTo(heap[i].data) == 0) {
                    //replace element to be deleted with last element
                    heap[i] = heap[--size];
                    int parent = (i - 1) >> 1;
                    int current = i;
                    if (current == 0 || heap[parent].compareTo(heap[current]) < 0) {  //if item is now root or parent is smaller
                        int child = getNextChild(current);
                        while (child != -1 && heap[child].compareTo(heap[current]) < 0) {    //if child is smaller than current
                            swap(current, child);   //swap element with child
                            current = child;
                            child = getNextChild(current);
                        }
                    } else {   //parent is greater
                        while (heap[current].compareTo(heap[parent]) < 0) {
                            swap(current, parent);
                            current = parent;
                            parent = (current - 1) >> 1;
                        }
                    }
                }
            }
        }

        modCount++;
        return true;
    }

    public E peek(){
        if(isEmpty())
            return null;
        return heap[0].data;
    }

    public boolean contains(E obj){
        for(int i = 0; i < size - 1; i++){
            if(obj.compareTo(heap[i].data) == 0)
                return true;
        }
        return false;
    }

    public int size(){ return size; }

    public void clear(){ size = entryNumber = modCount = 0; }

    public boolean isEmpty(){ return size == 0; }

    public boolean isFull(){ return size == heap.length; }

    //additional methods
    private void trickleUp(){
        int newIn = size - 1;
        int parentIn = (newIn - 1) >> 1;
        Wrapper<E> val = heap[newIn];
        while(parentIn >= 0 && val.compareTo(heap[parentIn]) < 0){
            heap[newIn] = heap[parentIn];
            newIn = parentIn;
            parentIn = (parentIn-1) >> 1;
        }
        heap[newIn] = val;
    }

    private void trickleDown(){
        int current = 0;
        int child = getNextChild(current);
        while(child != -1 && heap[current].compareTo(heap[child]) < 0 && heap[child].compareTo(heap[size-1]) < 0){
            heap[current] = heap[child];
            current = child;
            child = getNextChild(current);
        }
        heap[current] = heap[size - 1];
    }

    private int getNextChild(int index){
        int left = (index << 1) + 1;
        int right = left + 1;
        if(right < size){       //two children
            if(heap[left].compareTo(heap[right]) < 0)
                return left;
            return right;
        }
        else if(left < size)   //one child
            return left;
        return -1;             //no children
    }

    //swaps values in array at given indexes
    private void swap(int a, int b){
        Wrapper<E> temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    //Wrapper class
    protected class Wrapper<E> implements Comparable<Wrapper<E>>{
        E data;
        int priority;

        public Wrapper(E obj){
            data = obj;
            priority = entryNumber++;
        }

        public int compareTo(Wrapper<E> obj){
            if(((Comparable<E>)data).compareTo(obj.data) == 0)  //use priority number if data is equal
                return (priority - obj.priority);
            return ((Comparable<E>)data).compareTo(obj.data);
        }
    }

    @Override
    public String toString(){
        String list = "";
        for(int i = 0; i < size - 1; i++){
            list += heap[i].data + " ";
        }
        list += heap[size - 1].data;

        return list;
    }

    //returns an iterator that will display elements in level-order
    public Iterator iterator(){ return new IteratorHelper(); }

    private class IteratorHelper implements Iterator<E>{
        private int index, changeCount;

        public IteratorHelper(){
            index = 0;
            changeCount = modCount;
        }

        @Override
        public boolean hasNext(){
            if(changeCount != modCount)
                throw new ConcurrentModificationException();
            return index < size;
        }

        @Override
        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return heap[index++].data;
        }
    }
}
