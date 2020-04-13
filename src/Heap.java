/**
 * Implements ADT Heap
 *
 * @author Cate Sheller 
 * @version 29 October 2018
 */

/*
public class Heap<E>
{
    // invariant: data contains the data in an array, arranged according
    // to heap rules; numItems keeps track of the number of items in the heap
    private Object [] data;
    private int numItems;
    public static int DEFAULT_SIZE = 31;

    public Heap() {
        data = new Object[DEFAULT_SIZE];
        numItems = 0;
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
        if (numItems == 0) {
            return null;
        }
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
        while ((!heapOK) && (leftChild(current) < n))
        {
            if (rightChild(current) >= n)
                bigChild = leftChild(current);
            else
            {
                d1 = (Comparable)data[leftChild(current)];
                d2 = (Comparable)data[rightChild(current)];
                if (d1.compareTo(d2) > 0)
                    bigChild = leftChild(current);
                else
                    bigChild = rightChild(current);
            }
            d1 = (Comparable)data[current];
            d2 = (Comparable)data[bigChild];
            if (d1.compareTo(d2) < 0)
            {
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
        Object[] copy = data.clone();
        for (Object value: copy) {
            if (value != null) {
                s = s + (E)value + " ";
            }
        }
        return s;
    }
}

 */