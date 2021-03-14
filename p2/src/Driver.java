import data_structures.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Driver {
    private LinearList<Integer> list1;
    private Stack<Integer> stack1;
    private Queue<Integer> queue1;
    private Queue<String> queue2;

    public Driver()  throws FileNotFoundException{
        list1 = new LinearList<Integer>();
        stack1 = new Stack<Integer>();
        queue1 = new Queue<Integer>();
        queue2 = new Queue<String>();

        runTest();
    }

    private void runTest()  throws FileNotFoundException{
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);

        System.out.println();
        System.out.println("- - - LinearList - - - Initialized new LinearList<Integer> list1 - - -");
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println(" basic add and remove first and last  ");
        System.out.println();
        System.out.println("addFirst(2)");
        list1.addFirst(2);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeFirst()");
        list1.removeFirst();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("addLast(3)");
        list1.addLast(3);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeLast()");
        list1.removeLast();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" multiple removeLast() calls ");
        System.out.println();
        System.out.println("addLast(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addLast(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeLast()");
        list1.removeLast();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeLast() 9 more times...");
        for (int i = 1; i < 10; i++)
            list1.removeLast();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" addFirst(), then removeFirst() all of the elements ");
        System.out.println();
        System.out.println("addFirst(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addFirst(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeFirst() 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.removeFirst();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" addFirst(), then removeLast() all of the elements ");
        System.out.println();
        System.out.println("addFirst(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addFirst(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeLast() 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.removeLast();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" addLast(), then removeFirst() all of the elements ");
        System.out.println();
        System.out.println("addLast(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addLast(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeFirst() 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.removeFirst();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" contains() test ");
        System.out.println();
        System.out.println("addFirst(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addFirst(i);
        for (int i = 0; i < 12; i++) {
            System.out.println("Does the list contain: " + i + " " + list1.contains(i));
        }

        System.out.println();
        System.out.println(" remove() but of the head and tail elements ");
        System.out.println();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(10) 'the head of the list'...");
        list1.remove(10);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(1) 'the tail of the list'...");
        list1.remove(1);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        list1.clear();

        System.out.println();
        System.out.println(" remove duplicate item ");
        System.out.println();
        System.out.println("addFirst(1)...");
        list1.addFirst(1);
        System.out.println("addLast(1)...");
        list1.addLast(1);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(1) 'the head of the list'...");
        list1.remove(1);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(1) 'the tail and head of the list'...");
        list1.remove(1);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" remove() in middle ");
        System.out.println();
        System.out.println("addLast(i) 10 items via for loop...");
        for (int i = 1; i < 11; i++)
            list1.addLast(i);
        System.out.println("remove(6) 'an item in the middle'...");
        list1.remove(6);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("Printing the entire list...");
        for (int item : list1)
            System.out.print(item + " ");
        System.out.println();
        System.out.println("remove(i) from 1 to 4 items via for loop...");
        for (int i = 1; i < 5; i++)
            list1.remove(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("Printing the entire list...");
        for (int item : list1)
            System.out.print(item + " ");
        list1.clear();

        System.out.println();
        System.out.println();
        System.out.println(" decent amount of additions and removals ");
        System.out.println();
        System.out.println("addFirst(i) 10000 items via for loop...");
        for (int i = 1; i < 10001; i++)
            list1.addLast(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(i) all 10000 items via for loop...");
        for (int i = 1; i < 10001; i++)
            list1.remove(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" many items, awkward remove placement ");
        System.out.println();
        System.out.println("addLast(i) 1,000,000 items via for loop...");
        for (int i = 1; i < 1000001; i++)
            list1.addLast(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("remove(i) from 47,000 to 47,028 items via for loop...");
        for (int i = 47000; i < 47028; i++)
            list1.remove(i);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        list1.clear();

        System.out.println();
        System.out.println(" single element ");
        System.out.println();
        System.out.println("addFirst(10)...");
        list1.addFirst(10);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeFirst()");
        list1.removeFirst();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("addLast(10)...");
        list1.addLast(10);
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());
        System.out.println("removeLast()");
        list1.removeLast();
        System.out.println("First: " + list1.peekFirst() + " Last: " + list1.peekLast() + " Size: " + list1.size());

        System.out.println();
        System.out.println(" contains() and find() test with many elements ");
        System.out.println();
        System.out.println("addFirst(i) 1,000,000 items via for loop...");
        for (int i = 1; i < 1000001; i++)
            list1.addFirst(i);
        System.out.println("contains(700060)...");
        System.out.println("The list contains the value 700,060? " + list1.contains(700060));
        System.out.println("find(800000)...");
        System.out.println(list1.find(800000));
        System.out.println("find(1000001)...");
        System.out.println(list1.find(1000001));
        list1.clear();
        System.out.println();

        System.out.println("+ + +");
        System.out.println();
        System.out.println("- - - Stack - - - Initialized new Stack<Integer> stack1 - - -");
        System.out.println();
        System.out.println(" basic push(1), then loops and removes and isEmpty()  ");
        System.out.println();
        System.out.println("The stack's empty? " + stack1.isEmpty());
        System.out.println("Pushing 1 to the Stack...");
        stack1.push(1);
        System.out.println("Is it empty? " + stack1.isEmpty());
        stack1.makeEmpty();
        System.out.println("Adding 700 items to the stack via for loop...");
        for (int i = 1; i < 701; i++)
            stack1.push(i);
        System.out.println("Stack size: "+stack1.size()+" stack peek: "+stack1.peek());
        System.out.println("Removing 10 to 700 via for loop...");
        for (int i = 10; i < 701; i++)
            stack1.remove(i);
        System.out.println("Printing all the stack elements...");
        for (int stackElement : stack1)
            System.out.print(stackElement + " ");
        System.out.println();
        System.out.println("Removing 4 to 6 via for loop...");
        for (int i = 4; i < 7; i++)
            stack1.remove(i);
        System.out.println("Printing all the stack elements...");
        for (int stackElement : stack1)
            System.out.print(stackElement + " ");
        stack1.makeEmpty();
        System.out.println();

        System.out.println();
        System.out.println(" pushing multiple items, then taking a look at the stack ");
        System.out.println();
        System.out.println("push(i) 10 times via for loop...");
        for (int i = 1; i < 11; i++)
            stack1.push(i);
        System.out.println("Stack's size: " + stack1.size() + " Stack's top element: " + stack1.peek() + " Stack pop: "
                + stack1.pop() + " Stack's new size: " + stack1.size());
        System.out.println("Printing the entire stack...");
        for (int stackElement : stack1)
            System.out.print(stackElement + " ");
        System.out.println();
        System.out.println("pop() 3 times...");
        stack1.pop();
        stack1.pop();
        stack1.pop();
        System.out.println("Printing the entire stack...");
        for (int stackElement : stack1)
            System.out.print(stackElement + " ");
        System.out.println();
        System.out.println();

        System.out.println("+ + +");
        System.out.println();
        System.out.println("- - - Queue - - - Initialized new Queue<Integer> queue1 - - -");
        System.out.println();
        System.out.println(" basic enqueue(1) and isEmpty()  ");
        System.out.println();
        System.out.println("The queue's empty? " + queue1.isEmpty());
        System.out.println("Enqueueing 1 to the queue...");
        queue1.enqueue(1);
        System.out.println("Is it empty? " + queue1.isEmpty());
        queue1.makeEmpty();

        System.out.println();
        System.out.println(" enqueueing multiple items, then taking a look at the queue ");
        System.out.println();
        System.out.println("enqueue(i) 10 times via for loop...");
        for (int i = 1; i < 11; i++)
            queue1.enqueue(i);
        System.out.println("The entire queue now...");
        for (int queueElement : queue1)
            System.out.print(queueElement + " ");
        System.out.println("queue's size: " + queue1.size() + " queue's top element: " + queue1.peek()
                + " queue dequeue: " + queue1.dequeue() + " queue's new size: " + queue1.size());
        System.out.println("The entire queue now...");
        for (int queueElement : queue1)
            System.out.print(queueElement + " ");
        System.out.println();
        System.out.println("remove(7) "+queue1.remove(7));
        System.out.println("The entire queue now...");
        for (int queueElement : queue1)
            System.out.print(queueElement + " ");
        System.out.println();
        System.out.println("Queue's size is "+queue1.size());
        queue1.makeEmpty();
        System.out.println();
        System.out.println();



        System.out.println("+ + +");
        System.out.println();
        System.out.println("- - - Queue - - - Initialized new Queue<String> queue2 - - -");
        System.out.println();
        System.out.println(" basic enqueue(hello) and isEmpty()  ");
        System.out.println();
        System.out.println("Enqueueing hello to the queue...");
        queue2.enqueue("hello");
        System.out.println("The queue's empty? " + queue2.isEmpty());
        System.out.println("The entire queue now...");
        for (String queueElement : queue2)
            System.out.print(queueElement + " ");
        System.out.println();
        System.out.println("Queue's size is "+queue2.size());
        queue2.makeEmpty();

        System.out.println();
        System.out.println(" enqueue(hi number i) a million times from 1 to 1000000 ");
        for (int i = 1; i < 1000001; i++)
            queue2.enqueue("hi number "+i);
        System.out.println();
        System.out.println("Queue's size is "+queue2.size());
        System.out.println("The first element is: "+queue2.peek());
        System.out.println("The queue contains: hi number 706000? "+queue2.contains("hi number 706000"));
        System.out.println("The queue contains: hi number 1000001? "+queue2.contains("hi number 1000001"));
        System.out.println("remove(hi number 502382)...");
        queue2.remove("hi number 502382");
        System.out.println("does the queue contain: hi number 502382? "+ queue2.contains("hi number 502382"));

        System.out.println("+ + +");
        System.out.println();
        System.out.println("- - - LinearList - - - Initialized new LinearList<Integer> list1 - - -");
        System.out.println(" testing removal of an empty list want null (it doesn't say to throw) ");
        System.out.println(list1.removeLast());
        System.out.println(list1.removeFirst());
        System.out.println(list1.remove(1));
        System.out.println();
        System.out.println(" testing removal of an empty stack want false twice (it doesn't say to throw) ");
        System.out.println(list1.remove(-1));
        System.out.println(stack1.remove(-4));
        System.out.println(queue1.remove(-4));
    }

    public static void main(String[] args)  throws FileNotFoundException{
        new Driver();
    }

}
