import org.w3c.dom.Node;

// Binary Search Tree implementation
public class BST<E extends Comparable<? super E>> extends HW7_Abstract_Class<E> {
  private BSTNode<E> root; // Root of the BST
  private int nodecount; // Number of nodes in the BST

  // constructor
  BST() { root = null; nodecount = 0; }

    // Reinitialize tree
  public void clear() { root = null; nodecount = 0; }

  // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(E e) {
    root = inserthelp(root, e);
    nodecount++;
  }
  
  private BSTNode<E> inserthelp(BSTNode<E> rt, E value) {
	  if(rt == null) return new BSTNode<E>(value) ;
	  if (rt.value().compareTo(value) >= 0)
		  // rt value > key
		  rt.setLeft(inserthelp(rt.left(), value));
	  else // rt value <= leu
		  rt.setRight(inserthelp(rt.right(), value));
	  return rt;
  }

  // Remove a record from the tree
  // key: The key value of record to remove
  // Returns the record removed, null if there is none.
  public E remove(E key) {
    E temp = findhelp(root, key); // First find it
    if (temp != null) {
      root = removehelp(root, key); // Now remove it
      nodecount--;
    }
    return temp;
  }
  
  private BSTNode<E> removehelp(BSTNode<E> rt, E key) {
	  if (rt == null) return null;
	  if (rt.value().compareTo(key) > 0)
		  rt.setLeft(removehelp(rt.left(),key));
	  else if (rt.value().compareTo(key) < 0)
		  rt.setRight(removehelp(rt.right(), key));
	  else { // found it
		  if (rt.left() == null) return rt.right();
		  else if (rt.right() == null) return rt.left();
		  else { // two children
			  BSTNode<E> temp = getmax(rt.left());
			  rt.setValue(temp.value());
			  rt.setLeft(deletemax(rt.left()));
		  }
	  }
	  return rt;
  }
  
  private BSTNode<E> deletemax(BSTNode<E> rt){
	  if (rt.right() == null)  return rt.left();
	  rt.setRight(deletemax(rt.right()));
	  return rt;
  }
  
  private BSTNode<E> getmax(BSTNode<E> rt){
	  if (rt.right() == null) return rt;
	  return getmax(rt.right());  
  }

  // Return the record with key value k, null if none exists
  // key: The key value to find
  public E find(E key) { return findhelp(root, key); }

  private E findhelp(BSTNode<E> rt, E key) {
	  if(rt == null) return null;
	  if (rt.value().compareTo(key) > 0)
		  // rt value > key
		  return findhelp(rt.left(), key);
	  else if (rt.value().compareTo(key) == 0)
		  // rt value is key
		  return rt.value();
	  else return findhelp(rt.right(), key);
  }
  
  // Return the number of records in the dictionary
  public int size() { return nodecount; }


    /**
     * Helper method for calculating the number of leaf nodes in a binary search tree.
     * @param node - takes in a root node<E>
     * @return - returns an int representing the number of leaf nodes in the tree.
     */
  private int getNumberOfLeafs(BSTNode<E> node) {
      if (node == null)
          return 0;
      if (node.left() == null && node.right() == null)
          return 1;
      else
          return getNumberOfLeafs(node.right()) + getNumberOfLeafs(node.left());
  }

    /**
     * Helper method for calculating the height of a binary search tree.
     * @param node - takes in a root node<E>
     * @return - returns an int value representing the height of the tree
     */
    private int getHightOfTree(BSTNode<E> node) {
        if (node == null)
            return 0;
        else {
            int leftSubTree = getHightOfTree(node.left());
            int rightSubTree = getHightOfTree(node.right());

            return Math.max(leftSubTree, rightSubTree) + 1;
        }
    }

    /**
     * Helper method for calculating the depth of a particular node
     * @param e - takes in a node value representing the node we want the height of
     * @param node - takes in a root node<E>
     * @param depth - take in the current depth which will accumulated per call
     * @return - returns an int value representing the depth of the node we are searching for
     */
    private int depthOfNodeHelper(E e, BSTNode<E> node, int depth) {
        if (node.left() == null && node.right() == null) {
            return -1;
        } else if (node.value() == e) {
            return depth;
        } else {
            if (e.compareTo(node.value()) <= 0) {
                return depthOfNodeHelper(e, node.left(), depth + 1);
            } else {
                return depthOfNodeHelper(e, node.right(), depth + 1);
            }
        }
    }

    /**
     * Method which calls the helper method for calculating the number of leaf nodes
     * @return - returns a recursive call representing the number of leaf nodes
     */
    public int countLeafNodes() {
      return getNumberOfLeafs(root);
    }
    /**
     * Method which calls the helper method for calculating the height of the tree
     * @return - returns a recursive call representing the height
     */
    public int height() {
        return getHightOfTree(root);
    }
    /**
     * Method which calls the helper method for calculating the depth of a particular node
     * @return - returns a recursive call representing the depth of the node
     */
    public int depthOf(E o) {
        return depthOfNodeHelper(o, root, 0);
    }
}