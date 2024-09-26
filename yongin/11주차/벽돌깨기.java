import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// solve
// 1. 벽돌 개수 N개 크기만큼, 1~W 만큼 중복순열을 만든다.
// 2. 배열을 복사한 temp 배열에 중복순열에서 만든 리스트에서 값을 빼와 벽돌을 떨어뜨린다.
// 3. 하나씩 떨어뜨릴때마다, 방문체크를 하여 벽돌을 동시에 제거한다.
// 4. 제거될 때 마다 제거된 벽돌의 개수를 센다.
// 5. 이를 반복한다.

public class Solution {
	static int N, W, H, result;
	static int[][] arr;
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	static List<int[]> list;
	
	static class Point{
		int r, c, curValue;
		Point(int r, int c, int curValue){
			this.r=r;
			this.c=c;
			this.curValue=curValue;
		}
	}
	
	static int[][] copyArr(int[][] temp) {
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		
		return temp;
	}
	
	static void print(int[][] temp) {
		for (int i = 0; i < H; i++) {
			System.out.println(Arrays.toString(temp[i]));
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벽돌 개수
			W = Integer.parseInt(st.nextToken()); // col
			H = Integer.parseInt(st.nextToken()); // row
			arr = new int[H][W];
			list = new ArrayList<int[]>();
			result = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1~W 만큼 중복순열을 구한다.
			int[] data = new int[W];
			for (int i = 0; i < data.length; i++) {
				data[i] = i;
			}
//			System.out.println(Arrays.toString(data));
			int[] sel = new int[N];
			repeat_pmt(0, data, sel);
			// list : 벽돌 떨어 뜨리는 위치의 배열이 들어 있는 리스트
			
			for (int stone = 0; stone < list.size(); stone++) {
				int[][] temp = new int[H][W];
				temp = copyArr(temp);
//				print(temp);
				int[] stoneArr = list.get(stone);
				down(temp, stoneArr);
			}
//			System.out.println("result: " + result);
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void down(int[][] temp, int[] stoneArr) {
		// 벽돌을 떨어뜨린다.
//		System.out.println(Arrays.toString(stoneArr));
		for (int s = 0; s < stoneArr.length; s++) {
			int stoneIdx = stoneArr[s];
			
			// 벽돌을 떨어뜨리려는 위치에 벽돌이 없는 경우
			boolean isEmpty = true;
			int x = 0;
			while(true) {
				if(temp[x][stoneIdx]!=0) {
					isEmpty = false;
					break;
				}
				x++;
				if(x==H) break;
			}
			
			// 벽돌이 없는 경우면 break;
			if(isEmpty) break;
			
//5
//3 10 10
//0 0 0 0 0 0 0 0 0 0
//1 0 1 0 1 0 0 0 0 0
//1 0 3 0 1 1 0 0 0 1
//1 1 1 0 1 2 0 0 0 9
//1 1 4 0 1 1 0 0 1 1
//1 1 4 1 1 1 2 1 1 1
//1 1 5 1 1 1 1 2 1 1
//1 1 6 1 1 1 1 1 2 1
//1 1 1 1 1 1 1 1 1 5
//1 1 7 1 1 1 1 1 1 1
//			System.out.println(x);
			// 벽돌이 있는 경우면 벽돌을 제거한다.
			Queue<Point> queue = new ArrayDeque<>();
			queue.offer(new Point(x, stoneIdx, temp[x][stoneIdx]));
			temp[x][stoneIdx]=0;
			
			while(!queue.isEmpty()) {
				Point p = queue.poll();
				
				for (int i = 1; i < p.curValue; i++) {
					for (int d = 0; d < 4; d++) {
						int nr = p.r + dr[d] * i;
						int nc = p.c + dc[d] * i;
						
						if(nr>=0 && nr<H && nc>=0 && nc<W && temp[nr][nc]!=0) {
//							System.out.println("들어가냐?");
							queue.offer(new Point(nr, nc, temp[nr][nc]));
							temp[nr][nc]=0;
						}
					}
				}
			}

//			print(temp);
			// 블록을 내린다.
			Stack<Integer> stack = new Stack<Integer>();
			
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					if(temp[j][i] !=0) {
						stack.add(temp[j][i]);
					}
				}
				for (int j = H-1; j>=0; j--) {
					if(stack.isEmpty()) {
						temp[j][i] = 0;
					}else {
						temp[j][i] = stack.pop();
					}
				}
			}
		}
		// 벽돌의 개수를 센다.
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(temp[i][j]!=0) {
					cnt++;
				}
			}
		}
		result = Math.min(result, cnt);
		
	}

	private static void repeat_pmt(int k, int[] data, int[] sel) {
		// TODO Auto-generated method stub
		if(sel.length == k) {
			int[] copysel = new int[N];
			for (int i = 0; i < sel.length; i++) {
				copysel[i] = sel[i];
			}
			list.add(copysel);
//			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int i = 0; i < data.length; i++) {
			sel[k] = data[i];
			repeat_pmt(k+1, data, sel);
		}
		
		
	}
}



//1
//3 10 10
//0 0 0 0 0 0 0 0 0 0
//1 0 1 0 1 0 0 0 0 0
//1 0 3 0 1 1 0 0 0 1
//1 1 1 0 1 2 0 0 0 9
//1 1 4 0 1 1 0 0 1 1
//1 1 4 1 1 1 2 1 1 1
//1 1 5 1 1 1 1 2 1 1
//1 1 6 1 1 1 1 1 2 1
//1 1 1 1 1 1 1 1 1 5
//1 1 7 1 1 1 1 1 1 1



//1
//2 9 10
//0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0
//0 1 0 0 0 0 0 0 0
//0 1 0 0 0 0 0 0 0
//1 1 0 0 1 0 0 0 0
//1 1 0 1 1 1 0 1 0
//1 1 0 1 1 1 0 1 0
//1 1 1 1 1 1 1 1 0
//1 1 3 1 6 1 1 1 1
//1 1 1 1 1 1 1 1 1
