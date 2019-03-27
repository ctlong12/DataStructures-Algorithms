// The start of the DoublyLinkedList class, discussed
// in the 230 text
// Currently "implements List" is commented out because otherwise,
// this does not compile because most of the List methods
// are not yet implemented

/**
 * This class creates a double linked list and then forms different methods in order to interact with the list
 * Methods will either return a boolean value, void or int.
 */
public class DoublyLinkedList<E> implements List<E> {
	
	private Link<E> head;
	private Link<E> tail;
	private Link<E> curr;
	private int listSize; 

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
  
	public static void main(String[] args) {
		
	}

	/**
	 * The clear method will clear out all the nodes in the double linked list
	 */
	@Override
	public void clear() {
		head.setNext(tail);
		tail.setPrev(head);
		listSize = 0;
	}

	/**
	 * The insert method will insert a new node with a values at the beginning of the double
	 * linked list nd return true when the process is complete
	 * @param it takes in a value of type int
	 */
	@Override
	public boolean insert(E it) {
		curr = new Link(it, curr.prev(), curr);
		curr.prev().setNext(curr);
		curr.next().setPrev(curr);
		listSize++;
		return true;
	}

	/**
	 * The append method will append a new node with a specific value to the end of the
	 * double linked list
	 * @param it takes in a value of type int
	 */
	@Override
	public boolean append(E it) {
		tail.setPrev(new Link(it, tail.prev(), tail));
		tail.prev().prev().setNext(tail.prev());
		if (curr == tail) {
			curr = tail.prev();
		}
		listSize++;
		return true;
	}

	/**
	 * The remove method will remove the current node from the double linked list
	 */
	@Override
	public E remove() {
		if (curr == tail) { return null; }
		Object it = curr.element();
		curr.prev().setNext(curr.next());
		curr.prev().setPrev(curr.prev());
		curr = curr.next();
		listSize--;
		return (E) it;
	}

	/**
	 * The move to start method will move the current position to the start of the
	 * double linked list
	 */
	@Override
	public void moveToStart() {
		curr = head.next();

	}

	/**
	 * The move to end method will move the current position to the end of the
	 * double linked list
	 */
	@Override
	public void moveToEnd() {
		curr = tail.prev();
	}

	/**
	 * The prev method will move the current position one step back to its previous node
	 */
	@Override
	public void prev() {
		if (curr.prev() != head) {
			curr = curr.prev();
		}
	}

	/**
	 * The next method will move the current position one step forward to its next node
	 */
	@Override
	public void next() {
		if (curr.next() != tail) {
			curr = curr.next();
		}
	}

	/**
	 * The length method will return the number of nodes in the double linked list
	 */
	@Override
	public int length() {
		return listSize;
	}

	/**
	 *The currPos method will return the current position amongst the double linked list, -1 will be
	 * returned if the current position does not exist
	 */
	@Override
	public int currPos() {
		int counter = 0;
		Link<E> walker = head;

		while (walker != null) {
			if (walker == curr) {
				return counter;
			}
			counter++;
			walker = walker.next();
		}
		return -1;
	}

	/**
	 *The moveToPos method will move the current position to a specific point in
	 * the double linked list.
	 * The method will return true if the method was executed properly.
	 * @param pos takes in a int representing the position to be moved to
	 */
	@Override
	public boolean moveToPos(int pos) {
		int counter = 0;
		curr = head;
		if (pos < listSize) {
			while (counter != pos) {
				curr = curr.next();
				counter++;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 *The isAtEnd method will return true if the current position is at tht end of the
	 * double linked list and false otherwise
	 */
	@Override
	public boolean isAtEnd() {
		if (tail.prev() == curr) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *This method will return the value on the associated node in the double linked list
	 */
	@Override
	public E getValue() {
		return curr.element();
	}

	/**
	 *The isEmpty method will return true if the double linked list is empty
	 */
	@Override
	public boolean isEmpty() {
		if (listSize == 0) {
			return true;
		} else {
			return false;
		}
	}
}
