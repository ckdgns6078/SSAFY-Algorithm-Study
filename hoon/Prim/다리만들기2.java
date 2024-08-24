import java.util.*;

/**
 * 먼저 색칠되어 있는 곳들의 섬들을 idx를 부여한다.
 * 섬의 외곽에서 다른 섬으로 연결이 되면 인접리스트에 추가한다. 인접리스트들로 프림을
 * 통해 값을 추출한다.
 */

class Vertex3 implements Comparable<Vertex3>{
	int edge;
	int weight;
	
	Vertex3(int edge  , int weight){
		this.edge = edge;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex3 o) {
		return Integer.compare(this.weight, o.weight);
	}
	

	
}


public class Main {
	static int[][] map;
	static int n, m;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int result;
	static boolean[][] v;
	
	static ArrayList<Vertex3>[] list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		v = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==0) {
					map[i][j]=-1;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] !=-1 && !v[i][j]) {
					bfs(i , j , cnt);
					cnt++;
				}
			}
		}
		
		//인접 리스트를 생성하고 인접리스트를 초기화 시킨다.
		list = new ArrayList[cnt];
		for( int i = 0 ; i < list.length ; i++) {
			list[i] = new ArrayList();
		}
		
		//상하좌우를 검사해서 연결할 수 있는 지 검사하고 연결 할 수 있으면 list에 추가한다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]!=-1) {
					createList(i,j , map[i][j]);
				}
			}
		}
		
		
		//prim 시작
		PriorityQueue<Vertex3> pq = new PriorityQueue<Vertex3>();
		boolean[] v = new boolean[cnt];
		int[] dist = new int[cnt];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new Vertex3(0,0));
		dist[0]=0;
		
		while(!pq.isEmpty()) {
			Vertex3 p = pq.poll();
			
			if(v[p.edge]) {
				continue;
			}
			v[p.edge]=true;
			result+=p.weight;
			
			for( Vertex3 vt : list[p.edge]) {
				if(!v[vt.edge] && vt.weight < dist[vt.edge]) {
					pq.add(vt);
					dist[vt.edge]= vt.weight;
				}
			}
			
		}
		boolean lastCheck = true;
		for( int i = 0 ; i < v.length ; i++) {
			if( !v[i]) {
				lastCheck = false;
			}
		}
		if( lastCheck) {
			System.out.println(result);			
		}else {
			System.out.println(-1);
		}
		
		
		
	}
	private static void createList(int r, int c , int value) {
		for(int i = 0 ; i < 4 ; i++) {
			int cnt =0;
			int rr= r;
			int cc= c;
			while(true) {
				rr += dr[i];
				cc += dc[i];
				
				if(rr>=0 && rr< n && cc>=0 && cc<m) {
					if(map[rr][cc] == value) {
						break;
					}else if( map[rr][cc]==-1){
						cnt++;
					}else if( map[rr][cc]!=-1 && map[rr][cc]!= value) {
						if(cnt>=2) {
							list[value].add(new Vertex3(map[rr][cc], cnt));
							break;
						}else {
							break;
						}
					}
			
				}else {
					break;
				}
				
			}
		}
	}
	private static void bfs(int r, int c , int cnt) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r,c});
		v[r][c] = true;
		map[r][c] = cnt;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for( int i = 0 ; i < 4 ; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if( nr>=0 && nr<n && nc>=0 && nc<m && !v[nr][nc] && map[nr][nc]==1) {
					q.offer(new int[] {nr,nc});
					v[nr][nc] = true;
					map[nr][nc] = cnt;
				}	
			}
		}
		
	}
	
	
}
