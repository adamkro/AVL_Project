import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Testing {
    private static int RAND_LIM = 100;
        private static final Random rd = new Random();

    public static int calHeight(IAVLNode node){
        if (!node.isRealNode())
            return -1;
        return 1 + Math.max(calHeight(node.getLeft()),calHeight(node.getRight()));
    }
    public static boolean CorrectHeights(IAVLNode node){
        if (!node.isRealNode())
            return true;
        if (node.getHeight() != calHeight(node))
            return false;
        return (CorrectHeights(node.getLeft()) && CorrectHeights(node.getRight()));
    }

    public static boolean isAVL(IAVLNode node){
        if (!node.isRealNode())
            return true;
        int left = node.getLeft().getHeight();
        int right = node.getRight().getHeight();
        return (Math.abs(left-right) <= 1 && isAVL(node.getLeft()) && isAVL(node.getRight()));
    }

    public static boolean isBST(IAVLNode node){
        if (!node.isRealNode())
            return true;
        return ((!node.getRight().isRealNode() || node.getRight().getKey() > node.getKey()) &&
                (!node.getLeft().isRealNode() || node.getLeft().getKey() < node.getKey()) &&
                isBST(node.getRight()) &&
                isBST(node.getLeft()));
    }

    public static void testJoinSplit(int numOfJoins, boolean show){
        AVLTree t1 = genRandTree(rd.nextInt(20), 0, 100);
        AVLTree t2 = genRandTree(rd.nextInt(20), 101, 200);
        //PrintBST.print(t1.getRoot());
        //PrintBST.print(t2.getRoot());
        t1.join(new AVLNode(100, ""), t2);
        int total_size = t1.size();
        testTree(t1);
        //System.out.println("first join:");
        //PrintBST.print(t1.getRoot());
        int[] inorder = t1.keysToArray();
        for (int i = 0; i < Math.min(numOfJoins, total_size); i++) {
            int keyRemoved = inorder[rd.nextInt(inorder.length)];
            if (show)
                System.out.println("Split by key: " + keyRemoved);
            AVLTree[] split = t1.split(keyRemoved);
            if (show) {
                System.out.println("smaller than key tree: ");
                PrintBST.print(split[0].getRoot());
                System.out.println("bigger than key tree: ");
                PrintBST.print(split[1].getRoot());
            }
            testTree(split[0]);
            testTree(split[1]);
            split[0].join(new AVLNode(keyRemoved,""),split[1]);
            t1 = split[0];
            if (show) {
                System.out.println("tree after rejoin:");
                PrintBST.print(t1.getRoot());
            }
            testTree(t1);
        }
    }

    public static void alert(AVLTree t, String testName){
        System.out.println("*****************************************************************************");
        System.out.println("Tree failed "+ testName);
        System.out.println(Arrays.toString(t.keysToArray()));
        PrintBST.print(t.getRoot());
    }

    public static void testTree(AVLTree t){
        IAVLNode root = t.getRoot();
        if (!t.empty()){
            if (!isBST(root)) {
                alert(t, "BST test");
            }
            if (!CorrectHeights(root)) {
                alert(t, "Heights test");
            }
            if(!isAVL(root)){
                alert(t, "isAVL test");
            }
            if(!inorderTest(t)){
                alert(t, "inorder test");
            }
            if(!sizeTest(t.getRoot())){
                alert(t, "Size test");
            }
        }
        //System.out.println("tree test success");
    }
    public static boolean inorderTest(AVLTree tree){
        int[] lst = tree.keysToArray();
        int[] copiedArray = lst.clone();
        Arrays.sort(lst);
        return Arrays.equals(lst, copiedArray);
    }

    public static boolean sizeTest(IAVLNode node){
        if (node == null || !node.isRealNode()) return true;
        return (nodeSizeTest(node) && sizeTest(node.getLeft()) && sizeTest(node.getRight()));
    }
    public static boolean nodeSizeTest(IAVLNode node){
        if (!node.isRealNode()) return true;
        return (node.getSize() == sizeEval(node));
    }
    public static int sizeEval(IAVLNode node){
        if (!node.isRealNode()) return 0;
        int left = sizeEval(node.getLeft());
        int right = sizeEval(node.getRight());
        return ( left + right + 1);
    }

    public static AVLTree genRandTree(int size, int min,int max){ //genRandTree with range
        int num;
        Set<Integer> hs= new HashSet<Integer>();
        AVLTree t = new AVLTree();
        while(size > 0) {
            num = rd.nextInt(max-min)+min;
            if (hs.contains(num))
                continue;
            hs.add(num);
            t.insert(num, "");
            size--;
        }
        return t;
    }


    public static void popRandomNodes(AVLTree tree, int count){
        int[] inorder = tree.keysToArray();
        int j;
        for (int i = 0; i < count; i++) {
            j = rd.nextInt(inorder.length);
            tree.delete(inorder[j]);
        }
    }

    public static void main(String[] args){
//        int TESTS_AMOUNT = 1;
//        for (int i = 0; i < TESTS_AMOUNT; i++) {
//            int TREE_SIZE = rd.nextInt(10) + 2;
//            int POP_AMOUNT = rd.nextInt(TREE_SIZE);
//            AVLTree tree = genRandTree(TREE_SIZE, 10 ,20);
//            popRandomNodes(tree,POP_AMOUNT);
//            testTree(tree);
//            PrintBST.print(tree.getRoot());
//            TREE_SIZE = rd.nextInt(7) +1;
//            POP_AMOUNT = rd.nextInt(TREE_SIZE);
//            AVLTree tree2 = genRandTree(TREE_SIZE, 0 ,9);
//            popRandomNodes(tree2,POP_AMOUNT);
//            testTree(tree2);
//            PrintBST.print(tree2.getRoot());
//            //tree.join(,tree2)
//        }
        for (int i = 0; i < 1000; i++) {
            testJoinSplit(rd.nextInt(20), false);
        }
        //akdk

//        //different sizes tests
//        for (int j = 0; j < 30; j++) {
//            for (int i = 0; i < 500; i++) {
//                AVLTree t = genRandTree(j, 0, 100);
//                //PrintBST.print(t.getRoot());
//                if(!sizeTest(t.getRoot())){
//                    alert(t, "Root size test");
//                }
//            }
//        }
//        AVLTree tree = new AVLTree();
//        tree.insert(34, "");
//        tree.insert(52, "");
//        tree.insert(21, "");
//        tree.insert(72, "");
//        PrintBST.print(tree.getRoot());
//        AVLTree tree2 = new AVLTree();
//        AVLTree tree3 = new AVLTree();
//        AVLTree tree4 = new AVLTree();
//        AVLTree tree5 = new AVLTree();
//        tree5.insert(9, "");
//
//
//        IAVLNode x = new AVLNode(20,"");
//        PrintBST.print(tree.getRoot());
//        tree.join(x, tree2);
//        PrintBST.print(tree.getRoot());
//        IAVLNode y = new AVLNode(18,"");
//        tree3.join(y, tree);
//        PrintBST.print(tree.getRoot());
//        IAVLNode z = new AVLNode(100,"");
//        tree4.join(z, tree);
//        PrintBST.print(tree.getRoot());
//        IAVLNode z2 = new AVLNode(10,"");
//        tree5.join(z2, tree);
//        PrintBST.print(tree5.getRoot());
//
//        AVLTree tree6 = new AVLTree();
//        AVLTree tree7 = new AVLTree();
//        IAVLNode z4 = new AVLNode(10,"");
//        tree6.join(z4, tree7);
//        PrintBST.print(tree7.getRoot());
//
        AVLTree tree55 = new AVLTree();
        tree55.insert(113, "");
        tree55.insert(101, "");
        tree55.insert(120, "");
        tree55.insert(167, "");
        AVLTree tree66 = new AVLTree();
        tree66.insert(180, "");
        IAVLNode node = new AVLNode(173,"");
        PrintBST.print(tree55.getRoot());
        System.out.println(tree55.getRoot().getKey());
        System.out.println(tree55.getMin().getKey());
        System.out.println(tree55.getMax().getKey());
        tree55.join(node, tree66);
        PrintBST.print(tree55.getRoot());
        System.out.println(tree55.getRoot().getKey());
        System.out.println(tree55.getMin().getKey());
        System.out.println(tree55.getMax().getKey());


        System.out.println("done!");
    }
}

//        AVLTree tree = new AVLTree();
//        tree.insert(34, "");
//        tree.insert(30, "");
//        tree.insert(21, "");
//        tree.insert(72, "");
//        tree.insert(66, "");
//        tree.insert(98, "");
//        tree.insert(50, "");
//        tree.insert(83, "");
//        tree.delete(72);
//        PrintBST.print(tree.getRoot());
//        tree.delete(21);
//        PrintBST.print(tree.getRoot());
//        tree.delete(72);
//        PrintBST.print(tree.getRoot());


//tree.insert(1, "");
//        tree.insert(9, "");
//        tree.insert(5, "");
//        tree.insert(10, "");
//        tree.insert(2, "");
//        tree.insert(7, "");
//        tree.insert(4, "");
//        tree.insert(3, "");
//        tree.insert(6, "");
//        tree.insert(11, "");
//        tree.insert(12, "");
//        tree.insert(13, "");
//        tree.insert(14, "");
//        tree.insert(101, "");
//        tree.insert(202, "");
//        tree.delete(13);
//        tree.delete(12);
//        tree.insert(-5, "");
//        tree.delete(14);
//        tree.insert(1000, "");
//        tree.delete(202);
//        tree.delete(9);
//        tree.insert(22, "");
//        tree.delete(7);
//        tree.delete(6);
//        tree.insert(21, "");
//        tree.delete(101);
//        tree.delete(4);
//System.out.println("root height is "+tree.getRoot().getHeight());
//System.out.println(tree.getRoot().getKey());
//System.out.println(tree.getRoot().getRight().getLeft().getLeft().getHeight());
// System.out.println(tree.search(5));
//System.out.println(Arrays.toString(tree.keysToArray()));