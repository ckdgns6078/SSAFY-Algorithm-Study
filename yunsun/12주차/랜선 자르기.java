// 구간탐색 부분에서 버그가 나서 잡는데에 너무 힘들었다.
// end = '가장 긴 랜선의 길이'로 둘 경우, 
// 1개를 1개로 만드는 경우에 '가장 긴 랜선의 길이', 즉 end값은 영원히 답이 될 수 없을 수도 있다.
// 이 경우를 고려해서 end에 '내가 넣고 싶은 end값 + 1'을  저장해주었고,
// 랜선의 길이가 최대 int의 최대범위(2^31 -1)까지 가능하기 때문에 
// int를 초과할 수 있는 변수들에 대해서 long으로 설정해주었다.

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] wires = new int[K];
        long maxWire = Integer.MIN_VALUE;
        long start, end, mid, newMid = 0;
        int sum;
        
        for (int i = 0; i < K; i++) {
            wires[i] = sc.nextInt();
            maxWire = wires[i] > maxWire ? wires[i] : maxWire;
        }
        
        start = 1;
        end = maxWire + 1;
        mid = (start + end) / 2;
        
        while(true) {
        	sum = 0;
        	
            for (int i = 0; i < K; i++) {
            	long wiresNum = wires[i] / mid;
            	sum += wiresNum;
            }
            
            if (sum < N) {
            	// end가 mid-1이어도 되지만 그러면 영원히 mid-1에 도달하지 못하기 때문에 mid로 해
                end = mid;
            } else {
                start = mid;
            }
            
            newMid = (start + end) / 2;
            
            if (mid == newMid)
            	break;
            else
            	mid = newMid;
        }
        
        System.out.println(mid);
        sc.close();
    }
}
