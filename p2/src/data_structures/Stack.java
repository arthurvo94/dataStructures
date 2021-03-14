package data_structures;/*  Arthur Vo
    cssc4015
*/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Stack<E extends Comparable<E>> implements Iterable<E>{
    private int modCounter;
    private LinearList<E> list;

    public Stack(){
        list = new LinearList<>();
        modCounter = 0;
    }

    /* inserts the object obj into the stack
     */
    public void push(E obj){
        list.addLast(obj);
        modCounter++;
    }

    /* pops and returns the element on the top of the stack
     */
    public E pop(){
        if(isEmpty())
            throw new RuntimeException("ERROR Stack Underflow");
        modCounter++;
        return list.removeLast();
    }

    /* returns the number of elements currently in the stack
     */
    public int size(){
        return list.size();
    }

    /* return true if the stack is empty, otherwise false
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /* returns but does not remove the element on the top of the stack
     */
    public E peek(){
        return list.peekLast();
    }

    /* returns true if the object obj is in the stack,
     * otherwise false
     */
    public boolean contains(E obj){
        return list.contains(obj);
    }


    /* returns the stack to an empty state
     */
    public void makeEmpty(){
        list.clear();
        modCounter = 0;
    }

    /* removes the Object obj if it is in the stack and
     * returns true, otherwise returns false.
     */
    public boolean remove(E obj){
        return list.remove(obj) == obj;
    }

    /* returns a iterator of the elements in the stack. The elements
     * must be in the same sequence as pop() would return them.
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
                E temp = pop();
                list.addFirst(temp);
                modCounter--;   //maintain modcounter to account for relocating
                count++;
                return temp;
            }
        }
    }
}