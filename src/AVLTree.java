import java.util.ArrayList;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 *
 */
//CHECK IF WE CAN ADD METHODS TO INTERFACE
//CHECK IF WE NEED A TREE CONSTRUCTOR OR ADD FIRST ROOT WITH INSERT
//IAVL AND AVL WERE PUBLIC AND WE CHANGED IT

public class AVLTree {
	private int size = 0;
	private IAVLNode root = null;

  /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
  public boolean empty() {
  	return size == 0;
  }

 /**
   * public String search(int k)
   *
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   */
  public String search(int k)
  {
  	IAVLNode node = searchNode(k);
	 if(node != null)
	 	return node.getValue();
	return null;
  }

	public IAVLNode searchNode(int k)
	{
		if (this.empty()) return null;
		IAVLNode node = root;
		while (node.isRealNode()){
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
   *
   * inserts an item with key k and info i to the AVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * promotion/rotation - counted as one rebalnce operation, double-rotation is counted as 2.
   * returns -1 if an item with key k already exists in the tree.
   */
   public int insert(int k, String i) {
   		if (this.empty()) { // initialize new tree
   			this.setRoot(new AVLNode(k,i));
   			this.size = 1;
   			return 0;
		}
		if (this.search(k) != null) return -1; // CHECK IF = NULL IS OK
		this.size += 1;
		IAVLNode parent = findParent(this.getRoot(), k);
		IAVLNode node = new AVLNode(k, i);
		if (k < parent.getKey()) { // insert left
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
		if (parent.getHeight() == 0) { // CASE: NOT UNARY
			this.balance(parent,true);
		}
		return 0;
   }
//private int balance(IAVLNode node) {
//
//	int count = 0;
//	boolean finish_balance = false;
//	while (!finish_balance) {
//		int diff = rankDifference(node);
//		switch (diff) {
//			case 2:
//				if (rankDifference(node.getLeft()) == 1) { // one rotate right
//					rotateRight(node.getLeft(), node);
//					count += 2;
//				} else {
//					IAVLNode left = node.getLeft();
//					rotateLeft(left.getRight(), left);
//					rotateRight(node.getLeft(), node);
//					count += 5;
//				}
//				finish_balance = true;
//				break;
//			case (-2):
//				if (rankDifference(node.getRight()) == -1) { // one rotate left
//					rotateLeft(node.getRight(), node);
//					count += 2;
//				} else {
//					IAVLNode right = node.getRight();
//					rotateRight(right.getLeft(), right);
//					rotateLeft(node.getRight(), node);
//					count += 5;
//				}
//				finish_balance = true;
//				break;
//			default: //-1 or 1
//				int preHeight = node.getHeight();
//				node.updateHeight();
//				int postHeight = node.getHeight();
//				if (node.getParent() == null || preHeight == postHeight) {
//					finish_balance = true;
//					break;
//				}
//				count ++;
//				node = node.getParent();
//		}
//	}
//	return count;
//}

	private static int rankDifference(IAVLNode node){
		int leftHeight= node.getLeft().getHeight();
		int rightHeight= node.getRight().getHeight();
		return (leftHeight-rightHeight);
	}

   private void rotateRight(IAVLNode left, IAVLNode node){
		IAVLNode parent = node.getParent();
		node.setLeft(left.getRight());
		left.setRight(node);
	   	node.updateHeight();
	   	reConnectTree(left, parent);
   }
	private void rotateLeft(IAVLNode right, IAVLNode node) {
		IAVLNode parent = node.getParent();
		node.setRight(right.getLeft());
		right.setLeft(node);
		node.updateHeight();
		reConnectTree(right, parent);
	}

	private void reConnectTree(IAVLNode subTreeRoot, IAVLNode parent) {
		if (parent == null)
			this.setRoot(subTreeRoot);
		else if (parent.getKey() > subTreeRoot.getKey())
			parent.setLeft(subTreeRoot);
		else
			parent.setRight(subTreeRoot);
		subTreeRoot.updateHeight();
	}


	private static IAVLNode findParent(IAVLNode node, int k){
   	while(node.isRealNode()){
		if (k > node.getKey()){
			if(node.getRight().isRealNode())
				node = node.getRight();
			else
				return node;
		} else {
			if(node.getLeft().isRealNode())
				node = node.getLeft();
			else
				return node;
		}
	}
   	return null;
   }

  /**
   * public int delete(int k)
   *
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * demotion/rotation - counted as one rebalnce operation, double-rotation is counted as 2.
   * returns -1 if an item with key k was not found in the tree.
   */
   public int delete(int k) {
	   if (search(k) == null)
		   return -1;

	   IAVLNode node = searchNode(k);
	   IAVLNode nodeToBalance;
	   if (node.getHeight() == 0)  // node is leaf
		   nodeToBalance = simpleDelete(node);
	   else if ((!node.getRight().isRealNode()) || (!node.getLeft().isRealNode())) // unary node
		   nodeToBalance = simpleDelete(node);
	   else //inner node
		   nodeToBalance= replaceNode(node); //replace node with his successor
	   return (this.balance (nodeToBalance,false));
   }

	public IAVLNode replaceNode (IAVLNode node) {
		IAVLNode successor = findSuccessor(node);
		IAVLNode nodeToBalance = simpleDelete(successor);
		successor.setRight(node.getRight());
		successor.setLeft(node.getLeft());
		successor.setParent(node.getParent());
		if (node.getParent() == null)
			this.setRoot(successor);
		else{
			if (node.getIsRightChild())
				node.getParent().setRight(successor);
			else
				node.getParent().setLeft(successor);
		}
		node.setParent(null);
		node.setLeft(null);
		node.setRight(null);
		if (nodeToBalance == node) //case that that successor is child of node
			nodeToBalance = successor;
		return nodeToBalance;
	}


	public IAVLNode simpleDelete (IAVLNode node){
   		IAVLNode parent = node.getParent();
   		if (parent == null){
   			if (node.getHeight() == 0)
				this.setRoot(null);
   			else if (node.getRight().isRealNode()){
				this.setRoot(node.getRight());
				node.getRight().setParent(null);
			}
   			else{
				this.setRoot(node.getLeft());
				node.getLeft().setParent(null);
			}
   		}
   		else {
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
		node.setParent(null);
		node.setRight(null);
		node.setLeft(null);
		this.size -= 1;
		return parent;
	}


	public IAVLNode findSuccessor (IAVLNode node){
   		if(node.getRight().isRealNode()){
			node = node.getRight();
   			while (node.getLeft().isRealNode())
				node = node.getLeft();
			return node;
		}
   		else{
			int k = node.getKey();

   				node = node.getParent();
   			return node;
		}
	}

	private int balance (IAVLNode node, boolean isInsert) {
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
					if (isInsert)
						node = null;
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
					if (isInsert)
						node = null;
					break;
				default: //-1 or 1
					int preHeight = node.getHeight();
					node.updateHeight();
					int postHeight = node.getHeight();
					if (preHeight == postHeight) {
						node = null;
						break;
					}
					count ++;
			}
			if (node != null)
				node = node.getParent();
		}
		return count;
	}

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty
    */
   public String min()
   {
	   return "42"; // to be replaced by student code
   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty
    */
   public String max()
   {
	   return "42"; // to be replaced by student code
   }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
  public int[] keysToArray()
  {
        int[] arr = new int[this.size()];
	  	ArrayList<Integer> aList = new ArrayList<Integer>(this.size());
        inOrder(aList, this.getRoot());
	  	arr = aList.stream().mapToInt(i->i).toArray();
        return arr;
  }
	private void inOrder(ArrayList<Integer> list, IAVLNode node) {
  		if (node.isRealNode()) {
  			inOrder(list, node.getLeft());
  			list.add(node.getKey());
  			inOrder(list, node.getRight());
		}
	}
  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
        String[] arr = new String[42]; // to be replaced by student code
        return arr;                    // to be replaced by student code
  }

   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    *
    * precondition: none
    * postcondition: none
    */
   public int size()
   {
	   return this.size; // to be replaced by student code
   }
   
     /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    *
    * precondition: none
    * postcondition: none
    */
   public IAVLNode getRoot()
   {
	   return this.root;
   }
	private void setRoot(IAVLNode newRoot)
	{
		this.root = newRoot;
		newRoot.setParent(null);
	}

     /**
    * public string split(int x)
    *
    * splits the tree into 2 trees according to the key x. 
    * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
	  * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
    * postcondition: none
    */   
   public AVLTree[] split(int x)
   {
	   return null; 
   }
   /**
    * public join(IAVLNode x, AVLTree t)
    *
    * joins t and x with the tree. 	
    * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
	  * precondition: keys(x,t) < keys() or keys(x,t) > keys(). t/tree might be empty (rank = -1).
    * postcondition: none
    */   
   public int join(IAVLNode x, AVLTree t)
   {
	   return 0; 
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
		void updateHeight();
		void setRightChild(boolean b);
		boolean getIsRightChild();
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
  		private int rankDifference;
  		private int rank;
  		private boolean real;
	   	private boolean isRightChild;


	   public AVLNode(){
  			this.real = false;
  			this.height = -1;
  			this.key = -1;
  			this.value = null;
		}
  		public AVLNode(int key, String value){
  			this.key = key;
  			this.value = value;
  			this.real = true;
  			this.height = 0;
  			this.parent = null;
  			this.right = new AVLNode();
  			this.left = new AVLNode();
		}

		public int getKey() {
			return this.key;
		}

		public String getValue(){
			return this.value;
		}

	   public boolean getIsRightChild(){
		   return this.isRightChild;
	   }


		public void setLeft(IAVLNode node)
		{
			this.left = node;
			if (node != null) {
				node.setParent(this);
				node.setRightChild(false);
			}
		}
		public IAVLNode getLeft(){
			return this.left;
		}

		public void setRight(IAVLNode node)
		{
			this.right = node;
			if (node != null) {
				node.setParent(this);
				node.setRightChild(true);
			}
		}

	   public void setRightChild (boolean b) {
		   this.isRightChild = b;
	   }

		public IAVLNode getRight()
		{
			return this.right;
		}
		public void setParent(IAVLNode node)
		{
			this.parent = node;
//			if (this.getKey() < node.getKey()) node.setLeft(this);
//			else node.setRight(this);
		}
		public IAVLNode getParent()
		{
			return this.parent;
		}
		// Returns True if this is a non-virtual AVL node
		public boolean isRealNode()
		{
			return real;
		}
    public void setHeight(int height)
    {
      this.height = height;
    }
    public int getHeight()
    {
      return this.height;
    }
    public void updateHeight(){
  			IAVLNode left = this.getLeft();
  			IAVLNode right = this.getRight();
  			int h = 1 + Math.max(left.getHeight(), right.getHeight());
  			this.setHeight(h);
	}


  }



  

