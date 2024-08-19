package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class N과M_9 {
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static List<int[]> list = new ArrayList<int[]>();
	
	static void permutation(int idx, int k) {
		if(sel.length == k) {
			int[] copySel = Arrays.copyOf(sel, sel.length);
			list.add(copySel);
//			System.out.println("print" + Arrays.toString(sel));
			return;
		}
		if(arr.length == idx) return;
		
		for (int i = 0; i < arr.length; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[idx] = arr[i];
				permutation(idx+1, k+1);
				v[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		sel = new int[M];
		v = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
//		for(int a : arr)
//			System.out.println(a + " ");
		permutation(0, 0);
		List<List<Integer>> tempList = new ArrayList<>();
		for(int[] arr : list) {
			tempList.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
		}
		Set<List<Integer>> set = new LinkedHashSet<List<Integer>>(tempList);
		List<List<Integer>> newlist = new ArrayList<List<Integer>>(set);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < newlist.size(); i++) {
			for (int j = 0; j < newlist.get(i).size(); j++) {
				sb.append(newlist.get(i).get(j)+" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
