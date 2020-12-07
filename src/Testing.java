import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Testing {
    private static final Random rd = new Random();
//
//    public static int calHeight(IAVLNode node){
//        if (!node.isRealNode())
//            return -1;
//        return 1 + Math.max(calHeight(node.getLeft()),calHeight(node.getRight()));
//    }
//
//    public static boolean CorrectHeights(IAVLNode node){
//        if (!node.isRealNode())
//            return true;
//        if (node.getHeight() != calHeight(node))
//            return false;
//        return (CorrectHeights(node.getLeft()) && CorrectHeights(node.getRight()));
//    }
//
//    public static boolean isAVL(IAVLNode node){
//        if (!node.isRealNode())
//            return true;
//        int left = node.getLeft().getHeight();
//        int right = node.getRight().getHeight();
//        return (Math.abs(left-right) <= 1 && isAVL(node.getLeft()) && isAVL(node.getRight()));
//    }
//
//    public static boolean isBST(IAVLNode node){
//        if (!node.isRealNode())
//            return true;
//        return ((!node.getRight().isRealNode() || node.getRight().getKey() > node.getKey()) &&
//                (!node.getLeft().isRealNode() || node.getLeft().getKey() < node.getKey()) &&
//                isBST(node.getRight()) &&
//                isBST(node.getLeft()));
//    }
//
//    public static boolean minMaxTest(AVLTree t){
//        if (t.empty())
//            return (t.getMin() == null && t.getMax() == null);
//        IAVLNode min = t.getRoot();
//        while (min.getLeft().isRealNode())
//            min = min.getLeft();
//        IAVLNode max = t.getRoot();
//        while (max.getRight().isRealNode())
//            max = max.getRight();
//        return (max == t.getMax() && min == t.getMin());
//    }
//
//    public static void testJoinSplit(int numOfJoins, boolean show, int maxTreeSize){
//        if (maxTreeSize > 100){
//            System.out.println("maxTreeSize should be less than 100");
//            return;
//        }
//        AVLTree t1 = genRandTree(rd.nextInt(maxTreeSize/2), 0, 100);
//        AVLTree t2 = genRandTree(rd.nextInt(maxTreeSize/2), 101, 200);
//        if (show) {
//            System.out.println("first join trees: (can be empty)");
//            PrintBST.print(t1.getRoot());
//            PrintBST.print(t2.getRoot());
//        }
//        t1.join(new AVLNode(100, ""), t2);
//        int total_size = t1.size();
//        testTree(t1);
//        if (show) {
//            System.out.println("join with x=100:");
//            PrintBST.print(t1.getRoot());
//        }
//        int[] inorder = t1.keysToArray();
//        for (int i = 0; i < Math.min(numOfJoins, total_size); i++) {
//            int keyRemoved = inorder[rd.nextInt(inorder.length)];
//            if (show)
//                System.out.println("Split by key: " + keyRemoved);
//            AVLTree[] split = t1.split(keyRemoved);
//            if (show) {
//                System.out.println("smaller than key tree: ");
//                PrintBST.print(split[0].getRoot());
//                System.out.println("bigger than key tree: ");
//                PrintBST.print(split[1].getRoot());
//            }
//            testTree(split[0]);
//            testTree(split[1]);
//            split[0].join(new AVLNode(keyRemoved,""),split[1]);
//            t1 = split[0];
//            if (show) {
//                System.out.println("tree after rejoin:");
//                PrintBST.print(t1.getRoot());
//            }
//            testTree(t1);
//        }
//    }
//
//    public static boolean inorderTest(AVLTree tree){
//        int[] lst = tree.keysToArray();
//        int[] copiedArray = lst.clone();
//        Arrays.sort(lst);
//        return Arrays.equals(lst, copiedArray);
//    }
//
//    public static boolean sizeTest(IAVLNode node){
//        if (node == null || !node.isRealNode()) return true;
//        return (nodeSizeTest(node) && sizeTest(node.getLeft()) && sizeTest(node.getRight()));
//    }
//
//    public static boolean nodeSizeTest(IAVLNode node){
//        if (!node.isRealNode()) return true;
//        return (node.getSize() == sizeEval(node));
//    }
//    public static int sizeEval(IAVLNode node){
//        if (!node.isRealNode()) return 0;
//        int left = sizeEval(node.getLeft());
//        int right = sizeEval(node.getRight());
//        return ( left + right + 1);
//    }
//    public static AVLTree genRandTree(int size, int min,int max){ //genRandTree with range
//        int num;
//        Set<Integer> hs= new HashSet<Integer>();
//        AVLTree t = new AVLTree();
//        while(size > 0) {
//            num = rd.nextInt(max-min)+min;
//            if (hs.contains(num))
//                continue;
//            hs.add(num);
//            t.insert(num, "");
//            size--;
//        }
//        return t;
//    }
//
//    public static void popRandomNodes(AVLTree tree, int count){
//        int[] inorder = tree.keysToArray();
//        int j;
//        for (int i = 0; i < count; i++) {
//            j = rd.nextInt(inorder.length);
//            tree.delete(inorder[j]);
//        }
//    }
//
//    public static void alert(AVLTree t, String testName){
//        System.out.println("*****************************************************************************************");
//        System.out.println("Tree failed "+ testName);
//        System.out.println(Arrays.toString(t.keysToArray()));
//        System.out.println("min: " + t.getMin() + ", max: " + t.getMax());
//        PrintBST.print(t.getRoot());
//        System.out.println("*****************************************************************************************");
//    }
//
//    public static void testTree(AVLTree t){
//        IAVLNode root = t.getRoot();
//        if (!t.empty()){
//            if (!isBST(root)) {
//                alert(t, "BST test");
//            }
//            if (!CorrectHeights(root)) {
//                alert(t, "Heights test");
//            }
//            if(!isAVL(root)){
//                alert(t, "isAVL test");
//            }
//            if(!inorderTest(t)){
//                alert(t, "inorder test");
//            }
//            if(!sizeTest(t.getRoot())){
//                alert(t, "size test");
//            }
//            if(!minMaxTest(t)){
//                alert(t, "min/max test");
//            }
//        }
//    }
//
//
//    public static void manualCheck (){
//        AVLTree tree = new AVLTree();
//        AVLTree tree2 = new AVLTree();
//        // first tree keys
//        int A1 = 10; //insert value between 1 to 100
//        int A2 = 12; //insert value between 1 to 100
//        int A3 = 15; //insert value between 1 to 100
//        int A4 = 100; //insert value between 1 to 100
//        int A5 = 99; //insert value between 1 to 100
//        int A6 = 70; //insert value between 1 to 100
//        int A7 = 82; //insert value between 1 to 100
//        int A8 = 3; //insert value between 1 to 100
//        int A9 = 4; //insert value between 1 to 100
//        // second tree keys
//        int B1 = 210; //insert value between 200 to 300
//        int B2 = 220; //insert value between 200 to 300
//        int B3 = 300; //insert value between 200 to 300
//        int B4 = 200; //insert value between 200 to 300
//        // insert, delete, search special cases
//        tree.insert(A1, "");
//        int value = tree.insert(A1, "");
//        if (value != -1)
//            System.out.println("insert should return -1");
//        tree.delete(A1);
//        tree.insert(A2, "");
//        value =  tree.delete(A1);
//        if (value != -1)
//            System.out.println("delete should return -1");
//        tree.delete(A2);
//        if (tree.getMin()!= null)
//            System.out.println("min should be null");
//        if (tree.getMax()!= null)
//            System.out.println("max should be null");
//        tree.insert(A2, "");
//        if (tree.search(10) != null)
//            System.out.println("search should return null");
//
//        // building trees, and delete
//        tree.insert(A1, "");
//        tree.insert(A2, "");
//        tree.insert(A3, "");
//        tree.insert(A4, "");
//        tree.insert(A5, "");
//        tree.delete(A2);
//        tree.insert(A6, "");
//        tree.insert(A7, "");
//        tree.delete(A5);
//        tree.insert(A8, "");
//        tree.delete(A6);
//        tree.insert(A9, "");
//        tree.insert(A6, "");
//        tree.insert(A2, "");
//        tree.insert(A5, "");
//        tree2.insert(B1, "");
//        tree2.insert(B2, "");
//        tree2.insert(B3, "");
//        tree2.insert(B4, "");
//        testTree(tree);
//        testTree(tree2);
//
//        // split special cases
//        IAVLNode x = new AVLNode(150,"");
//        tree.join(x,tree2);
//        testTree(tree);
//        AVLTree[] array = tree.split(tree.getMax().getKey());
//        testTree(array[0]);
//        testTree(array[1]);
//        AVLTree[] arr = array[0].split(array[0].getMin().getKey());
//        testTree(arr[0]);
//        testTree(arr[1]);
//
//        // join special cases
//        AVLTree t1 = new AVLTree();
//        AVLTree t2 = new AVLTree();
//        t1.join(new AVLNode(3,""),t2); // join 2 empty trees
//        if (t1.size() != 1 || t1.getRoot().getKey() != 3 || t1.empty()){
//            System.out.println("error joining 2 empty trees");
//        }
//        AVLTree t3 = new AVLTree();
//        t3.insert(5,"");
//        AVLTree t4 = new AVLTree();
//        t3.join(new AVLNode(3,""),t4); // join 1 empty tree (other)
//        if (t3.size() != 2 || t3.empty()){
//            System.out.println("error joining 1 empty tree");
//        }
//        AVLTree t5 = new AVLTree();
//        t5.insert(5,"");
//        AVLTree t6 = new AVLTree();
//        t6.join(new AVLNode(3,""),t5); // join 1 empty tree (this)
//        if (t3.size() != 2 || t3.empty()){
//            System.out.println("error joining 1 empty tree");
//        }
//    }
//    static void SplitAndJoinTest(int SPLIT_JOIN_TESTS_AMOUNT, boolean showSteps, int MAX_TREE_SIZE){
//        for (int i = 0; i < SPLIT_JOIN_TESTS_AMOUNT; i++) {
//            testJoinSplit(rd.nextInt(20), showSteps, MAX_TREE_SIZE);
//        }
//
//    }
//    static void generalTest(int TESTS_AMOUNT){
//        for (int i = 0; i < TESTS_AMOUNT; i++) {
//            int TREE_SIZE = rd.nextInt(20) + 2;
//            int POP_AMOUNT = rd.nextInt(TREE_SIZE);
//            AVLTree tree = genRandTree(TREE_SIZE, 0 ,30);
//            popRandomNodes(tree,POP_AMOUNT);
//            testTree(tree);
//        }
//    }

    public static void main(String[] args){
//        manualCheck(); // manual special cases
//        int TESTS_AMOUNT = 1000; //start low, then increase
//        generalTest(TESTS_AMOUNT);
//
//        int SPLIT_JOIN_TESTS_AMOUNT = 1000; // start low, then increase
//        boolean showSteps = false; // prints steps. false for clean output
//        int MAX_TREE_SIZE = 20; // start small, then increase
//        SplitAndJoinTest(SPLIT_JOIN_TESTS_AMOUNT, showSteps, MAX_TREE_SIZE);
    }
}
