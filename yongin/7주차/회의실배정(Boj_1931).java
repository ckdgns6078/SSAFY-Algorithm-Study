import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int ans, result, EndMaxNumber, StartMinNumber;
	static List<int[]> list = new ArrayList<int[]>();
	
	public static void main(String[] args) throws Exception{
		/*
		 * 회의의 수 N
		 * N개의 회의 시작시간과 끝나는 시간이 주어짐
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			int[] a = new int[2];
			st = new StringTokenizer(br.readLine());
			a[0] = Integer.parseInt(st.nextToken());
			a[1] = Integer.parseInt(st.nextToken());
			EndMaxNumber = Math.max(EndMaxNumber, a[1]);
			if(a[0]<=a[1]) list.add(a);
		}
		Collections.sort(list, (o1, o2)->Integer.compare(o1[0], o2[0]));
		Collections.sort(list, (o1, o2)->Integer.compare(o1[1], o2[1]));
	
		
		int endTime = list.get(0)[1];
		int startTime = 0;
		ans = 1;
		for (int i = 1; i < list.size(); i++) {
			if(list.get(i)[0] >= endTime) {
				ans++;
				endTime=list.get(i)[1];
			}
		}
		System.out.println(ans);
		
	}
}


