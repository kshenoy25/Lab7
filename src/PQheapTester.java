import java.util.Random;
/*
 * A little tester class to demonstrate use of heap
 * as priority queue. In this instance, the data item
 * is its own priority
 */

public class PQheapTester
{
    public static void main(String [] args)
    {
        PQheap<Integer> nums = new PQheap<Integer>();
        Random rg = new Random();
        System.out.println("Enqueuing");
        for(int x=0; x<20; x++) {
            int tmp = rg.nextInt(10)+1;
            System.out.println(" " + tmp);
            nums.enqueue(tmp);
            nums.seeQueue();
        }
        System.out.println();
        System.out.println("And now, in order of importance:");
        for(int x=0; x<20; x++) {
            System.out.print(" " +nums.dequeue());
        }
        System.out.println();
    }
}