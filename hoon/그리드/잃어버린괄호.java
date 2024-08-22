import java.util.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String input = sc.next();
		
		String[] arr = input.split("\\-");
		
		
		int result = 0;
		int min = 0;
		for( int i = 0  ; i < arr.length ; i++) {
			String[] cut = arr[i].split("\\+");
			
			if(i==0) {
				for (int j = 0; j < cut.length; j++) {
					result+= Integer.parseInt(cut[j]);
				}
			}else {
				for (int j = 0; j < cut.length; j++) {
					min += Integer.parseInt(cut[j]);
				}
			}
		
		}
		
		System.out.println(result-min);
		
		
		
	}

}
