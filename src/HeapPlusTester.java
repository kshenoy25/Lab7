import javax.management.ObjectName;
import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;
import java.util.*;


class Heap<E> {
    private Object [] data;
    private int numItems;
    public static int DEFAULT_SIZE = 31;

    // Heap constructor
    public Heap() {
        data = new Object[DEFAULT_SIZE];
        numItems = 0;
    }

    public Heap(E[] items){
        data = new Object[items.length];
        int nSize = items.length;

        for (int i = (nSize/2); i > 0; i--){
            subHeap(items, nSize, i-1);
        }

        //// DEEP COPY Items array to data after running subHeap
        for (int i = 0; i < items.length; i++){
            data[i] = items[i];
        }

        reHeapDown(nSize);
    }


    public int getLevel (E target){
        boolean found = false;
        int current = 0;
        int lowPow=0;
        int highPow=0;
        // Check if the first item in the list is
        for (int i = 0; i < data.length; i++){
            if(data[i] == target){
                found = true;
                current = i;
                break;
            }
        }
        if (found){
            if (current == 0)
                return 1;
            if (current >0 && current < 3)
                return 2;

            for (int i = 2; i < data.length; i++){
                lowPow = (int) Math.pow(2, i)-1;
                highPow = (int) Math.pow(2, i+1)-2;
                if ( current >= lowPow && current <= highPow ){
                    return i+1 ;
                }
            }
        }

        return 0;


    }



    public int parent (int n) {
        return (n-1)/2;
    }

    public int leftChild (int n) {
        return 2*n + 1;
    }

    public int rightChild (int n) {
        return 2*n + 2;
    }

    public void subHeap(E arr[], int nSize, int index) {

        int parent = index; // Initialize largest as root
        int lChild = leftChild(index);
        int rChild = rightChild(index);

        Comparable dataParent =  (Comparable)arr[index];
        Comparable dataRchild;
        Comparable dataLchild;

        // Lets check if left child exists
        if (lChild < nSize ){
            dataLchild =  (Comparable)arr[lChild];
            if (dataLchild.compareTo(dataParent) > 0){
                dataParent = dataLchild;
                parent = lChild;
            }
        }

        // Lets check if right child exists
        if (rChild < nSize) {
            // IF left child was greater than parent, we compare right child with left child
            // else we compare with parent with right child
            dataRchild = (Comparable) arr[rChild];
            if (dataRchild.compareTo(dataParent) > 0) {
                parent = rChild;
            }
        }

        // SWAP values if parent has changed
        if (parent != index) {
            E swap = arr[index];
            arr[index] = arr[parent];
            arr[parent] = swap;
            subHeap(arr, arr.length, parent);
        }



    }

    public void addEntry (E entry) {
        if (numItems == data.length) {
            Object [] temp = new Object[data.length*2];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
        data[numItems] = entry;
        reHeapUp(numItems);
        numItems++;
    }

    public void reHeapUp(int n) {
        int x = n;
        Comparable d1 = (Comparable)data[x];
        Comparable d2 = (Comparable)data[parent(x)];
        while (x>0 && d1.compareTo(d2)>0) {
            E tmp = (E)data[x];
            data[x] = data[parent(x)];
            data[parent(x)] = tmp;
            x=parent(x);
            d1 = (Comparable)data[x];
            d2 = (Comparable)data[parent(x)];
        }
    }


    public E getTop () {
        if (numItems == 0) { return null; }
        E value = (E)data[0]; // save return value
        numItems--;
        data[0] = data[numItems];  // swap top & bottom
        reHeapDown(numItems); // restore heap
        return value;
    }

    public void reHeapDown (int n) {
        int current = 0, bigChild;
        boolean heapOK = false;
        Comparable d1, d2;
        while ((!heapOK) && (leftChild(current) < n)) {

            if (rightChild(current) >= n)
                bigChild = leftChild(current);
            else {
                d1 = (Comparable)data[leftChild(current)];
                d2 = (Comparable)data[rightChild(current)];
                if (d1.compareTo(d2) > 0) { bigChild = leftChild(current); }
                else { bigChild = rightChild(current); }
            }
            d1 = (Comparable)data[current];
            d2 = (Comparable)data[bigChild];
            if (d1.compareTo(d2) < 0) {
                E tmp = (E)data[current];
                data[current] = data[bigChild];
                data[bigChild] = tmp;
                current = bigChild;
            }
            else
                heapOK = true;
        } // end of while loop
    } // end of method

    public String toString() {
        String s="";
        String space ="  ";
        int lowPow;
        int highPow;
        int level =1;

        /// create 1st and 2nd level
        if (data.length > 0) {
            s = (E) data[0] + " \n";
        }

        for (int i = 1; i < data.length; i++ ){
            lowPow = (int) Math.pow(2, level) -1;
            highPow = (int) Math.pow(2, level+1)-2;
            /// If at the same level then add only newline
            if ( i >=  lowPow && i <= highPow){
                if ((E) data[i] != null ) {
                    s += space + (E) data[i] + "\n";
                }
            }else{
                // if level has changed, then add additional spaces and create newline
                level++;
                if ((E) data[i] != null ) {
                    // Add spaces
                    for (int j = 0; j < level * 2; j++) {
                        space += " ";
                    }
                    s += space + (E) data[i] + "\n";
                }
            }
        }

        return s;
    }
}

class HeapTester {
    public static void main (String [] args) {

        /// TESTING NEW CONSTRUCTOR
        Integer[] arr = {42,19,33,8,12,97,54,85,29,60,26,71};

        //Heap<Integer> hump1 = new Heap<Integer>();
        //for(int i = 1; i <= arr.length; i++){
        //    arr[i-1] = i;
        //}


        Heap<Integer> heapOne = new Heap<Integer>(arr);
        System.out.println(heapOne);

        int findValue = 100;
        System.out.println("Level of " + findValue + "  is - " + heapOne.getLevel (findValue));
        findValue = 12;
        System.out.println("Level of " + findValue + "  is - " + heapOne.getLevel (findValue));


        ///////////////////////////////////////////////////////////////////////////
        Heap<Integer> hump = new Heap<Integer>();
        Random rg = new Random();
        Scanner kb = new Scanner(System.in);
        System.out.println("Building heap with random numbers");
        System.out.print("Adding ");
        for (int x=0; x<20; x++) {
            int i = rg.nextInt(50);
            System.out.print(i + " ");
            hump.addEntry(i);
        }
        System.out.println();
        System.out.println("Complete heap:");
        System.out.println(hump);
        System.out.println("Enter a character to tear it down: ");
        kb.nextLine();
        System.out.println("And tearing it down");
        for (int x=0; x<20; x++) {
            System.out.print(hump.getTop());
            if (x < 19)
                System.out.print(",");
        }
        System.out.println();
    }
}

class PQheap<E> {
    private Heap<E> queue;
    public PQheap () {
        queue = new Heap<E>();
    }
    public void enqueue (E item){
        queue.addEntry(item);
    }
    public E dequeue () {
        return queue.getTop();
    }
    public void seeQueue() {
        System.out.println(queue);
    }

    public int numInFront(E value){
        ArrayList<E> items = new ArrayList<E>();
        E item;
        // Remove top item from queue
        while ((item = queue.getTop()) != null ){
            // Add the removed item to temp store - arraylist
            items.add(item);
            // Check if the value exists in the queue
            if ( (E)item == (E) value )  {
                // Add all the elements back to the queue
                for (int i =0; i < items.size(); i++){
                    queue.addEntry((E) items.get(i));
                }
                return ( items.size() - 1); // provides how many items ahead of the match
            }
        }

        //Add items back to the queue
        for (int i =0; i < items.size(); i++){
            queue.addEntry((E) items.get(i));
        }
        // Return -1 if match not found
        return -1;
    }

    // Implement peek(), get the top item from the Heap
    // Add it back on the Heap
    public E peek (){
        E item = queue.getTop();
        queue.addEntry(item);
        return item;
    }

    public static void main(String [] args) {
        PQheap<Integer> nums = new PQheap<Integer>();
        System.out.println("Enqueuing");
        for(int x=0; x<20; x++) {
            int tmp = (int)(Math.random()*10 + 1);
            System.out.println(" " + tmp);
            nums.enqueue(tmp);
           // nums.seeQueue();
        }
        System.out.println();
        nums.seeQueue();
        System.out.println(" PEEK - " + nums.peek());
        System.out.println(" How many numbers ahead of 9 - " + nums.numInFront(9));
        System.out.println("And now, in order of importance:");
        for(int x=0; x<20; x++) {
            System.out.println(" " +nums.dequeue());
        }
        System.out.println();
    }
}