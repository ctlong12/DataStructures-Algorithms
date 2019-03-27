
public abstract class HW7_Abstract_Class<E> {
	/**
	 * The following headers are for the methods requied in HW7.
	 * As each is recursive, it may require a private helper 
	 * method to accomplish its task.
	 * 
	 * NOTE - no global variables are required to solve these problems.
	 */
	/**
	 * Count the number of leaf nodes in a tree
	 * @return the count of leaf nodes
	 */
	public abstract int countLeafNodes();
	
	/**
	 * Compute tree height. The height of a tree is the length of
	 * the longest path from the root to the deepest node.
	 * The height of an empty tree (has no root) is -1. 
	 * The height of a tree with a single node (has only a root) - no paths, is 0.
	 * @return height of a tree
	 */
	public abstract int height();
	
	/** 
	 * Give an E value, if the value is in the tree,
	 * return the depth of the first occurrence of it you find,
	 * or return -1
	 * @param e the value searched for
	 * @return the depth of e or -1 if e is not in the tree
	 */
	public abstract int depthOf(E e);

}
