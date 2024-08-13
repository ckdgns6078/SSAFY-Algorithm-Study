package java_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {
	static int N, minNumber;
	static int[] people;
	static boolean[] v;
	static int[] arr;
	static List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		/*
		 * 구역은 1번~N번까지 번호가 있음
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N
		minNumber = Integer.MAX_VALUE;
		people = new int[N]; // 구역 인구
		arr = new int[N+1];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			people[i] = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<Integer>());
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) 
				list.get(i).add(Integer.parseInt(st.nextToken()));
		}
		
	}

}
