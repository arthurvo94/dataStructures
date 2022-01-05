package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.TreeMap;

/*  Arthur Vo
    cssc4015
*/
@SuppressWarnings("unchecked")

public class BalancedTree<K extends Comparable<K>, V> implements DictionaryADT<K,V> {
    TreeMap<K, V> rbTree;

    public BalancedTree(){
        rbTree = new TreeMap<>();
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key){ return rbTree.containsKey(key); }

    // Adds the given key/value pair to the dictionary.  Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value){
        //returns false if duplicate key
        if(contains(key))
            return false;

        rbTree.put(key, value);
        return true;
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key){
        return ((Comparable<V>)getValue(key)).compareTo(rbTree.remove(key)) == 0;
    }

    // Returns the value associated with the parameter key.  Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key){ return rbTree.get(key); }

    // Returns the key associated with the parameter value.  Returns
    // null if the value is not found in the dictionary.  If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value){
        for(K key: rbTree.keySet()){
            if(((Comparable<V>)value).compareTo(rbTree.get(key)) == 0)
                return key;
        }
        //returns null if value is not found
        return null;
    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size(){ return rbTree.size(); }

    // Returns true if the dictionary is at max capacity
    public boolean isFull(){
        return false;   //returns false because tree can never be full
    }

    // Returns true if the dictionary is empty
    public boolean isEmpty(){ return rbTree.isEmpty(); }

    // Returns the Dictionary object to an empty state.
    public void clear(){ rbTree.clear(); }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order.  The iterator must be fail-fast.
    public Iterator keys(){
         return rbTree.keySet().iterator();
    }

    // Returns an Iterator of the values in the dictionary.  The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator values(){
        return rbTree.values().iterator();
    }
}

