/*  Arthur Vo
    cssc4015
*/
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Queue<E extends Comparable<E>> implements Iterable<E>{
    private int modCounter;
    private LinearList<E> list;

    public Queue(){
        list = new LinearList<>();
        modCounter = 0;
    }

    /*inserts the object obj into the queue
     */
    public void enqueue(E obj){
        list.addLast(obj);
        modCounter++;
    }

    /* removes and returns the object at the front of the queue
     */
    public E dequeue(){
//        if(isEmpty())
//            throw new RuntimeException("ERROR Queue Underflow");
        modCounter++;
        return list.removeFirst();
    }

    /* returns the number of objects currently in the queue
     */
    public int size(){
        return list.size();
    }

    /* returns true if the queue is empty, otherwise false
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /* returns but does not remove the object at the front of the queue
     */
    public E peek(){
        return list.peekFirst();
    }

    /* returns true if the Object obj is in the queue
     */
    public boolean contains(E obj){
        return list.contains(obj);
    }

    /* returns the queue to an empty state
     */
    public void makeEmpty(){
        list.clear();
        modCounter = 0;
    }

    /* removes the Object obj if it is in the queue and
     * returns true, otherwise returns false.
     */
    public boolean remove(E obj){
        return list.remove(obj) == obj;
    }

    /* returns an iterator of the elements in the queue. The elements
     * must be in the same sequence as dequeue would return them.
     */
    public Iterator<E> iterator(){
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<E>{
        private int changeCounter, count;

        public IteratorHelper(){
            changeCounter = modCounter;
            count = 0;
        }

        @Override
        public boolean hasNext(){
            if(changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return count < size();
        }

        @Override
        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            else{
                E temp = dequeue();
                enqueue(temp);
                modCounter -= 2;   //maintain modcounter to account for relocating
                count++;
                return temp;
            }
        }

    }
}