/**
 * Chandler Long
 * Data Structures and Algorithms
 * The following program will take a double linked list and sort its contents.
 * After sorting the contents of the double linked list a remove methods is implemented
 * that removes any duplicate values amongst the double linked list.
 */



public class HW4<E extends Comparable<? super E>> extends DoublyLinkedList<E>{
    // SelfSorting lists will only store Comparables - objects that can be compared by the 
	// compareTo method
	/**
	 * constructor
	 */
	public SelfSortingListHW4() {
		super();
	}
	/**
	 * swap the contents of two given links
	 * @param one
	 * @param two
	 */
	private void swap(Link<E> one, Link<E> two) {
		Link<E> temp = new Link(null, null, null);
		temp.setElement(one.element());
		one.setElement(two.element());
		two.setElement(temp.element());
	}
	
	/**
	 * mimics an insertion sort - no arrays can be used
	 * The sort method will take a double linked list and sort its contents in a
	 * similar to the process of an insertion sort
	 */
	public void sort() {
		
		if (listSize <= 1) {
			return;
		} else {
			Link<E> link1 = head.next();
			Link<E> linkToFix = link1.next();
			Link<E> walker = linkToFix;

			while (walker != tail) {
				if (walker == link1 || walker.element().compareTo(walker.prev().element()) >= 0) {
					linkToFix = linkToFix.next();
					walker = linkToFix;

				} else {
					swap(walker.prev(), walker);
					walker = walker.prev();
				}
			}
		}
	}

	/**
	 * The remove duplicates method will take a sorted doubly linked list
	 * and remove any duplicate values within it.
	 */
	
	public void removeDuplicates() {
		if (listSize <= 1) {
			return;
		} else {
			Link<E> walker = head.next();
			while (walker != tail.prev()) {
				if (walker.element().compareTo(walker.next().element()) == 0) {
					removeNode(walker);
					listSize--;
				}
				walker = walker.next();
			}
		}
	}

	/**
	 * The helper method removeNode will remove a node from the designated position
	 * @param walker - The link walker will take in the position of the node we wish to swap
	 */

	public void removeNode(Link<E> walker) {
		walker.next().setPrev(walker.prev());
		walker.prev().setNext(walker.next());
	}
	
	/**
	 * This main begins to test your code
	 */
	public static void main(String[] args) {
		SelfSortingListHW6<Integer> s = new SelfSortingListHW6<>();
		s.append(330);
		s.append(12);
		s.append(4);
		s.append(12);
		s.append(330);
		s.append(-14);
		s.append(4);
		s.append(4);
		s.append(330);
		s.append(-14);
		System.out.println(s);
		// Output of above line should be: 12, 330, 4, 12, 330, -14, 4, 4, 330, -14
		s.sort();
	    System.out.println(s);	
		// Output of above line should be: -14, -14, 4, 4, 4, 12, 12, 330, 330, 330
		s.removeDuplicates();
		System.out.println(s);
		// Output of above line should be: -14, 4, 12, 330

	}
	
}
