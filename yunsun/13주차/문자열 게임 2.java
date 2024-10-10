import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	
	    for (int test_case = 0; test_case < T; test_case++) {
	        String W = sc.next();
	        int K = sc.nextInt();
	        int answer1 = Integer.MAX_VALUE;
	        int answer2 = Integer.MIN_VALUE;
	
	        // 문자열의 앞에서부터 한 글자씩 게임의 3,4번 값을 구함
	        L: for (int i = 0; i < W.length(); i++) {
	            if (K == 1) {
	                answer1 = 1;
	                answer2 = 1;
	                break;
	            }
	
	            char alphabet = W.charAt(i);
	
	            // 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구하기
	            // i+1번째 인덱스부터 해당 알파벳을 K-1개 찾음
	            int nextIdx = 0;
	            int currIdx = i;
	            for (int count = 2; count <= K; count++) {
	                nextIdx = W.indexOf(alphabet, currIdx + 1);
	                currIdx = nextIdx;
	                
	                if (nextIdx == -1) {
	                	continue L;
	                }
	            }
	            
	            answer1 = nextIdx - i + 1 < answer1 ? nextIdx - i + 1 : answer1;
	            answer2 = nextIdx - i + 1 > answer2 ? nextIdx - i + 1 : answer2;
	        }
	        
	        if (answer1 == Integer.MAX_VALUE) {
	            System.out.println(-1);
	        } else {
	            System.out.println(answer1 + " " + answer2);
	        }
	    }
	}
}
