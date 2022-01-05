package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/*  Arthur Vo
    cssc4015
*/
@SuppressWarnings("unchecked")

public class BinarySearchTree <K extends Comparable<K>, V extends Comparable<V>> implements DictionaryADT<K,V> {
    //node inner class
    private class Node<K,V> {
        private K key;
        private V val;
        private Node<K,V> left;
        private Node<K,V> right;

        public Node(K key, V val){
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    //instance variables
    private Node<K,V> root;
    private int size;
    private int modCounter;

    public BinarySearchTree(){
        root = null;
        size = modCounter = 0;
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key){
        if(!isEmpty()){
            Node<K,V> current = root;
            //searches tree for key
            while(current != null) {
                if(key.compareTo(current.key) == 0)     //found
                    return true;
                else if(key.compareTo(current.key) < 0) //left case
                    current = current.left;
                else                                    //right case
                    current = current.right;
            }
        }

        //returns false if not found or dictionary is empty
        return false;
    }

    // Adds the given key/value pair to the dictionary.  Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value){
        //returns false if duplicate key
        if(contains(key))
            return false;

        Node<K,V> newNode = new Node<>(key, value);

        if(isEmpty())
            root = newNode;
        else{
            Node current = root;
            Node parent;
            while(true){
                parent = current;
                if(key.compareTo((K)current.key) < 0){  //left case
                    current = current.left;
                    if(current == null){    //no children, insert left
                        parent.left = newNode;
                        size++;
                        modCounter++;
                        return true;
                    }
                }
                else{                                   //right case
                    current = current.right;
                    if(current == null){    //no children, insert right
                        parent.right = newNode;
                        size++;
                        modCounter++;
                        return true;
                    }
                }
            }
        }
        //bookkeeping
        size++;
        modCounter++;
        return true;
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key){
        if(isEmpty())   //tree is empty case
            return false;

        //keep track of current node and its parent
        Node<K,V> current = root;
        Node<K,V> parent = null;


        //find node to be deleted while keeping track of parent
        while( current != null  && current.key.compareTo(key) != 0) {
            parent = current;

            if(key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }

        //key not found
        if(current == null)
            return false;

        //0 children
        if(current.left == null && current.right == null){
            if(current == root) //only one element
                root = null;
            else{
                //removes parent's left/right pointer
                if(parent.left == current)
                    parent.left = null;
                else
                    parent.right = null;
            }
        }
        //1 children
        else if(current.left == null ^ current.right == null) {
            Node<K,V> child;
            //determines which child will replace node to be deleted
            if(current.left == null)
                child = current.right;
            else
                child = current.left;

            if(current == root) //if root is being deleted
                root = child;
            else{
                //reassign parent's left/right pointer
                if(parent.left == current)
                    parent.left = child;
                else
                    parent.right = child;
            }
        }
        //2 children
        else{
            //find the inorder successor to replace deleted node
            Node<K,V> successor = current.right;
            while(successor.left != null){   //find leftmost node in given subtree
                successor = successor.left;
            }

            //store successor data
            K successorKey = successor.key;
            V successorVal = successor.val;

            //delete successor node
            delete(successor.key);

            //replace original deleted node with successor data
            current.key = successorKey;
            current.val = successorVal;
        }

        //bookkeeping
        size--;
        modCounter++;
        return true;
    }

    // Returns the value associated with the parameter key.  Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key){
        if(isEmpty())
            return null;
        Node<K,V> current = root;
        while((current.key).compareTo(key) != 0){
            if(key.compareTo(current.key) < 0)
                current = current.left;     //left case
            else
                current = current.right;    //right case
            //not found
            if(current == null)
                return null;
        }
        return current.val;
    }

    // Returns the key associated with the parameter value.  Returns
    // null if the value is not found in the dictionary.  If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value){
        if(!isEmpty()) {
            Iterator keys = keys();
            K curr;
            while (keys.hasNext()) {
                curr = (K) keys.next();
                if (getValue(curr).compareTo(value) == 0)
                    return curr;
            }
        }

        //returns null if empty or value not found
        return null;
    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size(){ return size; }

    // Returns true if the dictionary is at max capacity
    // returns false because tree can never be full
    public boolean isFull(){ return false; }

    // Returns true if the dictionary is empty
    public boolean isEmpty(){ return root == null; }

    // Returns the Dictionary object to an empty state.
    public void clear(){
        root = null;
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

    //key iterator creates a linked list
    private class IteratorKeyHelper implements Iterator<K>{
        private int changeCounter;
        private LinearList<K> list;

        public IteratorKeyHelper(){
            changeCounter = modCounter;
            list = new LinearList<>();

            inOrder(root);
        }

        //helper method to add elements of tree in order to an array
        //recursively fills array in left-middle-right order
        private void inOrder(Node<K,V> n){
            if(n != null){
                inOrder(n.left);
                list.addLast(n.key);
                inOrder(n.right);
            }
        }

        @Override
        public boolean hasNext(){
            if(changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return !list.isEmpty();
        }

        @Override
        public K next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return list.removeFirst();
        }
    }

    private class IteratorValHelper implements Iterator<V>{
        private int changeCounter;
        private LinearList<V> list;

        public IteratorValHelper(){
            changeCounter = modCounter;
            list = new LinearList<>();

            inOrder(root);
        }

        //helper method to add elements of tree in order to an array
        //recursively fills array in left-middle-right order
        private void inOrder(Node<K,V> n){
            if(n != null){
                inOrder(n.left);
                list.addLast(n.val);
                inOrder(n.right);
            }
        }

        @Override
        public boolean hasNext(){
            if(changeCounter != modCounter)
                throw new ConcurrentModificationException();
            return !list.isEmpty();
        }

        public V next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return list.removeFirst();
        }
    }
}

