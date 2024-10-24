import java.util.*;

class Point implements Comparable<Point>{
	int edge;
	int weight;
	
	public Point(int edge , int weight) {
		this.edge = edge;
		this.weight = weight;
	}
	@Override
	public int compareTo(Point o) {
		return Integer.compare(this.weight , o.weight);
	}
}


public class Main{
	static int n;
	static int m;
	static ArrayList<Point>[] list;
	static int[] dist;
	static int[] resultArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		resultArr = new int[n+1];
		list = new ArrayList[n+1];
		for(int i =0; i< n+1 ; i++) {
			list[i] = new ArrayList();
		}
		
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Point> q = new PriorityQueue();
		
		for(int i=0;i<m ;i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			
			list[start].add(new Point(end , weight));
			list[end].add(new Point(start , weight));
		}
		
		q.offer(new Point(1,0));
		dist[1] = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if(p.weight>dist[p.edge]) {
				continue;
			}
			
			
			for(Point next : list[p.edge]) {
				if(dist[p.edge] + next.weight < dist[next.edge]) {
					dist[next.edge] = dist[p.edge] + next.weight; 
					 q.offer(new Point(next.edge, dist[next.edge])); 
//					resultArr[p.edge] = next.edge;
					 resultArr[next.edge] = p.edge;
				}
			}
			
		}
		StringBuilder sb = new StringBuilder();
		int result=0;
		for(int i=0;i<n+1 ;i++) {
			if(resultArr[i]>0) {
				result++;
				sb.append(i +" " + resultArr[i]);
				sb.append("\n");
			}
		}
		
		System.out.println(result);
		System.out.println(sb);
		
	}
}
