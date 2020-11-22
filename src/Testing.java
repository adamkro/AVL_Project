import java.util.Arrays;

public class Testing {
    public static void main(String[] args){
        AVLTree tree = new AVLTree();
        tree.insert(1, "");
        tree.insert(2, "");
        tree.insert(3, "");
        tree.insert(4, "");
        tree.insert(5, "d");
        tree.insert(6, "");
        tree.insert(7, "");
        tree.insert(8, "d");
        tree.insert(9, "d");
        tree.insert(10, "d");
        System.out.println(tree.getRoot().getRight().getKey());




        // System.out.println(tree.search(5));
        System.out.println(Arrays.toString(tree.keysToArray()));
    }
}
