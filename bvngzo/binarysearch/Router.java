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
		int C = Integer.parseInt(st.nextToken());
		long[] home = new long[N];
		
		for(int i = 0; i < N; i++) {
			home[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(home);
		
		long end = home[home.length-1] - home[0];
		long start = 1;
		long len = (start + end) / 2;
		
		long ans = -1;
		
		while(start <= end) {
			int cnt = 1;
			long last = home[0];
			boolean flag = false;
			
			for(int i = 1; i < N; i++) {
				if(home[i] >= last + len) {
					cnt++;
					last = home[i];
				}
				
				if(cnt == C) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				ans = Math.max(ans, len);
				start = len+1;
			}
			else {
				end = len-1;
			}
			
			len = (start+end)/2;
			
		}
		System.out.println(ans);
		
	}

}
