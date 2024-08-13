import java.util.*;

public class Main {
    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[] peopleOfArea;
    static int[][] adjArea;
    static void seperate(int idx, int k, boolean[] sel){
        if(idx == N+1){
            List<Integer> A1 = new ArrayList<>();
            List<Integer> A2 = new ArrayList<>();
            //////////////////// 구역 분할 ////////////////////////
            for(int i = 1; i <= N; i++){
                if(sel[i]){
                    A1.add(i);
                }else{
                    A2.add(i);
                }
            }
            /////////////////// 구역별 인구수 //////////////////////////
            int numA1 = gary(A1);
            int numA2 = gary(A2);

            if(numA1 != -1 && numA2 != -1) {
                ans = Math.min(ans, Math.abs(numA1-numA2));}

            return;
        }

        sel[idx] = true;
        seperate(idx+1,k+1,sel);

        sel[idx] = false;
        seperate(idx+1,k,sel);
    }

    static int gary(List<Integer> A1){
        if(A1.size() == 0) return -1;
        if(A1.size() == 1) return peopleOfArea[A1.get(0)];

        int startA1 = A1.get(0);
        int numA = 0;                       // A구역 인구 수
        boolean connectedA = false;
        boolean[] visited = new boolean[N+1];
        int numOfNode = 1;

        Stack<Integer> stackA1 = new Stack<>();

        stackA1.push(startA1);
        visited[startA1] = true;
        numA += peopleOfArea[startA1];

        L:while(!stackA1.isEmpty()){
            int node = stackA1.pop();

            for(int adj : adjArea[node]){
                if(A1.contains(adj) && !visited[adj]){
                    stackA1.push(adj);
                    visited[adj] = true;
                    numA += peopleOfArea[adj];
                    numOfNode +=1;
                }
                if(numOfNode == A1.size()){
                    connectedA = true;
                    break L;
                }
            }
        }

        if(connectedA) return numA;
        else return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        peopleOfArea = new int[N+1];

        adjArea = new int[N+1][];

        for (int i = 1; i <= N; i++){
            peopleOfArea[i] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++){
            int adj_n = sc.nextInt();
            int[] adjTemp = new int[adj_n];

            for(int k = 0; k < adj_n; k++){
                adjTemp[k] = sc.nextInt();
            }
            adjArea[i] = adjTemp;
        }

        seperate(1,1,new boolean[N+1]);

        if(ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

    }
}
