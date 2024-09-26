import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		long min = 0;
		long max = 0;
		long mid = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		while(max > min) {
			mid = (max+min)/2; // 절단기 높이
			
			long sum = 0;
			for (int i = 0; i < arr.length; i++) {
				if(arr[i]>=mid) {
					sum += arr[i]-mid;
				}
			}

			// H가 높을수록 가져가는 나무의 개수는 작다.
			
			// cnt가 구하려고 하는 나무의 개수보다 작으면 H가 높다는 뜻
			if(sum < M) {
				max = mid; // max 값을 mid로 줄인다.
			}
			// cnt가 구하려고 하는 나무의 개수보다 높으면 H가 작다는 뜻
			else {
				min = mid+1; // min 값을 mid+1로 증가한다.
			}
		}
		System.out.println(min-1);
	}
}

//4 7
//20 15 10 17

//4 7
//1 2 3 1
