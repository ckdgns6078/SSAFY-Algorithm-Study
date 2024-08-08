package baekjun.simulation;


import java.util.*;

public class Baseball {
	
    static class Player{
        int no;
        int[] record;
        int loc;

        public Player(int no){
            this.no = no;
            record = new int[N];
            loc = 0;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "no=" + no +
                    ", record=" + Arrays.toString(record) +
                    '}';
        }
    }

    static void permutation(int k, Player[] sel, boolean[] v){
        if(k == sel.length){
            sel[3] = players[0];

            // 야구 합시다.
            int idx = 1;
            int score = 0;
            for(int i =0; i < N; i++){// 이닝
                int out = 0;
                Player[] field = new Player[4];// 야구장
                while(out<3){
                    int player_no = ((idx%9 == 0) ? 9 : idx%9) - 1;
                    Player hitter = sel[player_no];
                    if(hitter.record[i] == 0){
                        out++; 
                    }
                    else{
                        int hit = hitter.record[i];
                        field[0] = hitter;
                        for(int p = 3; p >= 0; p--) {
                        	if(field[p] == null)continue;
                        	Player temp = field[p];
                        	if(temp.loc+hit>3) {
                        		temp.loc = 0;
                        		score += 1;
                        		field[p] = null;
                        	}else {
                        		//진루자처리
                        		temp.loc += hit;
                        		field[temp.loc] = temp;
                        		field[p] = null;
                        	}
                        }
                        field[0] = null;
                    }
                    idx++;
                }// end baseball game
                for(int p = 3; p >= 0; p--) {
                	if(field[p] == null)continue;
                	field[p].loc = 0;
                }

                ans = Math.max(ans, score);

            }// end for
            return;
        }
        if(k==3) permutation(k+1,sel,v);
        for(int i = 1; i < players.length; i++){
            //if(i==3) continue;
            if(v[i] == false){
                sel[k] = players[i];
                v[i] = true;
                permutation(k+1,sel,v);
                v[i] = false;
            }
        }



    }
    static Player[] players = new Player[9];
    static boolean[] visited = new boolean[9];
    static int N;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < 9; j++){
                if(players[j] == null){players[j] = new Player(j);}
                players[j].record[i] = sc.nextInt();
            }
        }

        permutation(0, new Player[9], visited);
        System.out.println(ans);


    }
}
