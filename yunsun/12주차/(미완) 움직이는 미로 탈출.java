import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// DFS 사용? BFS 사용?
// 움직일 때 그 칸이랑 그 위에 칸이 비어있으면 이동 가능
// 근데 현재 위치에 서 있을 수 있고, 지나간 칸도 다시 갈 수 있는데 어떻게 오른쪽 위로 갈 수 있는 지 체크하지..?
// 다 뚫려있을 때 무한 로딩을 어떻게 막지....DFS를 쓰면 안되는걸까
// 아래로 내려갈 필요가 있을까? 필요없을 거 같은데
// 위에 칸을 갈 수 있다면(다음에 안 깔린다는 보장 하에) 굳이 같은 줄 칸도 검사할 필요 없음
class User {
	int r, c;
	User(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	@Override
	public String toString() {
		return "(" + this.r + "," + this.c + ")";
	}
}

public class boj16954 {
	static char[][] map = new char[8][8];
	static int[] dr = {-1, -1, -1, 0, 0, 0};
	static int[] dc = {1, 0, -1, 1, 0, -1};
	static Queue<User> queue = new LinkedList<User>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 8; i++) {
			String s = sc.next();
			for (int j = 0; j < 8; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		bfs(new User(7, 0));
		
		sc.close();
	}

	private static void bfs(User u) {
		boolean flag = false;
		User user = u;
		queue.add(user);
		
		L: while(!queue.isEmpty()) {
			int queueSize = queue.size();
			System.out.println("Queue size: " + queueSize);
			
			// 한 칸 이동한 후 벽 내리기
			for (int k = 0; k < queueSize; k++) {
				boolean goUp = false;
				User curr = queue.poll();
//				System.out.println("curr.r: " + curr.r);
//				System.out.println("curr.c: " + curr.c);
//				System.out.println();
				if (curr.r == 0 && curr.c == 7) {
					flag = true;
					break L;
				}
				
				// 위쪽으로 올라가려 시도
				for (int i = 0; i < 3; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];
					// 이동할 칸이 체스판 내에 있고, 비어 있으며, 그 위에 칸도 비어 있다면
					if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && map[nr][nc] == '.') {
						if (queue.contains(new User(nr, nc))) {
							System.out.println("dddd");
							goUp = true;
						}
						else if (nr != 0) {
							if (map[nr-1][nc] == '.') {
								queue.add(new User(nr, nc));
								goUp = true;
							}
						} else {
							queue.add(new User(nr, nc));
							goUp = true;
						}
					}
				}
				// 위쪽 줄이 안되면 같은 줄에서 시도
				if (!goUp) {
					for (int i = 3; i < 6; i++) {
						int nr = curr.r + dr[i];
						int nc = curr.c + dc[i];
						// 이동할 칸이 체스판 내에 있고, 비어 있으며, 그 위에 칸도 비어 있다면
						if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && map[nr][nc] == '.') {
							if (queue.contains(new User(nr, nc))) {
								goUp = true;
							} else if (nr != 0) {
								if (map[nr-1][nc] == '.') {
									queue.add(new User(nr, nc));
									goUp = true;
								}
							} else {
								queue.add(new User(nr, nc));
								goUp = true;
							}
						}
					}
				}
			}
			
			for (int i = 6; i < 0; i++) {
				for (int j = 0; j < 8; j++) {
					map[i+1][j] = map[i][j];
				}
			}
			for (int j = 0; j < 8; j++) {
				map[0][j] = '#';
			}
			
			
		}
		
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
		
	}
}
