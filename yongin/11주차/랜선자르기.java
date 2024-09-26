import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int K, N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		long left = 0;
		long right = arr[arr.length-1];
		long mid = 0;
		right++;
//		System.out.println(right);
		
		// N개보다 많이 드는 것도 N개를 만드는 것에 포함
		
		// 
		while(right > left) {
			mid = (left+right)/2;
			long cnt = 0;
			for (int i = 0; i < arr.length; i++) {
				cnt+=(arr[i]/mid);
			}
//			System.out.println(cnt);
//			break;
			if(cnt<N) {
				right = mid;
			}else {
				left = mid+1;
			}
//			System.out.println(left + " " + right + " " + mid);
//			System.out.println(cnt);
		}
		System.out.println(left-1);
	}
}
//1 2 3 10000
