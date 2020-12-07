import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Data {
    private static final Random rd = new Random();

    public static int[] randomArr(int size){
        int[] arr = new int[size];
        Set<Integer> hs= new HashSet<Integer>();
        int num;
        for (int i = 0; i < size; i++) {
            num = rd.nextInt();
            while(hs.contains(num))
                num = rd.nextInt();
            hs.add(num);
            arr[i] = num;
        }
        return arr;
    }

    public static int[] descArr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size-i;
        }
        return arr;
    }

    public static int fingerSearch (int k,AVLTree t) {
        if (t.empty())
            return 0;
        int steps = 0;
        IAVLNode start = t.getMax();
        while (start.getParent() != null && start.getParent().getKey() > k) {
            start = start.getParent();
            steps++;
        }
        IAVLNode node = start;
        while (node.isRealNode()) {
            if (k > node.getKey()) {
                if (node.getRight().isRealNode()) {
                    node = node.getRight();
                    steps++;
                }
                else
                    return steps;
            } else {
                if (node.getLeft().isRealNode()) {
                    node = node.getLeft();
                    steps++;
                }
                else
                    return steps;
            }
        }
        return steps; //return null
    }

    public static int insertFromArr(int[] arr){
        AVLTree tree = new AVLTree();
        int count = 0;
        for (int key : arr) {
            count += fingerSearch(key, tree);
            tree.insert(key, "");
        }
        return count;
    }

    public static AVLTree createTreeFromArr(int[] arr){
        AVLTree tree = new AVLTree();
        for (int key : arr) {
            tree.insert(key, "");
        }
        return tree;
    }


    public static long insertionSort (int[] array) {
        long swaps = 0;
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j - 1;
            while ((i > -1) && (array[i] > key)) {
                array[i + 1] = array[i];
                i--;
                swaps++;
            }
            array[i + 1] = key;
            swaps++;
        }
        return swaps;
    }

    public static void main(String[] args) {
        //question 1
//        for (int i = 1; i < 11; i++) {
//            System.out.println("*****************************");
//            System.out.println("test num :" + i);
//            int[] randArr = randomArr(10000 * i);
//            int[] descArr = descArr(10000 * i);
//            int AVLRandArr = insertFromArr(randArr);
//            int AVLDescArr = insertFromArr(descArr);
//            System.out.println("searches random array: "+AVLRandArr);
//            System.out.println("searches descending array: "+AVLDescArr);
//            long swapsArr = insertionSort(randArr);
//            long swapsDescArr = insertionSort(descArr);
//            System.out.println("swaps random array: "+swapsArr);
//            System.out.println("swaps descending array: "+swapsDescArr);
//        }
        //question 2
        for (int i = 1; i < 11; i++) {
            System.out.println("*****************************");
            System.out.println("test num :" + i);
            int[] randArr = randomArr(10000 * i);
            AVLTree t1 = createTreeFromArr(randArr);
            AVLTree t2 = createTreeFromArr(randArr);
            int randKey = randArr[10];
            int maxLeftSubTree = t2.getMaxLeftSubTree();
            int[] resRand = t1.splitAndCount(randKey);
            int[] resMax = t2.splitAndCount(maxLeftSubTree);

            System.out.println("random split avg: "+resRand[0]);
            System.out.println("random split max cost: "+resRand[1]);
            System.out.println("max split avg: "+resMax[0]);
            System.out.println("max split max cost: "+resMax[1]);
        }

    }
}