import java.util.*;

public class Main {

	static int n;
	static int[] arr;
	static int position;
	static int answer = 0;
	static int[][] map;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		position = sc.nextInt();
		
		recursive(n , arr , 0);
		System.out.println(answer);
		
	}
	
	public static void recursive2(int turn , int[] gameList , int result) {
		if(mafiaCheck(gameList) || citizenCheck(gameList)) {
			answer = Math.max(answer , result);
			return;
		}
		
		
		if(turn%2==0) {
			for(int i=0;i<n;i++) {
				if(i!=position && gameList[i]!=888) {
					int[] tempList = gameList.clone();
					gameList[i] = 888;
					
					for(int j=0;j<n;j++) {
						if(gameList[j]!=888) {
							gameList[j] = map[i][j];
						}
					}
					recursive(turn-1 , gameList , result +1);
					for(int j=0;j<n;j++) {
						gameList[i] = tempList[i];
					}
				}
			}
		}else {
			int cnt =0;
			int min = Integer.MIN_VALUE;
			for(int i=0;i<n;i++) {
				if(gameList[i]!=888) {
					if(min < gameList[i]) {
						min = Math.max(min , gameList[i]);
						cnt = i;
						
						
					}
				}
			}
			int temp = gameList[cnt];
			gameList[cnt] = 888;
			recursive(turn -1 , gameList , result);
			gameList[cnt] = temp; 
		}
	}

	public static void recursive(int turn, int[] gameList, int result) {
		if (mafiaCheck(gameList) || citizenCheck(gameList)) {
			answer = Math.max(answer, result);
			return;
		}

		if (turn % 2 == 0) {  // 밤 (은진이의 차례)
			for (int i = 0; i < n; i++) {
				if (gameList[i] != 888 && i != position) {
					int originData = gameList[i];
					mafia(i, gameList);					
					recursive(turn - 1, gameList, result + 1);
					reset(i , gameList , originData);
				}
			}
		} else {  // 낮 (시민들의 차례)
			int cnt =0;
			int min = Integer.MIN_VALUE;
			for(int i=0;i<n;i++) {
				if(gameList[i]!=888) {
					if(min < gameList[i]) {
						min = Math.max(min , gameList[i]);
						cnt = i;
						
						
					}
				}
			}
			int temp = gameList[cnt];
			gameList[cnt] = 888;
			recursive(turn -1 , gameList , result);
			gameList[cnt] = temp; 
		}
	}

	private static void reset(int idx, int[] gameList , int originData) {
		for(int i=0;i<n;i++) {
			if(gameList[i]!=888) {
				gameList[i] -= map[idx][i];
			}
		}		
		gameList[idx] = originData;
	}

	public static void mafia(int idx, int[] gameList) {
		gameList[idx] = 888;
		for (int i = 0; i < n; i++) {
			if (gameList[i] != 888) {
				gameList[i] += map[idx][i];
			}
		}
	}
	
	public static void citizen(int[] gameList) {
		int maxValue = Integer.MIN_VALUE;
		int maxIndex = -1;
		for (int i = 0; i < n; i++) {
			if (gameList[i] != 888 && gameList[i] > maxValue) {
				maxValue = gameList[i];
				maxIndex = i;
			}
		}
		// 유죄 지수가 가장 높은 사람 죽이기
		if (maxIndex != -1) {
			gameList[maxIndex] = 888;
		}
	}
	
	private static boolean mafiaCheck(int[] gameList) {		
		if(gameList[position] == 888) {
			return true;
		}
		return false;
	}
	
	private static boolean citizenCheck(int[] gameList) {
		int cnt =0;
		for(int i=0;i<gameList.length;i++) {
			if(i!=position && gameList[i]!=888) {
				cnt++;
			}
		}
		if(cnt==0) {
			return true;
		}else {
			return false;
		}		
	}
	
	
}
