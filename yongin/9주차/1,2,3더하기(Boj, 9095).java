import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[11];
			arr[1]=1;
			arr[2]=2;
			arr[3]=4;
//			System.out.println(Arrays.toString(arr));
			
			for (int i = 4; i < N+1; i++) {
				arr[i] = arr[i-1]+arr[i-2]+arr[i-3];
			}
			int result = arr[N];
			System.out.println(result);
		}
	}
}
