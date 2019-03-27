// The start of the DoublyLinkedList class, discussed
// in the 230 text
// Currently "implements List" is commented out because otherwise,
// this does not compile because most of the List methods
// are not yet implemented
public class DoublyLinkedList<E> implements List<E> {

	protected Link<E> head;
	protected Link<E> tail;
	protected Link<E> curr; // points to a element or tail, never head
	protected int listSize;

	// Initialize an empty list
	public DoublyLinkedList() {
		head = new Link<E>(null, null,null);
		tail = new Link<E>(null, head, null);
		head.setNext(tail);
		curr = tail;
		listSize = 0;
	}

	// return a String representation of list, starting at head working
	// toward tail
    public String toString() {
		Link<E> temp = head.next();
		String out = "";
		while (temp != tail) {
			out += temp.element() + ((temp.next() != tail) ? ", " : "");
			temp = temp.next();
		}
		return out;
	  }

    public void clear() {
    	head = new Link(null, null, null);
    	tail = curr = new Link(head, null, null);
    	head.setNext(tail);
    	listSize = 0;
    };

    // Insert "it" at the current location
    // The client must ensure that the list's capacity is not exceeded
    public boolean insert(E it) {

    	curr = new Link<>(it, curr.prev(), curr);
    	curr.prev().setNext(curr);
    	curr.next().setPrev(curr);
    	listSize++;
    	return true; };

    // Append "it" at the end of the list
    // The client must ensure that the list's capacity is not exceeded
    public boolean append(E it) {
    	tail.setPrev(new Link<>(it, tail.prev(), tail));
    	tail.prev().prev().setNext(tail.prev());
    	if (curr == tail) curr = tail.prev();
    	listSize++;
    	return true;}

    // Remove and return the current element
    public E remove() {
    	if (curr == tail) return null;
    	E it = curr.element();
    	curr.next().setPrev(curr.prev());
    	curr.prev().setNext(curr.next());
    	curr = curr.next();
    	listSize--;
    	return it;
    }

    // Set the current position to the start of the list
    // which is the first link in list unless empty
    public void moveToStart() {
    	curr = head.next();
    };

    // Set the current position to the end of the list
    public void moveToEnd() {
    	curr = tail;
    };

    // Move the current position one step left, no change if already at beginning
    public void prev() {
    	if (curr != head.next())
    		curr = curr.prev();
    };

    // Move the current position one step right, no change if already at end
    public void next() {
    	if (curr != tail)
    		curr = curr.next();
    };

    // Return the number of elements in the list
    public int length() {
    	return listSize;
    }

    // Return the (index) position of the current element
    public int currPos() {
    	// must count to find position from start
    	int where = 0;
    	Link walker = head.next();
    	while (walker != curr) {
    		walker = walker.next();
    		where++;
    	}
    	return where;
    }

    // Set the current position to "pos"
    public boolean moveToPos(int pos) {

	    if ((pos < 0) || (pos > listSize)) return false;
	    if (pos <= listSize/2) {
	        // walk to position, starting at head
		    curr = head.next();
		    for(int i=0; i<pos; i++) curr = curr.next();
	    }
	    else { // walk to position from tail, it's closer
	    	curr = tail;
		    for(int i=listSize; i>pos; i--) curr = curr.prev();
	    }
	    return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() {
    	return curr == tail;
    }

    // Return the current element
    public E getValue() {
    	return curr.element();
    }

    // Tell if the list is empty or not
    public boolean isEmpty() {
    	return listSize == 0;
    }

	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.append("hello");
		list.append("there");
		list.append("Monday");
		System.out.println(list);
		System.out.println(list.currPos());
		list.moveToEnd();
		System.out.println(list.currPos());
		System.out.println(list);
		list.moveToPos(1);
		System.out.println(list.currPos());
		System.out.println(list.getValue());
		list.remove();
		System.out.println(list);
		System.out.println(list.currPos());
		System.out.println(list.getValue());


	}
}
