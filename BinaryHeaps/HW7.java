/*
Chandler Long
HW7
Similar to Homework one, this program consists of two different ways to solve the famous selection problem.
Unlike Homework One, we will be solving this using a priority queue and recording the time it took.
 */

public class HW7 extends HW7_AbstractClass {

    static HW7 heap = new HW7();

    /*
    The first algorithm sorts all the elements into a priority queue, from there we will delete the
    smallest value and assign it to a variable until the kth value is reached.
    PRECAUTION: None
     */

    public int selection1(int N) {
        int k = N / 2;
        int kSmallest = 0;

        //Set up Heap
        BinaryHeap<Integer> myHeap = new BinaryHeap<>();
        for( int i = 0; i <= N; i++) {
            int num = (int) (Math.random() * 100);
            myHeap.insert(num);
        }
        //Assign heap.a to myHeap
        heap.a = myHeap;

        //Loop through and assign the min value to kSmallest each time
        for (int i = 0; i < k; i++) {
            try {
                kSmallest = heap.a.deleteMin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return kSmallest;
    }

    /*
      The second algorithm sorts all the elements into an array, then stores the first k elements in
      a priority queue. from there we will compare the elements in the array vs the heap. If the element
      in the array is smaller than the findMin() of the heap. We will insert that element.
      PRECAUTION: None
    */
    public int selection2(int N) {
        //Establish array and heap
        BinaryHeap<Integer> myHeap = new BinaryHeap<>();
        int numArray[] = new int[N];

        int k = N / 2;

        //Set up array
        for(int i = 0; i < N; i++) {
            int num = (int) (Math.random() * 100);
            numArray[i] = num;
        }
        //Set up heap
        for(int i = 0; i < k + 1; i++) {
                myHeap.insert(numArray[i]);
        }

        heap.a = myHeap;

        int kSmallest = 0;

        //Start comparing elements
        for (int i = k - 1; i < N; i++) {
            try {
                //If the element is larger than the element in heap.a, insert numArray[i] into heap
                if (numArray[i] > heap.a.findMin()) {
                    heap.a.deleteMin();
                    heap.a.insert(numArray[i]);
                    kSmallest = heap.a.findMin();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return kSmallest;
    }

    /*
      In the main method, we will time the two algorithms. We will also declare the number of elements.
      PRECAUTION: None
    */

    public static void main(String args[]) {
        int numberOfItems = 500000;

        //Algorithm One!
        System.out.println("ALGORITHM ONE!");
        long algorithmOneStartTime = System.currentTimeMillis();
        System.out.println("kth (" + (numberOfItems / 2) + ") smallest element is: " + heap.selection1(numberOfItems));
        long algorithmOneEndTime = System.currentTimeMillis();

        System.out.println("Algorithm One Took " + (algorithmOneEndTime - algorithmOneStartTime) / 1000.0 + " seconds\n");

        //Algorithm Two!
        System.out.println("ALGORITHM TWO!");
        long AlgorithmTwoStartTime = System.currentTimeMillis();
        System.out.println("kth (" + (numberOfItems / 2) + ") smallest element is: " + heap.selection2(numberOfItems));
        long AlgorithmTwoEndTime = System.currentTimeMillis();

        System.out.println("Algorithm One Took " + (AlgorithmTwoEndTime - AlgorithmTwoStartTime) / 1000.0 + " seconds");

    }

}
