package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/**
 * Linear List
 * CS 310 Spring 2021
 * @author Arthur Vo
 * edoras 4015
 */

@SuppressWarnings("unchecked")

public class ArrayLinearList<E> implements LinearListADT<E>{

    //instance variables
    public static final int DEFAULT_MAX_CAPACITY = 100;
    private E[] list;
    private int capacity, size, front, rear, modCounter;

    //constructors
    public ArrayLinearList(){
        this.capacity = DEFAULT_MAX_CAPACITY;
        this.list = (E[]) new Object[capacity];
        size = modCounter = 0;
        front = rear = 0;
    }

    public ArrayLinearList(int maxCapacity){
        this.capacity = maxCapacity;
        this.list = (E[]) new Object[capacity];
        size = modCounter = 0;
        front = rear = 0;
    }

    // utility returns front and rear values
    public void ends() {
        System.out.println("Front: " + front + ",Rear: " + rear);
    }

    //add object to front of list and return true if successful or false if list is full
    public boolean addFirst(E obj){
        if(isFull())        //return null if list is empty
            return false;
        else{
            if(size == 0){ //first element added case
                list[front] = obj;
                rear = 0;
            }
            else if(front == 0){ //handles wraparound case
                front = capacity - 1;
                list[front] = obj;
            }
            else{           //adds object to front
                list[--front] = obj;
            }
            size++;
            modCounter++;
            return true;
        }
    }

    //add object to rear of list and return true if successful or false if list is full
    public boolean addLast(E obj){
        if(isFull())        //return null if list is empty
            return false;
        else {
            if(size == 0){ //first element added case
                list[rear] = obj;
                rear = 0;
            }
            else if (rear == capacity - 1){   //handles wraparound case
                rear = 0;
                list[rear] = obj;
            }
            else{           //adds object to end
                list[++rear] = obj;
            }
            size++;
            modCounter++;
            return true;
        }
    }

    //removes and returns first object in list or returns null if list is empty
    public E removeFirst(){
        if(isEmpty())       //return null if list is empty
            return null;
        else{
            E temp = list[front];  //store temp object to be removed
            if(front == capacity - 1){   //handles wraparound case
                list[front] = null;
                front = 0;
            }
            else{           //removes object from front
                list[front++] = null;
            }
            size--;
            if(size == 0){
                front = rear = 0;
            }
            modCounter++;
            return temp;
        }
    }

    //removes and returns last object in list or returns null if list is empty
    public E removeLast(){
        if(isEmpty())       //return null if list is empty
            return null;
        else{
            E temp = list[rear];    //store temp object to be removed
            if(rear == 0){  //handles wraparound case
                list[rear] = null;
                rear = capacity - 1;
            }
            else{           //removes object from front
                list[rear--] = null;
            }
            size--;
            if(size == 0){
                front = rear = 0;
            }
            modCounter++;
            return temp;
        }
    }

    //removes and returns specified object
    public E remove(E obj){
        if(!contains(obj))      //return null if object doesn't exist in list
            return null;

        int count, x;
        count = x = 0;
        for(int i = 0; i <= size; i++){ //iterator
            x = front + i;
            count++;                //find index of object to remove relative to front
            if(x > capacity - 1)
                x = x - capacity;
//            System.out.println(list[x]);
            if(((Comparable<E>)list[x]).compareTo(obj) == 0)
                break;
        }

//        System.out.println("TEST FRONT: " + front);
//        System.out.println("TEST COUNT: " + count);
        int removeIndex = front + count - 1;
        if(count >= capacity) { //wraparound case
            removeIndex = count - capacity;
        }
//        System.out.println("TEST REMOVEIN: " + removeIndex);

        E temp = list[removeIndex]; //remove and store object
        list[removeIndex] = null;
        size--;

        count = 0;                 //clear variable to be reused
//        System.out.println("TEST COUNT0: " + count);
//        System.out.println("REAR: " + rear + " REMOVEIN: " + removeIndex);

//        System.out.println("REMOVEIN: " + removeIndex);
//        ends();

        if(rear > removeIndex)     //assign count to number of objects to be shifted
            count = rear - removeIndex;
        else if(rear == removeIndex)
            count = 1;
        else                       //wraparound case
            count = (capacity - removeIndex - 1) + rear + 1;

//        System.out.println("TEST COUNT: " + count);

        if(removeIndex == 0)        //reassign rear pointer
            rear = capacity - 1;
        else
            rear = removeIndex - 1;

        for(int i = 1; i <= count; i++){    //shift elements after object removed
            x = removeIndex + i;
            if(x >= capacity){ //wraparound case
                x = x - capacity;
            }
//            System.out.println("TEXT i: " + i + " TEST X: " + x);
            addLast(list[x]);
            size--;
//            System.out.print("size: " + size + " ");
//            ends();
        }

        modCounter++;
        return temp;
    }

    //returns first element in list, null if list is empty
    public E peekFirst(){
        if(isEmpty())
            return null;
        return list[front];
    }

    //returns last element in list, null if list is empty
    public E peekLast(){
        if(isEmpty())
            return null;
        return list[rear];
    }

    // Returns true if the parameter object obj is in the list, false otherwise. The list is not modified.
    public boolean contains(E obj){
        int x = 0;
        for(int i = 0; i <= size; i++){
            x = front + i;
            if(x >= capacity)
                x = x - capacity;
            if(((Comparable<E>)list[x]).compareTo(obj) == 0)
                return true;
        }
        return false;
    }

    /* Returns the element matching obj if it is in the list, null otherwise.
     *  In the case of duplicates, this method returns the element closest to front.
     *  The list is not modified.
     */
    public E find(E obj){
        E temp = null;
        if(contains(obj)){
            int x = 0;
            for(int i = 0; i <= size; i++){
                x = front + i;
                if(x >= capacity)
                    x = x - capacity;
                if(((Comparable<E>)list[x]).compareTo(obj) == 0){
                    temp = list[x];
                    break;
                }
            }
        }
        return temp;
    }

    // The list is returned to an empty state.
    public void clear(){
        while(!isEmpty()){
            removeFirst();
        }
    }

    // Returns true if the list is empty, otherwise false
    public boolean isEmpty(){
        if(this.size == 0)
            return true;
        return false;
    }

    // Returns true if the list is full, otherwise false
    public boolean isFull(){
        if(this.size == this.capacity)
            return true;
        return false;
    }

    // Returns the number of objects currently in the list.
    public int size(){
        return this.size;
    }

    public Iterator<E> iterator(){
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E> {
        private int index;
        private int changeCounter;
        private int count;

        public IteratorHelper() {
            this.index = front;
            this.changeCounter = modCounter;
        }

        @Override
        public boolean hasNext() {
            if (changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else {
                E temp = list[index];
                if(index == capacity - 1)   //wraparound case
                    index = 0;
                else
                    index++;
                count++;
                return temp;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}