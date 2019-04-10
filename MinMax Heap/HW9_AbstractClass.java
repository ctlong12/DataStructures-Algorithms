
public abstract class HW8_AbstractClass<E> {
	
	// The data
	protected E[] MMHeap; // Pointer to the heap array
	protected int capacity;   // Maximum size of the heap
	protected int n;     // Number of things now in heap	
	
	// The operations
	public abstract void buildheap();
	
	public abstract E findMin();
	
	public abstract E findMax();
	
	public abstract void deleteMin();
	
	public abstract void deleteMax();
	
	public abstract void insert(E key);

}
