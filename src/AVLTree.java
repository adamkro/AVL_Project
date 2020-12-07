import com.sun.deploy.security.SelectableSecurityManager;

import java.util.ArrayList;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 *
 */
//CHECK IF WE NEED A TREE CONSTRUCTOR OR ADD FIRST ROOT WITH INSERT
//IAVL AND AVL WERE PUBLIC AND WE CHANGED IT
//SHOULD WE COUNT SIZEABOVE STEPS WHEN NO BALANCE NEEDED??

public class AVLTree {
	private IAVLNode root = null;
	private IAVLNode min = null;
	private IAVLNode max = null;

	/**
	 * public boolean empty()
	 * <p>
	 * returns true if and only if the tree is empty
	 * complexity - O(1)
	 */
	public boolean empty() {
		return root == null;
	}

	/**
	 * public IAVLNode getMin()
	 * <p>
	 * return tree's min
	 * complexity - O(1)
	 */
	public IAVLNode getMin() {
		return this.min;
	}

	/**
	 * private void setMin(IAVLNode node)
	 * <p>
	 * set node as tree's min
	 * complexity - O(1)
	 */
	private void setMin(IAVLNode node) {
		this.min = node;
	}

	/**
	 * public IAVLNode getMax()
	 * <p>
	 * return tree's max
	 * complexity - O(1)
	 */
	public IAVLNode getMax() {
		return this.max;
	}

	/**
	 * private void setMax(IAVLNode node)
	 * <p>
	 * set node as tree's max
	 * complexity - O(1)
	 */
	private void setMax(IAVLNode node) {
		this.max = node;
	}

	/**
	 * public String search(int k)
	 * <p>
     * returns the info of an item with key k if it exists in the tree
	 * otherwise, returns null
	 * complexity - O(log n)
	 */
	public String search(int k) {
		IAVLNode node = searchNode(k);
		if (node != null)
			return node.getValue();
		return null;
	}


	/**
	 * public void updateSizeAbove(IAVLNode node)
	 * <p>
	 * update size field from node to root
	 * complexity - O(log n)
	 */
	public void updateSizeAbove(IAVLNode node) {
		while (node != null) {
			node.updateSize();
			node = node.getParent();
		}
	}
	/**
	 * public IAVLNode searchNode(int k)
	 * <p>
	 * returns the node with key k if it exists in the tree
	 * otherwise, returns null
	 * complexity - O(log n)
	 */
	public IAVLNode searchNode(int k) {
		if (this.empty()) return null;
		IAVLNode node = root;
		while (node.isRealNode()) {
			if (node.getKey() == k)
				return node;
			if (node.getKey() > k)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		return null;
	}
	/**
	 * public int insert(int k, String i)
	 * <p>
	 * inserts an item with key k and info i to the AVL tree.
	 * the tree must remain valid (keep its invariants).
	 * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
	 * promotion/rotation - counted as one rebalnce operation, double-rotation is counted as 2.
	 * returns -1 if an item with key k already exists in the tree.
	 * complexity - O(log n)
	 */
	public int insert(int k, String i) {
		IAVLNode node = new AVLNode(k, i);
		if (this.empty()) { // initialize new tree
			this.setRoot(node);
			this.setMin(node);
			this.setMax(node);
			return 0;
		}
		if (this.search(k) != null) return -1; // CHECK IF = NULL IS OK
		IAVLNode parent = findParent(this.getRoot(), k);
		if (k < this.getMin().getKey()) //update min if needed
			this.setMin(node);
		if (k > this.getMax().getKey()) //update max if needed
			this.setMax(node);
		if (k < parent.getKey()) { // insert left
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
		if (parent.getHeight() == 0) { // CASE: NOT UNARY
			this.balance(parent, true);
		} else {
			this.updateSizeAbove(parent);
		}
		return 0;
	}

	/**
	 * private static int rankDifference(IAVLNode node)
	 * <p>
	 * returns rank(height) difference between node's left son and right son
	 * complexity - O(1)
	 */
	private static int rankDifference(IAVLNode node) {
		int leftHeight = node.getLeft().getHeight();
		int rightHeight = node.getRight().getHeight();
		return (leftHeight - rightHeight);
	}

	/**
	 * private void rotateRight(IAVLNode left, IAVLNode node)
	 * <p>
	 * right rotation between left and node
	 * complexity - O(1)
	 */
	private void rotateRight(IAVLNode left, IAVLNode node) {
		IAVLNode parent = node.getParent();
		node.setLeft(left.getRight());
		left.setRight(node);
		node.update();
		reConnectTree(left, parent);
	}

	/**
	 * private void rotateLeft(IAVLNode right, IAVLNode node)
	 * <p>
	 * left rotation between right and node
	 * complexity - O(1)
	 */
	private void rotateLeft(IAVLNode right, IAVLNode node) {
		IAVLNode parent = node.getParent();
		node.setRight(right.getLeft());
		right.setLeft(node);
		node.update();
		reConnectTree(right, parent);
	}

	/**
	 * private void reConnectTree(IAVLNode subTreeRoot, IAVLNode parent)
	 * <p>
	 * connects subTreeRoot as a child to parent
	 * complexity - O(1)
	 */
	private void reConnectTree(IAVLNode subTreeRoot, IAVLNode parent) {
		if (parent == null)
			this.setRoot(subTreeRoot);
		else if (parent.getKey() > subTreeRoot.getKey())
			parent.setLeft(subTreeRoot);
		else
			parent.setRight(subTreeRoot);
		subTreeRoot.update();
	}

	/**
	 * private static IAVLNode findParent(IAVLNode node, int k)
	 * <p>
	 * pre: k is not in node's subtree
	 * return node that should be k's parent if inserted
	 * complexity - O(log n)
	 */
	private static IAVLNode findParent(IAVLNode node, int k) {
		while (node.isRealNode()) {
			if (k > node.getKey()) {
				if (node.getRight().isRealNode())
					node = node.getRight();
				else
					return node;
			} else {
				if (node.getLeft().isRealNode())
					node = node.getLeft();
				else
					return node;
			}
		}
		return null;
	}

	/**
	 * public int delete(int k)
	 * <p>
	 * deletes an item with key k from the binary tree, if it is there;
	 * the tree must remain valid (keep its invariants).
	 * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
	 * demotion/rotation - counted as one rebalnce operation, double-rotation is counted as 2.
	 * returns -1 if an item with key k was not found in the tree.
	 * complexity - O(log n)
	 */
	public int delete(int k) {
		IAVLNode node = searchNode(k);
		if (node == null) {
			return -1;
		}
		IAVLNode nodeToBalance;
		if (node.getHeight() == 0)  // node is leaf
			nodeToBalance = simpleDelete(node);
		else if ((!node.getRight().isRealNode()) || (!node.getLeft().isRealNode())) // unary node
			nodeToBalance = simpleDelete(node);
		else //inner node
			nodeToBalance = replaceNode(node); //replace node with his successor

		int balanceSteps = this.balance(nodeToBalance, false); //balance
		if (k == this.getMin().getKey()) //find the new min
			this.calculateMin();
		if (k == this.getMax().getKey()) //find the new max
			this.calculateMax();
		return balanceSteps;
	}

	/**
	 * private void calculateMax()
	 * <p>
	 * get most right node and make it this tree's max
	 * complexity - O(log n)
	 */
	private void calculateMax() {
		if (this.empty()) {
			this.setMax(null);
			return;
		}
		IAVLNode node = this.getRoot();
		while (node.getRight().isRealNode())
			node = node.getRight();
		this.setMax(node);
	}

	/**
	 * private void calculateMin()
	 * <p>
	 * get most left node and make it this tree's min
	 * complexity - O(log n)
	 */
	private void calculateMin() {
		if (this.empty()) {
			this.setMin(null);
			return;
		}
		IAVLNode node = this.getRoot();
		while (node.getLeft().isRealNode())
			node = node.getLeft();
		this.setMin(node);
	}

	/**
	 * public IAVLNode replaceNode(IAVLNode node)
	 * <p>
	 * remove and replace inner node with his successor and update all involved nodes
	 * return node that needs balancing
	 * complexity - O(log n)
	 */
	public IAVLNode replaceNode(IAVLNode node) {
		IAVLNode successor = findSuccessor(node);
		IAVLNode nodeToBalance = simpleDelete(successor);
		successor.setRight(node.getRight());
		successor.setLeft(node.getLeft());
		successor.setParent(node.getParent());
		successor.setHeight(node.getHeight());
		if (node.getParent() == null)
			this.setRoot(successor);
		else {
			if (node.getIsRightChild())
				node.getParent().setRight(successor);
			else
				node.getParent().setLeft(successor);
		}
		node.nullify();
		if (nodeToBalance == node) //case that that successor is child of node
			nodeToBalance = successor;
		return nodeToBalance;
	}

	/**
	 * public IAVLNode simpleDelete(IAVLNode node)
	 * <p>
	 * remove unary node or leaf that exists in tree
	 * returns node's pre parent
	 * complexity - O(1)
	 */
	public IAVLNode simpleDelete(IAVLNode node) {
		IAVLNode parent = node.getParent();
		if (parent == null) { // node is root
			if (node.getHeight() == 0)
				this.setRoot(null);
			else if (node.getRight().isRealNode()) {
				this.setRoot(node.getRight());
				node.getRight().setParent(null);
			} else {
				this.setRoot(node.getLeft());
				node.getLeft().setParent(null);
			}
		} else { // node isn't root
			IAVLNode newChild;
			if (node.getRight().isRealNode())
				newChild = node.getRight();
			else
				newChild = node.getLeft();
			if (node.getIsRightChild())
				parent.setRight(newChild);
			else
				parent.setLeft(newChild);
		}
		node.nullify();
		return parent;
	}

	/**
	 * public IAVLNode findSuccessor(IAVLNode node)
	 * <p>
	 * pre: node is inner node and has a right child
	 * return it's successor
	 * complexity - O(log n)
	 */
	public IAVLNode findSuccessor(IAVLNode node) {
			node = node.getRight();
			while (node.getLeft().isRealNode())
				node = node.getLeft();
			return node;
	}

	/**
	 * private int balance(IAVLNode node, boolean isInsert)
	 * <p>
	 * balancing tree by rank difference as taught in class
	 * return count of operations
	 * complexity - O(log n)
	 */
	private int balance(IAVLNode node, boolean isInsert) {
		int count = 0;
		while (node != null) {
			int diff = rankDifference(node);
			switch (diff) {
				case 2:
					if (rankDifference(node.getLeft()) == -1) {
						IAVLNode left = node.getLeft();
						rotateLeft(left.getRight(), left);
						rotateRight(node.getLeft(), node);
						count += 5;
					} else {
						rotateRight(node.getLeft(), node);
						count += 2;
					}
					if (isInsert) {
						this.updateSizeAbove(node);
						node = null;
					} else
						node = node.getParent();
					break;
					case (-2):
					if (rankDifference(node.getRight()) == 1) {
						IAVLNode right = node.getRight();
						rotateRight(right.getLeft(), right);
						rotateLeft(node.getRight(), node);
						count += 5;
					} else {
						rotateLeft(node.getRight(), node);
						count += 2;
					}
					if (isInsert) {
						this.updateSizeAbove(node);
						node = null;
					} else
						node = node.getParent();
					break;
				default: //-1 or 1
					int preHeight = node.getHeight();
					node.update();
					int postHeight = node.getHeight();
					if (preHeight == postHeight) {
						this.updateSizeAbove(node);
						node = null;
						break;
					}
					count++;
			}
			if (node != null)
				node = node.getParent();

		}
		return count;
	}

	/**
	 * public String min()
	 * <p>
	 * Returns the info of the item with the smallest key in the tree,
	 * or null if the tree is empty
	 * complexity - O(1)
	 */
	public String min() {
		if (this.empty())
			return null;
		return this.getMin().getValue();
	}

	/**
	 * public String max()
	 * <p>
	 * Returns the info of the item with the largest key in the tree,
	 * or null if the tree is empty
	 * complexity - O(1)
	 */
	public String max() {
		if (this.empty())
			return null;
		return this.getMax().getValue();
	}

	/**
	 * public int[] keysToArray()
	 * <p>
	 * Returns a sorted array which contains all keys in the tree,
	 * or an empty array if the tree is empty.
	 * complexity - O(n)
	 */
	public int[] keysToArray() {
		if (this.empty())
			return new int[0];
		int[] arr = new int[this.size()];
		inOrder(arr, this.getRoot(), 0);
		return arr;
	}

	/**
	 * private int inOrder(int[] arr, IAVLNode node, int i)
	 * <p>
	 * traverse tree and update arr keys inorder
	 * complexity - O(n)
	 */
	private int inOrder(int[] arr, IAVLNode node, int i) {
		if (node.isRealNode()) {
			i = inOrder(arr, node.getLeft(), i);
			arr[i++] = node.getKey();
			i = inOrder(arr, node.getRight(), i);
		}
		return i;
	}
	/**
	 * private int inOrder(String[] arr, IAVLNode node, int i)
	 * <p>
	 * traverse tree and update arr values in keys order
	 * complexity - O(n)
	 */
	private int inOrder(String[] arr, IAVLNode node, int i) {
		if (node.isRealNode()) {
			i = inOrder(arr, node.getLeft(), i);
			arr[i++] = node.getValue();
			i = inOrder(arr, node.getRight(), i);
		}
		return i;
	}

	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree,
	 * sorted by their respective keys,
	 * or an empty array if the tree is empty.
	 * complexity - O(n)
	 */
	public String[] infoToArray() {
		if (this.empty())
			return new String[0];
		String[] arr = new String[this.size()];
		inOrder(arr, this.getRoot(), 0);
		return arr;
	}

	/**
	 * public int size()
	 * <p>
	 * Returns the number of nodes in the tree.
	 * <p>
	 * precondition: none
	 * postcondition: none
	 * complexity - O(1)
	 */
	public int size() {
		return this.getRoot().getSize();
	}

	/**
	 * public int getRoot()
	 * <p>
	 * Returns the root AVL node, or null if the tree is empty
	 * <p>
	 * precondition: none
	 * postcondition: none
	 * complexity - O(1)
	 */
	public IAVLNode getRoot() {
		return this.root;
	}

	/**
	 * private void setRoot(IAVLNode newRoot)
	 * <p>
	 * set newRoot as tree's root and update parent if needed
	 * complexity - O(1)
	 */
	private void setRoot(IAVLNode newRoot) {
		this.root = newRoot;
		if (newRoot != null)
			newRoot.setParent(null);
	}

	/**
	 * public AVLTree[] split(int x)
	 * <p>
	 * splits the tree into 2 trees according to the key x.
	 * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
	 * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
	 * postcondition: none
	 * complexity - O(log n)
	 */

	public AVLTree[] split(int x) {
		IAVLNode node_x = searchNode(x);
		AVLTree smaller = constructSubTree(node_x.getLeft());
		AVLTree bigger = constructSubTree(node_x.getRight());
		AVLTree[] res = new AVLTree[2];
		res[0] = smaller;
		res[1] = bigger;
		IAVLNode node = node_x.getParent();
		node_x.abandonThisChild();
		node_x.nullify();
		IAVLNode tmp;
		while (node != null) {
			tmp = node.getParent();
			node.abandonThisChild();
			IAVLNode left = node.getLeft();
			IAVLNode right = node.getRight();
			node.setLeft(new AVLNode());
			node.setRight(new AVLNode());
			node.setParent(null);
			node.update();
			if (node.getKey() < x) {
				smaller.join(node, constructSubTree(left)); //Make sure join takes care of node's children and parent
			} else {
				bigger.join(node, constructSubTree(right));
			}
			node = tmp;
		}
		res[0].calculateMin();
		res[0].calculateMax();
		res[1].calculateMin();
		res[1].calculateMax();
		return res;
	}

	//**************************************************
	//**************************************************
	//**************************************************
	//**************************************************
	//**************************************************
	//for measurements
	public int[] splitAndCount(int x) {
		int count = 0;
		int sum = 0;
		int max = 0;
		int curr = 0;
		IAVLNode node_x = searchNode(x);
		AVLTree smaller = constructSubTree(node_x.getLeft());
		AVLTree bigger = constructSubTree(node_x.getRight());
		AVLTree[] res = new AVLTree[2];
		res[0] = smaller;
		res[1] = bigger;
		IAVLNode node = node_x.getParent();
		node_x.abandonThisChild();
		node_x.nullify();
		IAVLNode tmp;
		while (node != null) {
			tmp = node.getParent();
			node.abandonThisChild();
			IAVLNode left = node.getLeft();
			IAVLNode right = node.getRight();
			node.setLeft(new AVLNode());
			node.setRight(new AVLNode());
			node.setParent(null);
			node.update();
			if (node.getKey() < x) {
				curr = smaller.join(node, constructSubTree(left)); //Make sure join takes care of node's children and parent
			} else {
				curr = bigger.join(node, constructSubTree(right));
			}
			sum += curr;
			count++;
			if (curr > max)
				max = curr;
			node = tmp;
		}
		res[0].calculateMin();
		res[0].calculateMax();
		res[1].calculateMin();
		res[1].calculateMax();
		int[] ans = new int[2];
		ans[0] = sum/count;
		ans[1] = max;
		return ans;
	}


	public int getMaxLeftSubTree(){
		IAVLNode node = this.getRoot().getLeft();
		while(node.getRight().isRealNode())
			node = node.getRight();
		return node.getKey();
	}






	//**************************************************
	//**************************************************
	//**************************************************
	//**************************************************
	//**************************************************
	/**
	 * public static AVLTree constructSubTree(IAVLNode node)
	 * <p>
	 * return tree with node as it's root
	 * complexity - O(1)
	 */
	public static AVLTree constructSubTree(IAVLNode node) {
		AVLTree tree = new AVLTree();
		if (node.isRealNode()) {
			tree.setRoot(node);
			node.setParent(null);
			tree.setMin(node);
			tree.setMax(node);
		}
		return tree;
	}


	/**
	 * public join(IAVLNode x, AVLTree t)
	 * <p>
	 * joins t and x with the tree.
	 * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
	 * precondition: keys(x,t) < keys() or keys(x,t) > keys(). t/tree might be empty (rank = -1).
	 * postcondition: none
	 * complexity - O(log n)
	 */

	public int join(IAVLNode x, AVLTree t) {
		int rank_t = -1;
		int rank_this = -1;
		if (!t.empty())
			rank_t = t.getRoot().getHeight();
		if (!this.empty())
			rank_this = this.getRoot().getHeight();

		if (t.empty() && this.empty()) { //both trees are empty
			this.setRoot(x);
			this.setMin(x);
			this.setMax(x);
			return 1;
		}
		else if (t.empty() || this.empty()) { //one of the trees are empty
			if (t.empty())
				joinEmpty(this,x);
			else {
				joinEmpty(t,x);
				this.setRoot(t.getRoot());
				this.setMax(t.getMax());
				this.setMin(t.getMin());
			}
			if (x.getKey() < this.getRoot().getKey())
				this.setMin(x);
			if (x.getKey() > this.getRoot().getKey())
				this.setMax(x);
		}
		else if (rank_this < rank_t) {
			t.joinNodes(this.getRoot(), t.getRoot(), x);
			this.newMaxMin(t);
			this.setRoot(t.getRoot());
		}
		else if (rank_this == rank_t || rank_this > rank_t) {
			this.joinNodes(t.getRoot(), this.getRoot(), x);
			this.newMaxMin(t);
		}
		x.update();
		// special case - one rotation if needed in the first balance step
		if (x.getParent() != null) {
			int diff = rankDifference(x.getParent());
			if (diff == 2)
				rotateRight (x.getParent().getLeft(), x.getParent());
			if (diff == -2)
				rotateLeft(x.getParent().getRight(), x.getParent());
		}
		//
		this.balance(x.getParent(), true); // Continues to balance the tree as insert
		if (rank_t == -1 || rank_this == -1){
			return Math.max(rank_this,rank_t) + 1; //Returns complexity
		}
		return Math.abs(rank_this - rank_t) + 1; //Returns complexity
	}

	/**
	 * private void newMaxMin(AVLTree t)
	 * <p>
	 * pre: t keys are all smaller or bigger than this tree
	 * update this tree's min/max fields when joining with t
	 * complexity - O(1)
	 */
	private void newMaxMin(AVLTree t) {
		if (this.getRoot().getKey() > t.getRoot().getKey())
			this.setMin(t.getMin());
		else
			this.setMax(t.getMax());
	}

	/**
	 * public void joinNodes(IAVLNode small_tree, IAVLNode big_tree, IAVLNode x)
	 * <p>
	 * pre: small_tree subtree height is lower than big_tree
	 * pre: small_tree subtree keys are all smaller or bigger than big_tree subtree	 *
	 * perform join as taught in class
	 * complexity - O(log n)
	 */
	public void joinNodes(IAVLNode small_tree, IAVLNode big_tree, IAVLNode x) {
		if (small_tree.getKey() > big_tree.getKey()) { //join right
			while (big_tree.getHeight() > small_tree.getHeight())
				big_tree = big_tree.getRight();
			x.setRight(small_tree);
			if (big_tree.getParent() != null) //not root
				big_tree.getParent().setRight(x);
			else
				this.setRoot(x); // x is root, case of rank_this == rank_t
			x.setLeft(big_tree);

		} else { //join left
			while (big_tree.getHeight() > small_tree.getHeight())
				big_tree = big_tree.getLeft();
			x.setLeft(small_tree);
			if (big_tree.getParent() != null) //not root
				big_tree.getParent().setLeft(x);
			else
				this.setRoot(x); // x is root, case of rank_this == rank_t
			x.setRight(big_tree);
		}
	}

	/**
	 * public void joinEmpty(AVLTree t, IAVLNode x)
	 * <p>
	 * pre: x key is bigger or smaller than all t keys
	 * insert x node to t
	 * complexity - O(log n)
	 */
	public void joinEmpty(AVLTree t, IAVLNode x) {
		IAVLNode node = t.getRoot();
		if (x.getKey() > node.getKey()){
			while (node.getHeight() >= 0)
				node = node.getRight();
			node.getParent().setRight(x);
			x.setLeft(node);
		} else { //join left
			while (node.getHeight() >= 0)
				node = node.getLeft();
			node.getParent().setLeft(x);
			x.setRight(node);
		}

	}

}
	/**
	   * public interface IAVLNode
	   * ! Do not delete or modify this - otherwise all tests will fail !
	   */
	interface IAVLNode{
		public int getKey(); //returns node's key (for virtuval node return -1)
		public String getValue(); //returns node's value [info] (for virtuval node return null)
		public void setLeft(IAVLNode node); //sets left child
		public IAVLNode getLeft(); //returns left child (if there is no left child return null)
		public void setRight(IAVLNode node); //sets right child
		public IAVLNode getRight(); //returns right child (if there is no right child return null)
		public void setParent(IAVLNode node); //sets parent
		public IAVLNode getParent(); //returns the parent (if there is no parent return null)
		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node
    	public void setHeight(int height); // sets the height of the node
    	public int getHeight(); // Returns the height of the node (-1 for virtual nodes)
		void setIsRightChild(boolean b); // set b as isRightChild value
		boolean getIsRightChild(); // returns if node is a right child
		int getSize(); // returns node's size
		void updateSize(); // calculate and update node's size
		void update(); // calculate and update node's size and height
		void nullify(); // set parent, left and right null
		void abandonThisChild(); // replace node with a virtual node as parent's child
	}

   /**
   * public class AVLNode
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in 
   * another file.
   * This class can and must be modified.
   * (It must implement IAVLNode)
   */
   class AVLNode implements IAVLNode{
	   private int key;
	   private String value;
	   private IAVLNode parent;
	   private IAVLNode left;
	   private IAVLNode right;
	   private int height;
	   private int size;
	   private boolean real;
	   private boolean isRightChild;

	   public AVLNode(){
		   this.real = false;
		   this.height = -1;
		   this.key = -1;
		   this.value = null;
		   this.size = 0;
	   }
	   public AVLNode(int key, String value){
		   this.key = key;
		   this.value = value;
		   this.real = true;
		   this.height = 0;
		   this.parent = null;
		   this.setRight(new AVLNode());
		   this.setLeft(new AVLNode());
		   this.size = 1;
	   }

	   public int getKey(){
		   return this.key;
	   }

	   public String getValue(){
		   return this.value;
	   }

	   public boolean getIsRightChild(){
		   return this.isRightChild;
	   }

	   /**
		* public void setLeft(IAVLNode node)
		* <p>
		* set node as this left, and update node's parent to this
		* complexity - O(1)
		*/
	   public void setLeft(IAVLNode node)
	   {
		   this.left = node;
		   if (node != null) {
			   node.setParent(this);
			   node.setIsRightChild(false);
		   }
	   }

	   public IAVLNode getLeft(){
		   return this.left;
	   }

	   /**
		* public void setRight(IAVLNode node)
		* <p>
		* set node as this right, and update node's parent to this
		* complexity - O(1)
		*/
	   public void setRight(IAVLNode node)
	   {
		   this.right = node;
		   if (node != null) {
			   node.setParent(this);
			   node.setIsRightChild(true);
		   }
	   }

	   public void setIsRightChild (boolean b){
		   this.isRightChild = b;
	   }

	   public IAVLNode getRight(){
		   return this.right;
	   }

	   public void setParent(IAVLNode node){
		   this.parent = node;
	   }

	   public IAVLNode getParent(){
		   return this.parent;
	   }

	   /**
		* public boolean isRealNode()
		* <p>
		* return true iff node is not virtual
		* complexity - O(1)
		*/
	   public boolean isRealNode(){
		   return real;
	   }

	   public void setHeight(int height){
		   this.height = height;
	   }
	   public int getHeight(){
		   return this.height;
	   }

	   /**
		* public void update()
		* <p>
		* calculate and update node's size and height based on it's children
		* complexity - O(1)
		*/
	   public void update(){
		   this.updateHeight();
		   this.updateSize();
	   }

	   /**
		* public void updateHeight()
		* <p>
		* calculate and update node's height based on it's children
		* complexity - O(1)
		*/
	   public void updateHeight(){
		   IAVLNode left = this.getLeft();
		   IAVLNode right = this.getRight();
		   int h = 1 + Math.max(left.getHeight(), right.getHeight());
		   this.setHeight(h);
	   }
	   public int getSize(){
		   return this.size;
	   }
	   public void setSize(int s){
		   this.size = s;
	   }

	   /**
		* public void updateSize()
		* <p>
		* calculate and update node's size based on it's children
		* complexity - O(1)
		*/
	   public void updateSize(){
		   this.setSize(1 + left.getSize() + right.getSize());
	   }

	   /**
		* public void nullify()
		* <p>
		* set parent, right and left to null
		* complexity - O(1)
		*/
	   public void nullify(){
		   this.setParent(null);
		   this.setRight(null);
		   this.setLeft(null);
	   }

	   /**
		* public void nullify()
		* <p>
		* get this node's parent and replace node with a virtual node as a child
		* complexity - O(1)
		*/
	   public void abandonThisChild(){
		   if (this.getParent() != null){
			   if (this.getIsRightChild())
				   this.getParent().setRight(new AVLNode());
			   else
				   this.getParent().setLeft(new AVLNode());
		   }
	   }

	   //for debugging
	   public String toString(){
		   return ""+this.getKey();
	   }
   }



  

