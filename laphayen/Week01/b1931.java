/*
 * 회의실 배정
 * 165684KB
 * 1032ms
 * 1064B	
 */

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class b1931 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		LinkedList<Point> l = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			// 링크드 리스트에 요소 추가.
			l.add(new Point(s, e));
		}

		Collections.sort(l, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if (p1.y == p2.y) {
					return Integer.compare(p1.x, p2.x);
				} else {
					return Integer.compare(p1.y, p2.y);
				}
			}
		});

//		// 잘 들어갔는지 확인.
//		for (Point p : l) {
//			System.out.println(p);
//		}
//		
//		System.out.println("-----------------------------");

		int result = 0;
		int lastTime = 0;
		for (Point p : l) {
			if (p.x >= lastTime) {
//				System.out.println(p);
				lastTime = p.y;
				result++;
			}
		}

		System.out.println(result);
		
	}
}
