import data_structures.*;

public class Driver{
    public static void main(String[] args){
        new Driver();
    }

    public Driver(){
//        myTests();
//        runTests();
        moreTests();
    }

    public void myTests(){
        BinaryHeapPriorityQueue<Integer> minHeap = new BinaryHeapPriorityQueue<Integer>(10);

        //test insert
        System.out.println("TESTING INSERT");
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(3);
        minHeap.insert(18);
        minHeap.insert(5);
        System.out.println(minHeap.toString());

        //test remove
        System.out.println("TESTING REMOVE");
        minHeap.remove();

        System.out.println(minHeap.toString());

        //test iterator
        System.out.println("TESTING ITERATOR");
        for(Integer i: minHeap)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("TESTING DELETE");
        minHeap.delete(5);
        for(Integer i: minHeap)
            System.out.print(i + " ");
        System.out.println();
    }

    public void moreTests(){
        BinaryHeapPriorityQueue<Integer> q = new BinaryHeapPriorityQueue<Integer>(15);

//        //case 1
//        q.insert(1);
//        q.insert(2);
//        q.insert(3);
//        q.insert(4);
//        q.remove();
//        System.out.println("expected: 2, 4, 3");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 2
//        q.insert(1);
//        q.insert(2);
//        q.insert(3);
//        q.insert(4);
//        q.insert(5);
//        q.remove();
//        System.out.println("expected: 2, 4, 3, 5");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 3
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.insert(2);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 2, 2, 2, 2");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 4
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.insert(2);
//        q.insert(2);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 2, 2, 2, 2, 2");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 5
//        q.insert(1);
//        q.insert(2);
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 1, 2, 2, 2,");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 6
//        q.insert(1);
//        q.insert(1);
//        q.insert(1);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 1, 2, 1");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 7
//        q.insert(1);
//        q.insert(1);
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 1, 2, 1, 2");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 8
//        q.insert(1);
//        q.insert(1);
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.remove();
//        System.out.println("expected: 1, 2, 1, 2");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 9
//        q.insert(1);
//        q.insert(2);
//        q.insert(2);
//        q.insert(4);
//        q.insert(5);
//        q.insert(3);
//        q.insert(6);
//        q.delete(2);
//        System.out.println("expected: 1, 4, 3, 6, 5");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");
//
//
//        //case 10
//        for (int i = -1; i>=-4; i--) {
//            for (int j = -1; j>=-4; j--)
//                q.insert(j);
//        }
//        System.out.println("expected: -4, -4, -4, -3, -3, -3, -3, -1, -1, -1, -2, -2, -1, -2, -2");
//        for (Integer i : q) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("size: " + q.size());
//        q.clear();
//        System.out.println("");


        //case 11
        for (int i = -1; i>=-4; i--) {
            for (int j = -1; j>=-4; j--)
                q.insert(j);
        }
        for (Integer i : q) {
            System.out.print(i+" ");
        }
        System.out.println();
        q.delete(-3);
        q.delete(-4);
        System.out.println("expected: -2, -2, -2, -2, -1, -1, -1, -1");
        for (Integer i : q) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("size: " + q.size());
        q.clear();
        System.out.println("");


        //case 12
        System.out.println("expected: 10, 9, 8, 7, 6, 5, 4, 3, 2, 1");
        for (int i = 10; i>0; i--) {
            q.insert(i);
            System.out.print(q.peek()+" ");
        }
        System.out.println();
        System.out.println("size: " + q.size());
    }

    public void runTests(){
        BinaryHeapPriorityQueue<Integer> heap = new BinaryHeapPriorityQueue<Integer>(50);
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(8);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        System.out.println("Before remove initial heap is...");
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Remove() ...");
        heap.remove();
        for (Integer i : heap)
            System.out.print(i + " ");
    }

}