import java.util.Arrays;

public class Testing {
    public static void main(String[] args){
        AVLTree tree = new AVLTree();
        tree.insert(1, "");
        tree.insert(9, "");
        tree.insert(5, "");
        tree.insert(10, "");
        tree.insert(2, "");
        tree.insert(7, "");
        tree.insert(4, "");
        tree.insert(3, "");
        tree.insert(6, "");
        tree.insert(11, "");
        tree.insert(12, "");
        tree.insert(13, "");
        tree.insert(14, "");
        tree.insert(101, "");
        tree.insert(202, "");
        tree.delete(13);
        tree.delete(12);
        tree.insert(-5, "");
        tree.delete(14);
        tree.insert(1000, "");
        tree.delete(202);
        tree.delete(9);
        tree.insert(22, "");
        tree.delete(7);
        tree.delete(6);
        tree.insert(21, "");
        tree.delete(101);
        tree.delete(4);


        PrintBST.print(tree.getRoot());
        //System.out.println("root height is "+tree.getRoot().getHeight());
        System.out.println(tree.getRoot().getKey());
        //System.out.println(tree.getRoot().getRight().getLeft().getLeft().getHeight());



        // System.out.println(tree.search(5));
        System.out.println(Arrays.toString(tree.keysToArray()));
    }
}
