import java.util.*;


class Vertex implements Comparable<Vertex>{
	int edge;		//정점
	int weight;		//가중치
	
	Vertex(int edge , int weight){
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex o) {
		return Integer.compare(this.weight, o.weight);
	}
}



public class Main {

	static ArrayList<Vertex>[] list;
	static int n;
	static boolean[] visited;
	static int[] dist;
	static int[][] map;
	static long result =0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n= sc.nextInt();
		map= new int[n][n];
		
		visited = new boolean[n];
		list = new ArrayList[n];
		dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		//리스트 초기화
		for(int i = 0 ; i < n ; i++) {
			list[i] = new ArrayList();
		}
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if(i !=j ) {
					list[i].add(new Vertex(j , map[i][j]));
//					list[j].add(new Vertex(i , map[j][i]));
				}
			}
		}

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(0,0));
		dist[0] = 0;

		while(!pq.isEmpty()) {
			Vertex p = pq.poll();
			if(visited[p.edge]) {
				continue;
			}
			visited[p.edge] = true;
			result+= p.weight;
			
			for( Vertex vt : list[p.edge]) {
				if(!visited[vt.edge] && vt.weight < dist[vt.edge]) {
//					pq.add(new Vertex(vt.edge, vt.weight));
					pq.add(vt);
					dist[vt.edge] = vt.weight;
					
				}
			}
			
		}

		System.out.println(result);
	}

}
