import data_structures.*;

public class Driver2 {
    private Stack<Integer> stack;
    private Queue<Integer> queue;

    public Driver2() {
        stack = new Stack<>();
        queue = new Queue<>();

        runStackTest();
        runQueueTest();
    }

    private void runStackTest() {
        System.out.println("\n-----TEST PUSH-----");
        System.out.println("Should print: 5 4 3 2 1");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        printStack();

        System.out.println("\n-----TEST ITERATOR-----");
        System.out.println("Should print: 5 4 3 2 1");
        printStack();

        System.out.println("\n-----TEST POP-----");
        System.out.println("Should print: ");
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        printStack();

        System.out.println("\n-----TEST makeEmpty-----");
        System.out.println("Should print: 3 2 1");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.makeEmpty();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        printStack();

        System.out.println("\n-----TEST LARGE N-----");
        System.out.println("Should print: ");
        stack.makeEmpty();
        for(int i = 1; i <= 1000; i++){
            stack.push(i);
        }
        for(int i = 1; i <= 1000; i++){
            stack.pop();
        }
        for(int i = 1; i <= 1000; i++){
            stack.push(i);
            stack.pop();
        }
        printStack();

        System.out.println("\n-----TEST REMOVE-----");
        System.out.println("Should print: 10 9 8 7 6 2 1");
        stack.makeEmpty();
        for(int i = 1; i <= 10; i++){
            stack.push(i);
        }
        System.out.println("REMOVE 5: " +stack.remove(5));
        System.out.println("REMOVE 4: " +stack.remove(4));
        System.out.println("REMOVE 3: " +stack.remove(3));
        System.out.println("REMOVE 20: " +stack.remove(20));
        printStack();
    }

    private void runQueueTest(){
        System.out.println("\n-----TEST ENQUEUE-----");
        System.out.println("Should print: 1 2 3 4 5");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        printQueue();

        System.out.println("\n-----TEST ITERATOR-----");
        System.out.println("Should print: 1 2 3 4 5");
        printQueue();

        System.out.println("\n-----TEST DEQUEUE-----");
        System.out.println("Should print: ");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        printQueue();

        System.out.println("\n-----TEST makeEmpty-----");
        System.out.println("Should print: 1 2 3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.makeEmpty();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        printQueue();

        System.out.println("\n-----TEST LARGE N-----");
        System.out.println("Should print: ");
        queue.makeEmpty();
        for(int i = 1; i <= 1000; i++){
            queue.enqueue(i);
        }
        for(int i = 1; i <= 1000; i++){
            queue.dequeue();
        }
        for(int i = 1; i <= 1000; i++){
            queue.enqueue(i);
            queue.dequeue();
        }
        printQueue();

        System.out.println("\n-----TEST REMOVE-----");
        System.out.println("Should print: 1 2 6 7 8 9 10");
        queue.makeEmpty();
        for(int i = 1; i <= 10; i++){
            queue.enqueue(i);
        }
        System.out.println("REMOVE 5: " +queue.remove(5));
        System.out.println("REMOVE 4: " +queue.remove(4));
        System.out.println("REMOVE 3: " +queue.remove(3));
        System.out.println("REMOVE 20: " +queue.remove(20));
        printQueue();

        System.out.println("\n-----TEST QUEUE UNDERFLOW-----");
        System.out.println("Should print:");
        queue.makeEmpty();
        for(int i = 1; i <= 10; i++){
            queue.enqueue(i);
        }
        for(int i = 1; i <= 11; i++){
            queue.dequeue();
        }
        printQueue();

    }

    public void printQueue(){
        for(Integer i: queue){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void printStack(){
        for(Integer i: stack){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Driver2();
    }

}
