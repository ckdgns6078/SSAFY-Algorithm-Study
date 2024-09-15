import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Client {
		int num;
		int arrivedAt;
		int desk;
		int repair;

		public Client(int n, int t) {
			num = n;
			arrivedAt = t;
			desk = -1;
			repair = -1;
		}
	}
	static class Timer{
		int t;
		
		public Timer(int t) {
			this.t = t;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 데스크 수
			int M = Integer.parseInt(st.nextToken()); // 정비소 수
			int K = Integer.parseInt(st.nextToken()); // 고객 수
			int[] target = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) }; // 타겟
			int[] desks = new int[N + 1];
			int[] repairs = new int[M + 1];
			
			Queue<Client> clients = new ArrayDeque();
			Queue<Client> waiting = new ArrayDeque();
			PriorityQueue<Integer> deskNo = new PriorityQueue<Integer>();
			PriorityQueue<Integer> repairNo = new PriorityQueue<Integer>();

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				desks[i] = Integer.parseInt(st.nextToken());
				deskNo.offer(i);
			}
				
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				repairs[i] = Integer.parseInt(st.nextToken());
				repairNo.offer(i);
			}
				
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++)
				clients.offer(new Client(i, Integer.parseInt(st.nextToken())));

			Timer timer = new Timer(clients.peek().arrivedAt); // 가장먼저 온 손닝의 도착 시간
			int ans = 0;

			PriorityQueue<Client> onRepair = new PriorityQueue<Client>((o1, o2) -> Integer
					.compare(o1.arrivedAt + repairs[o1.repair] - timer.t, o2.arrivedAt + repairs[o2.repair] - timer.t));
			PriorityQueue<Client> onDesk = new PriorityQueue<Client>((o1, o2) -> 
					(o1.arrivedAt + desks[o1.desk] - timer.t == o2.arrivedAt + desks[o2.desk] - timer.t) 
					? Integer.compare(o1.desk, o2.desk) 
					: Integer
					.compare(o1.arrivedAt + desks[o1.desk] - timer.t, o2.arrivedAt + desks[o2.desk] - timer.t));
			
			while (true) {
				while(!onRepair.isEmpty()) {				// 정비소 처리
					 Client cl = onRepair.peek();
					 if(cl.arrivedAt + repairs[cl.repair] - timer.t == 0) {
						 onRepair.poll();
						 repairNo.offer(cl.repair);
						 if(cl.desk == target[0] && cl.repair == target[1]) {
							 ans += cl.num;
						 }
					 }else {
						 break;
					 }
				}
				
				while(!waiting.isEmpty()) {
					if(!repairNo.isEmpty()) {
						Client cl = waiting.poll();
						cl.arrivedAt = timer.t;
						cl.repair = repairNo.poll();
						onRepair.offer(cl);
					}else break;
				}

				while(!onDesk.isEmpty()) {				// 데스크 처리
					 Client cl = onDesk.peek();
					 if(cl.arrivedAt + desks[cl.desk] - timer.t <= 0) {
						 onDesk.poll();
						 if(!repairNo.isEmpty()) {
							 cl.arrivedAt = timer.t;
							 cl.repair = repairNo.poll();
							 onRepair.offer(cl);
							 deskNo.offer(cl.desk);
						 }else {
							 waiting.offer(cl);
							 deskNo.offer(cl.desk);
						 }
						 
					 }else {
						 break;
					 }
				}
				
				while(!clients.isEmpty()) {
					Client cl = clients.peek();
					if(cl.arrivedAt <= timer.t && !deskNo.isEmpty()) {
						clients.poll();
						cl.arrivedAt = timer.t;
						cl.desk = deskNo.poll();
						onDesk.offer(cl);
					}else {
						break;
					}
				}
				
				if(clients.isEmpty() && onDesk.isEmpty() && onRepair.isEmpty()) break;
				
				timer.t++;

			}// end while
			ans = (ans == 0) ? -1 : ans;
			System.out.printf("#%d %d\n",tc,ans);

		}
	}

}
