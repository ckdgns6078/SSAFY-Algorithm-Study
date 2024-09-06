import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TreasurePW {

	public static void main(String[] args) {
		

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			Set<String> words = new HashSet();
						
			String str = sc.next();
			
			int len = N/4;
			
			for(int i = 0; i < len; i++) {
				for(int j = 0; j < N; j+=len) {
					String temp = str.substring(j,j+len);
					words.add(temp);
				}
				str = rotate(str);
				
			}
		
			String[] wordList = words.toArray(new String[words.size()]);
			
			Arrays.sort(wordList, (s1,s2) -> -Integer.compare(Integer.parseInt(s1,16), Integer.parseInt(s2,16)));
			System.out.printf("#%d %d\n",tc,Integer.parseInt(wordList[K-1],16));
			
		}
		
	}
	
	static String rotate(String s) {
		return s.charAt(s.length()-1) + s.substring(0, s.length()-1);
	}
	
}
