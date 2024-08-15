import java.util.*;

public class Main {

    static int[][] M;
    static int N;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        M = new int[N+1][N+1];

        int[] score = new int[N+1];

        while(true){
            int f1 = sc.nextInt();
            int f2 = sc.nextInt();

            if(f1 == -1 && f2 == -1) break;

            M[f1][f2] = 1;
            M[f2][f1] = 1;
        }

        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= N; i++){
            score[i] = bfs(i);
            min = Math.min(score[i], min);
        }

        ArrayList<Integer> candidates = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            if(score[i] == min){
                candidates.add(i);
            }
        }

        System.out.println(min+" "+candidates.size());
        candidates.forEach(i-> System.out.print(i+" "));


    }


    static int bfs(int s){
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[N+1];

        q.offer(s);
        dist[s] = -1;

        int len = 1;
        int max = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            int len_q = q.size();

            for(int k =0 ; k < len_q; k++){
                int temp = q.poll();

                for (int i = 1; i <= N; i++) {
                    if (M[temp][i] == 1) {
                        if (dist[i] == 0) {
                            dist[i] = len;
                            q.offer(i);
                            max = Math.max(max,dist[i]);
                        } else {
                            dist[i] = Math.min(len, dist[i]);
                        }
                    }
                }
            }
            len++;
        }

      return max;
    }

    static void print(){
        for(int[] arr : M){
            for(int a : arr){
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }
}
