import java.util.Arrays;

public class trash {

    //	public void updateSizeAbove(IAVLNode node, boolean inc){
//  		while (node != null){
//  			if(inc)
//  				node.changeSizeBy(1);
//  			else
//  				node.changeSizeBy(-1);
//  			node = node.getParent();
//		}
//	}

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

//        AVLTree tree = new AVLTree();
//        tree.insert(//        tree.insert(30, "");
////        tree.insert(21, "");
////        tree.insert(72, "");
////        tree.insert(66, "");
////        tree.insert(98, "");
////        tree.insert(50, "");
////        tree.insert(83, "");
////        tree.delete(72);
////        PrintBST.print(tree.getRoot());
////        tree.delete(21);
////        PrintBST.print(tree.getRoot());
////        tree.delete(72);
////        PrintBST.print(tree.getRoot());
//
//
////tree.insert(1, "");
////        tree.insert(9, "");
////        tree.insert(5, "");
////        tree.insert(10, "");
////        tree.insert(2, "");
////        tree.insert(7, "");
////        tree.insert(4, "");
////        tree.insert(3, "");
////        tree.insert(6, "");
////        tree.insert(11, "");
////        tree.insert(12, "");
////        tree.insert(13, "");
////        tree.insert(14, "");
////        tree.insert(101, "");
////        tree.insert(202, "");
////        tree.delete(13);
////        tree.delete(12);
////        tree.insert(-5, "");
////        tree.delete(14);
////        tree.insert(1000, "");
////        tree.delete(202);
////        tree.delete(9);
////        tree.insert(22, "");
////        tree.delete(7);
////        tree.delete(6);
////        tree.insert(21, "");
////        tree.delete(101);
////        tree.delete(4);
////System.out.println("root height is "+tree.getRoot().getHeight());
////System.out.println(tree.getRoot().getKey());
////System.out.println(tree.getRoot().getRight().getLeft().getLeft().getHeight());
//// System.out.println(tree.search(5));
////System.out.println(Arrays.toString(tree.keysToArray()));34, "");


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
//
//    AVLTree tree55 = new AVLTree();
//        tree55.insert(113, "");
//        tree55.insert(101, "");
//        tree55.insert(120, "");
//        tree55.insert(167, "");
//    AVLTree tree66 = new AVLTree();
//        tree66.insert(180, "");
//    IAVLNode node = new AVLNode(173,"");
//        PrintBST.print(tree55.getRoot());
//        System.out.println(tree55.getRoot().getKey());
//        System.out.println(tree55.getMin().getKey());
//        System.out.println(tree55.getMax().getKey());
//        tree55.join(node, tree66);
//        PrintBST.print(tree55.getRoot());
//        System.out.println(tree55.getRoot().getKey());
//        System.out.println(tree55.getMin().getKey());
//        System.out.println(tree55.getMax().getKey());
//    AVLTree[] trees = tree55.split(167);
//        PrintBST.print(trees[0].getRoot());
//        System.out.println(trees[0].getMin().getKey());
//        System.out.println(trees[0].getMax().getKey());
//        PrintBST.print(trees[1].getRoot());
//        System.out.println(trees[1].getMin().getKey());
//        System.out.println(trees[1].getMax().getKey());



//    AVLTree tree = new AVLTree();
//        System.out.println(Arrays.toString(tree.keysToArray()));
//        System.out.println(Arrays.toString(tree.infoToArray()));
//        tree.insert(8, "8");
//        tree.insert(10, "10");
//        System.out.println(Arrays.toString(tree.keysToArray()));
//        System.out.println(Arrays.toString(tree.infoToArray()));
//        tree.insert(4, "4");
//        tree.insert(1, "1");
//        tree.insert(11, "11");
//        tree.insert(6, "6");
//        System.out.println(Arrays.toString(tree.keysToArray()));
//        System.out.println(Arrays.toString(tree.infoToArray()));
//        tree.insert(3, "3");
//        tree.insert(5, "5");
//        tree.insert(7, "7");
//        System.out.println(Arrays.toString(tree.keysToArray()));
//        System.out.println(Arrays.toString(tree.infoToArray()));
//        tree.insert(9, "9");
//        tree.insert(2, "2");
//        System.out.println(Arrays.toString(tree.keysToArray()));
//        System.out.println(Arrays.toString(tree.infoToArray()));

//
//
//    //**************************************************
//    //**************************************************
//    //**************************************************
//    //**************************************************
//    //**************************************************
//    //for measurements
//    public int[] splitAndCount(int x) {
//        int count = 0;
//        int sum = 0;
//        int max = 0;
//        int curr = 0;
//        IAVLNode node_x = searchNode(x);
//        AVLTree smaller = constructSubTree(node_x.getLeft());
//        AVLTree bigger = constructSubTree(node_x.getRight());
//        AVLTree[] res = new AVLTree[2];
//        res[0] = smaller;
//        res[1] = bigger;
//        IAVLNode node = node_x.getParent();
//        node_x.abandonThisChild();
//        node_x.nullify();
//        IAVLNode tmp;
//        while (node != null) {
//            tmp = node.getParent();
//            node.abandonThisChild();
//            IAVLNode left = node.getLeft();
//            IAVLNode right = node.getRight();
//            node.setLeft(new AVLNode());
//            node.setRight(new AVLNode());
//            node.setParent(null);
//            node.update();
//            if (node.getKey() < x) {
//                curr = smaller.join(node, constructSubTree(left)); //Make sure join takes care of node's children and parent
//            } else {
//                curr = bigger.join(node, constructSubTree(right));
//            }
//            sum += curr;
//            count++;
//            if (curr > max)
//                max = curr;
//            node = tmp;
//        }
//        res[0].calculateMin();
//        res[0].calculateMax();
//        res[1].calculateMin();
//        res[1].calculateMax();
//        int[] ans = new int[2];
//        ans[0] = sum/count;
//        ans[1] = max;
//        return ans;
//    }
//
//
//    public int getMaxLeftSubTree(){
//        IAVLNode node = this.getRoot().getLeft();
//        while(node.getRight().isRealNode())
//            node = node.getRight();
//        return node.getKey();
//    }
//
//
//
//
//
//
//    //**************************************************
//    //**************************************************
//    //**************************************************
//    //**************************************************
//    //**************************************************

}
