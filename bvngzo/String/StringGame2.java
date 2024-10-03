import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			char[] cc = sc.next().toCharArray();
			int n = sc.nextInt();
			
			ArrayList<Integer>[] alpha = new ArrayList['z'-'a'+1];
			
			for(int i = 0; i < cc.length; i++) {
				if(alpha[cc[i]-'a']==null) 
					alpha[cc[i]-'a'] = new ArrayList<Integer>();
				
				alpha[cc[i]-'a'].add(i);
			}
			
			int a1 = Integer.MAX_VALUE;
			int a2 = Integer.MIN_VALUE;
			
			for(int i = 0; i <= 'z'-'a'; i++) {
				if(alpha[i] == null) continue;
				
				int len = alpha[i].size();
				if(len<n) continue;
				
				for(int j = 0; j < len; j++) {
					// 로직 처리
					if(j+n-1 < len) {
						a1 = Math.min(a1, alpha[i].get(j+n-1)-alpha[i].get(j)+1);
						a2 = Math.max(a2, alpha[i].get(j+n-1)-alpha[i].get(j)+1);
					}
					else break;
				}
			}
			
			if(a1 == Integer.MAX_VALUE || a2 == Integer.MIN_VALUE)
				System.out.println(-1);
			else
				System.out.println(a1+" "+a2);
		}
	}

}
