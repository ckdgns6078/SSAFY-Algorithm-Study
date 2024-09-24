import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int K = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);
        
        int[] lines = new int[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Integer.parseInt(br.readLine().trim());
        }
        
        long start = 1;
        long end = Arrays.stream(lines).max().getAsInt();
        long result = 0;
        
        while (start <= end) {
            long mid = (start + end) / 2;
            long lineCount = 0;
            
            for (int line : lines) {
                lineCount += line / mid;
            }
            
            if (lineCount >= N) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
