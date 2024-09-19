import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[1001];
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 3;
		for (int i = 4; i < N+1; i++) {
//			System.out.println(i);
			arr[i] = (arr[i-1]+arr[i-2])%10007;
		}
//		System.out.println(Arrays.toString(arr));
		int result = arr[N];
		System.out.println(result);
	}
}
