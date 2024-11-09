import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCities = Integer.parseInt(reader.readLine());
        
        int minLimit = 0, maxLimit = -1;
        int[] budgets = new int[numberOfCities];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numberOfCities; i++) {
            budgets[i] = Integer.parseInt(tokenizer.nextToken());
            maxLimit = Math.max(maxLimit, budgets[i]);
        }
        
        int totalBudget = Integer.parseInt(reader.readLine());
        while (minLimit <= maxLimit) {
            int limit = (minLimit + maxLimit) / 2;
            long allocatedBudget = 0;
            for (int i = 0; i < numberOfCities; i++) {
                if (budgets[i] > limit) allocatedBudget += limit;
                else allocatedBudget += budgets[i];
            }
            if (allocatedBudget <= totalBudget) {
                minLimit = limit + 1;
            } else {
                maxLimit = limit - 1;
            }
        }
        System.out.println(maxLimit);
    }
}
