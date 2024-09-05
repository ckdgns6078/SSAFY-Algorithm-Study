import java.util.*;

class Micro {
	int r;
	int c;
	int size;
	int dir;

	Micro(int r, int c, int size, int dir) {
		this.r = r;
		this.c = c;
		this.size = size;
		this.dir = dir;
	}

}

public class Solution {

	static int N; // 배열의 크기
	static int M; // 반복 횟수
	static int K; // 미생물의 갯수
	static ArrayList<Micro> mlist;		//군집 list
	static ArrayList<Micro>[][] list;	//map list
	static int[] dr = { 0 , -1 , 1 , 0 , 0 };
	static int[] dc = { 0 , 0 , 0 , -1 , 1 };
	
	

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			mlist = new ArrayList();
			list = new ArrayList[N][N];
			
			for(int i = 0 ; i < N ; i++) {
				for( int j = 0 ; j < N ; j++) {
					list[i][j] = new ArrayList();
				}
			}
			
			
			
			for(int i = 0 ; i < K ; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
				int size = sc.nextInt();
				int dir = sc.nextInt();
				mlist.add(new Micro(r,c,size,dir));
			}
			
			
			//전체 반복 반복문
			for(int k = 0 ; k < M ; k++) {
				
				//mlist안에 있는 미생물들을 이동 시킨다.
				for(int i = 0 ; i < mlist.size(); i++) {
					Micro mic = mlist.get(i);
					mic.r+= dr[mic.dir];
					mic.c+= dc[mic.dir];
					mlist.set(i, mic);
					list[mic.r][mic.c].add(mic);
				}
				//
				for(int i=0 ; i < N ; i++) {
					for(int j=0 ; j < N ; j++) {
						if(list[i][j].size()>0) {
							if(i==0 || i == N-1 || j==0 || j==N-1) {
								Micro mic = list[i][j].get(0);
								mic.dir = changeDir(mic.dir);
								mic.size = mic.size/2;
								
								// 변경된 값 반영하기
								for(int t = 0 ; t < mlist.size() ; t++) {
									Micro m = mlist.get(t);
									if(m.r == mic.r && m.c == mic.c) {
										mlist.set(t, mic);
										break;
									}
								}
							//list 에 여러개의 미생물이 군집해 있을때
							}else if(list[i][j].size()>1) {
								int dir =0 ;
								int size =0;
								int sizeSum = 0;
								
								for(int l = 0 ; l < list[i][j].size() ; l++) {
									Micro mic = list[i][j].get(l);
									sizeSum+= mic.size;
									if(mic.size>size) {
										dir = mic.dir;
										size = mic.size;
									}
								}
								int cnt = list[i][j].size();
								for(int t = mlist.size()-1 ; t>=0 ; t--) {
									Micro mic = mlist.get(t);
									if(i == mic.r && j == mic.c) {
										mlist.remove(t);
										cnt--;
									}
									if(cnt==0) {
										break;
									}
								}
								mlist.add(new Micro(i,j,sizeSum,dir));
							}
						}
					}
				}
				
				for(int a = 0 ; a < N ; a++) {
					for(int b = 0 ; b < N ; b++) {
						if(list[a][b].size()>0) {
							list[a][b] = new ArrayList();
						}
					}
				}
			}
			
			int result = 0;
			for(int i =0 ; i < mlist.size() ; i++) {
				Micro mic = mlist.get(i);
				result+= mic.size;
			}
			
			
			System.out.println("#" + tc +" " + result);
			
			
			
			
		}
	}
	
	//방향을 변경하는 코드 0 , n-1에 도착할 경우 사용
	//상: 1, 하: 2, 좌: 3, 우: 4
	public static int changeDir(int dir) {
		if( dir == 1) {
			return 2;
		}else if( dir ==2) {
			return 1;
		}else if (dir == 3) {
			return 4;
		}else if ( dir == 4) {
			return 3;
		}
		
		return 0;
		
	}
	
	
}
