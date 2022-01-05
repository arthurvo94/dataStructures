package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/*  Arthur Vo
    cssc4015
*/
@SuppressWarnings("unchecked")

public class Hashtable<K extends Comparable<K>, V> implements DictionaryADT<K,V>{
    //node inner class
    private class DictionaryNode<K,V> implements Comparable<DictionaryNode<K,V>>{
        K key;
        V val;

        public DictionaryNode(K key, V val){
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(DictionaryNode<K, V> n){
            return ((Comparable<K>)key).compareTo((K)n.key);
        }
    }

    //instance variables
    private LinearList<DictionaryNode<K,V>>[] list;
    private final int tableSize;
    private int size;
    private int modCounter;

    public Hashtable(int tableSize){
        list = new LinearList[tableSize];
        this.tableSize = tableSize;
        size = modCounter = 0;
        //initializes linear list at each index
        for(int i = 0; i < tableSize; i++){
            list[i] = new LinearList<>();
        }
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key){
        DictionaryNode<K,V> findNode = new DictionaryNode<>(key, null);

        //get positive hashcode and mod by table size
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF;
        hashVal = hashVal % tableSize;

        return list[hashVal].contains(findNode);
    }

    // Adds the given key/value pair to the dictionary.  Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value){
        //check duplicates
        if(contains(key))
            return false;

        DictionaryNode<K,V> newNode = new DictionaryNode<>(key, value);

        //get positive hashcode and mod by table size
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF;
        hashVal = hashVal % tableSize;

        //add element to linked list at index
        list[hashVal].addFirst(newNode);

        //bookkeeping
        size++;
        modCounter++;

        return true;
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key){
        //check if element exists
        if(!contains(key))
            return false;

        DictionaryNode<K,V> delNode = new DictionaryNode<>(key, null);
        //get positive hashcode and mod by table size
        int hashVal = key.hashCode();
        hashVal = hashVal & 0x7FFFFFFF;
        hashVal = hashVal % tableSize;

        list[hashVal].remove(delNode);
        //bookkeeping
        size--;
        modCounter++;
        return true;
    }

    // Returns the value associated with the parameter key.  Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key){
        try {
            //get positive hashcode and mod by table size
            int hashVal = key.hashCode();
            hashVal = hashVal & 0x7FFFFFFF;
            hashVal = hashVal % tableSize;

            //iterate through list at hashed index to find matching key
            for (DictionaryNode<K, V> el : list[hashVal])
                if (((Comparable<K>) key).compareTo((K) el.key) == 0)
                    return el.val;
        }
        catch(NullPointerException e){
            return null;
        }

        //not found
        return null;
    }

    // Returns the key associated with the parameter value.  Returns
    // null if the value is not found in the dictionary.  If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value){
        //iterate through every list at every index to find value
        for(int i = 0; i < tableSize; i++)
            for(DictionaryNode<K,V> el: list[i])
                if(((Comparable<V>)value).compareTo(el.val) == 0)
                    return el.key;

        return null;
    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size(){ return size; }

    // Returns true if the dictionary is at max capacity
    // always returns false because chained hashtable is never full
    public boolean isFull(){ return false; }

    // Returns true if the dictionary is empty
    public boolean isEmpty(){ return size == 0; }

    // Returns the Dictionary object to an empty state.
    public void clear(){
        //clears linear list at every index
        for(int i = 0; i < tableSize; i++)
            list[i].clear();
        size = modCounter = 0;
    }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order.  The iterator must be fail-fast.
    public Iterator keys(){
        return new IteratorKeyHelper();
    }

    // Returns an Iterator of the values in the dictionary.  The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator values(){
        return new IteratorValHelper();
    }

    private class IteratorKeyHelper implements Iterator<K>{
        private DictionaryNode[] nodes;
        private int changeCounter;
        private int index;

        public IteratorKeyHelper(){
            changeCounter = modCounter;
            nodes = new DictionaryNode[size];
            index = 0;
            int j = 0;
            for(int i = 0; i < tableSize; i++)
                for(DictionaryNode n : list[i])
                    nodes[j++] = n;

            nodes = insertionSort(nodes);
        }

        public boolean hasNext(){
            if(changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return index < size;
        }

        public K next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return (K)nodes[index++].key;
        }
    }

    private class IteratorValHelper implements Iterator<V>{
        Iterator<K> iterator;

        public IteratorValHelper(){
            iterator = keys();
        }

        public boolean hasNext(){
            return iterator.hasNext();
        }

        public V next(){
            return getValue(iterator.next());
        }
    }

    private DictionaryNode[] insertionSort(DictionaryNode[] array){
        DictionaryNode[] on = array;
        int in, out;
        DictionaryNode<K,V> temp;

        for(out = 1; out < on.length; out++){
            temp = on[out];
            in = out;
            while(in > 0 && (on[in-1].compareTo(temp) > 0)){
                on[in] = on[in-1];
                in--;
            }
            on[in] = temp;
        }

        return array;
    }
}