import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		long[] hos = new long[N];
		
		for(int i = 0; i < N; i++) {
			hos[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(hos);
		
		long hi = hos[0] + K;
		long lo = hos[0];
		
		long ans = -1;
		
		while(lo <= hi) {
			long target = (lo + hi)/2;
			Long lv = K;
			boolean flag = true;
			
			for(int i = 0; i < N; i++) {
				if((target - hos[i])<0) {		// 목표 레벨 달성
					flag = true;
					break;
				}
					
				if(lv < target - hos[i]) {		// 목표 레벨 실패
					flag = false;
					break;
				}
				
				lv -= (target - hos[i]);
			}
			
			if(flag) {
				ans = Math.max(ans, target);
				lo = target + 1;
			}else {
				hi = target-1;
			}
		
		}// end while
		
		System.out.println(ans);
		
	}

}
