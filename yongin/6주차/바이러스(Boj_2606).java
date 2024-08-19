package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 바이러스_2606 {
	static List<List<Integer>> list;
	static List<Integer> result;
	
	public static void main(String[] args) throws Exception{
		list = new ArrayList<List<Integer>>();
		result = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int computerCounter = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < computerCounter+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int computerIdx = Integer.parseInt(st.nextToken());
			int computerNum = Integer.parseInt(st.nextToken());
			list.get(computerIdx).add(computerNum);
			list.get(computerNum).add(computerIdx);
		}
//		for (int i = 1; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}

//		for(int i=0; i<list.get(1).size(); i++) {
//			int n = list.get(1).get(i);
//			recursive(n);
//		}
		if(list.get(1).size()>0) {
			recursive(1);
		}else {
			result.add(1);
		}
		
//		1. [8]
//		2. [3, 5]
//		3. [2]
//		4. [5, 7]
//		5. [4, 2]
//		6. [7, 9]
//		7. [6, 4]
//		8. [9, 1]
//		9. [8, 6]
		
//		System.out.println(result);
		System.out.println(result.size()-1);
		
		
	}
	static void recursive(int n1) {
		
		for (int i = 0; i < list.get(n1).size(); i++) {
			int n2 = list.get(n1).get(i);
			if(!result.contains(n2)) {
				result.add(n2);
				recursive(n2);
			}
		}
	}
}

//9
//8
//2 3
//4 5
//6 7
//8 9
//2 5
//4 7
//6 9
//1 8
