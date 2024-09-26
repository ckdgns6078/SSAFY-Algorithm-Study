import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        long m = sc.nextLong();
        
        int[] tree = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = sc.nextInt();
        }
        
        Arrays.sort(tree);
        
        long low = 0;
        long high = tree[n - 1];
        long max = 0;
        
        while (low <= high) {
            long sum = 0;
            long cut = (low + high) / 2;
            
            for (int i = 0; i < n; i++) {
                if (tree[i] > cut) {
                    sum += tree[i] - cut;
                }
            }
            
            
            if (sum >= m) {
                max = cut;
                low = cut + 1;
            } else {
                high = cut - 1;
            }
        }
        
        System.out.println(max);
        
        sc.close();
    }
}
