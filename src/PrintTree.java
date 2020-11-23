public class PrintTree
{

    static final int COUNT = 10;

    static void print2DUtil(IAVLNode root, int space) {
        if (root == null || !root.isRealNode())
            return;
        // Increase distance between levels
        space += COUNT;
        // Process right child first
        print2DUtil(root.getRight(), space);
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getKey() + "\n");
        // Process left child
        print2DUtil(root.getLeft(), space);
    }

    static void print2D(IAVLNode root) {
        print2DUtil(root, 0);
    }
}


