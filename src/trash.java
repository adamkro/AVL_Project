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

}
