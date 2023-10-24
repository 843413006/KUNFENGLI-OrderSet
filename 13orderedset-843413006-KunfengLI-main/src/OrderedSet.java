//KUNFENGLI
//Description:The OrderedSet is a generic collection class that utilizes a binary search tree
//structure to store elements. This collection ensures each element is unique and offers 
//functionalities for insertion, deletion, searching, and set operations like intersection, union, and subset. 
//Each element must implement the Comparable interface to ensure proper ordering and comparison.
/*
 Develop class OrderedSet<E extends Comparable<E>> as a generic collection 
 that stores nodes in a binary search tree data structure. Each node has
 a reference to data, a left binary search tree, and a right binary search 
 tree. The type to be stored is limited to those that implement interface 
 Comparable interface or any interface that extends Comparable. Construct an
 OrderedSet of elements where the elements are not comparable is not possible.
 
 @authors Rick Mercer and YOUR NAME
 */

public class OrderedSet<T extends Comparable<T>> {

	// A private class that stores one node in a Binary Search Tree
	private class TreeNode {

		private TreeNode right;
		private T data;
		private TreeNode left;

		public TreeNode(T element) {
			left = null;
			data = element;
			right = null;
		}
	} // end class TreeNode

	private TreeNode root;
	private int n;

	// Create an empty OrderedSet
	public OrderedSet() {
		root = null;
		n = 0;
	}

	/*
	 * Insert an element to this OrderedSet and return true keeping this an
	 * OrderedSet. element is already exists, do not change this OrderedSet, return
	 * false.
	 */
	public boolean insert(T element) {
		// TODO: Implement this method
		if (root == null) {
			root = new TreeNode(element);
			n++;
			return true;
		} else {
			return insertRec(root, element);
		}
	}

	private boolean insertRec(TreeNode current, T element) {
		if (element.compareTo(current.data) < 0) {
			if (current.left == null) {
				current.left = new TreeNode(element);
				n++;
				return true;
			} else {
				return insertRec(current.left, element);
			}
		} else if (element.compareTo(current.data) > 0) {
			if (current.right == null) {
				current.right = new TreeNode(element);
				n++;
				return true;
			} else {
				return insertRec(current.right, element);
			}
		} else {
			return false; // element already exists
		}
	}

	/*
	 * Return the number of elements in this OrderedSet, which should be 0 when
	 * first constructed. This may run O(n) or O(1)--your choice.
	 */
	public int size() {
		// TODO: Implement this method
		return n;
	}

	/*-
	 * Return one string that concatenates all elements in this OrderedSet as
	 * they are visited in order. Elements are separated by spaces as in "1 4 9" 
	 * for this OrderedSet:
	 *    4
	 *   / \
	 *  1   9
	 */
	public String toStringInorder() {
		// TODO: Implement this method
		StringBuilder result = new StringBuilder();
		inorderRec(root, result); // 修改这里
		return result.toString().trim();
	}

	private void inorderRec(TreeNode current, StringBuilder sb) {
		if (current != null) {
			inorderRec(current.left, sb);
			sb.append(current.data).append(" ");
			inorderRec(current.right, sb);
		}
	}

	/*
	 * Return true is search equals an element in this OrderedSet, false if not.
	 */
	public boolean contains(T search) {
		// TODO: Implement this method
		return containsRec(root, search);
	}

	private boolean containsRec(TreeNode current, T search) {
		if (current == null) {
			return false;
		}
		if (search.compareTo(current.data) < 0) {
			return containsRec(current.left, search);
		} else if (search.compareTo(current.data) > 0) {
			return containsRec(current.right, search);
		} else {
			return true; // found the element
		}
	}

	/*
	 * Return the element in this OrderedSet that is greater than all other
	 * elements. If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T max() {
		// TODO: Implement this method
		if (root == null) {
			return null;
		}
		TreeNode current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current.data;
	}

	/*
	 * Return the element in this OrderedSet that is less than all other elements.
	 * If this OrderedSet is empty, return null. No recursion needed.
	 */
	public T min() {
		// TODO: Implement this method
		if (root == null) {
			return null;
		}
		TreeNode current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.data;
	}

	/*
	 * Return the intersection of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The
	 * intersection of two sets is the set of elements that are in both sets. The
	 * intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
	 */
	public OrderedSet<T> intersection(OrderedSet<T> other) {
		// TODO: Implement this method
		OrderedSet<T> result = new OrderedSet<T>();
		intersectionRec(root, other, result);
		return result;
	}

	private void intersectionRec(TreeNode current, OrderedSet<T> other, OrderedSet<T> result) {
		if (current != null) {
			if (other.contains(current.data)) {
				result.insert(current.data);
			}
			intersectionRec(current.left, other, result);
			intersectionRec(current.right, other, result);
		}
	}

	/*
	 * Return the union of this OrderedSet and the other OrderedSet as a new
	 * OrderedSet. Do not modify this OrderedSet or the other OrderedSet. The union
	 * of two sets is the set all distinct elements in the collection.[ The union of
	 * {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
	 */
	public OrderedSet<T> union(OrderedSet<T> other) {
		// TODO: Implement this method
		OrderedSet<T> result = new OrderedSet<T>();
		unionRec(root, result);
		unionRec(other.root, result);
		return result;
	}

	private void unionRec(TreeNode current, OrderedSet<T> result) {
		if (current != null) {
			result.insert(current.data);
			unionRec(current.left, result);
			unionRec(current.right, result);
		}
	}

	/*
	 * Return an OrderedSet that contains all elements greater than or equal to the
	 * first parameter (inclusive) and less than the second parameter (exclusive).
	 */
	public OrderedSet<T> subset(T inclusive, T exclusive) {
		// TODO: Implement this method
		OrderedSet<T> result = new OrderedSet<T>();
		subsetRec(root, inclusive, exclusive, result);
		return result;
	}

	private void subsetRec(TreeNode current, T from, T to, OrderedSet<T> result) {
		if (current != null) {
			if (from.compareTo(current.data) <= 0 && to.compareTo(current.data) > 0) {
				result.insert(current.data);
			}
			if (from.compareTo(current.data) < 0) {
				subsetRec(current.left, from, to, result);
			}
			if (to.compareTo(current.data) > 0) {
				subsetRec(current.right, from, to, result);
			}
		}
	}

	/*-
	* If element equals an element in this OrderedSet, remove it and return
	* true. Return false whenever element is not found. In all cases, this
	* OrderedSet must remain a true OrderedSet. Here is one recommended algorithm
	
	https://drive.google.com/file/d/1yjnYeIufsY1EgqJvaQ1nOXC627ZWauVa/view?usp=sharing
	
	* This algorithm should be O(log n)
	*/
	public boolean remove(T element) {
		int initialSize = n; // store the initial size
		root = removeRec(root, element);
		return n < initialSize;
	}

	private TreeNode removeRec(TreeNode current, T element) {
		if (current == null) {
			return null;
		}

		if (element.compareTo(current.data) < 0) {
			current.left = removeRec(current.left, element);
		} else if (element.compareTo(current.data) > 0) {
			current.right = removeRec(current.right, element);
		} else {
			if (current.left == null) {
				n--;
				return current.right;
			} else if (current.right == null) {
				n--;
				return current.left;
			}
			current.data = min(current.right);
			current.right = removeRec(current.right, current.data);
		}
		return current;
	}

	private T min(TreeNode root) {
		T minValue = root.data;
		while (root.left != null) {
			minValue = root.left.data;
			root = root.left;
		}
		return minValue;
	}
}