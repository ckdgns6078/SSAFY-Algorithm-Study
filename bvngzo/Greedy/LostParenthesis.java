import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		int[] numbers = Arrays.stream(str.split("[+,-]")).mapToInt(Integer::parseInt).toArray();
		
		int sum = numbers[0];
		
		int idx = 1;
		int temp = 0;
		boolean parenthesis = false;
		for(char c : str.toCharArray()) {
			
			if(!parenthesis) {
				if(c == '+') {
					sum+=numbers[idx++];
				}else if(c == '-') {
					parenthesis = true;
					temp = numbers[idx++];
				}
			}else {
				if(c == '+') {
					temp+=numbers[idx++];
				}else if(c == '-') {
					sum -= temp;
					temp = numbers[idx++];
				}
			}
		}
		
		System.out.println(sum-temp);
	}

}
