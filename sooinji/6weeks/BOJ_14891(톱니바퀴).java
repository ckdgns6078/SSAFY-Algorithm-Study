import java.util.*;

public class Main {
	static String[] wheels = new String[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			wheels[i] = sc.next();
		}
		int k = sc.nextInt();
		int num, dir;
		for (int i = 0; i < k; i++) {
			num = sc.nextInt() - 1;
			dir = sc.nextInt();
			boolean[] check = new boolean[4];
			check[num] = true;
			if (num == 0) {
				if(wheels[0].charAt(2) != wheels[1].charAt(6)) {
					check[1] = true; // 반대
				}
				if(check[1] && wheels[1].charAt(2) != wheels[2].charAt(6)) {
					check[2] = true;
				}
				if(check[2] && wheels[2].charAt(2) != wheels[3].charAt(6)) {
					check[3] = true; // 반대
				}
				for (int j = 0; j < 4; j++) {
					if(check[j]) {
						if (j % 2 == 0) rotation(j, dir);
						else rotation(j, dir * (-1));
					}
				}
			}
			else if (num == 1) {
				if(wheels[0].charAt(2) != wheels[1].charAt(6)) {
					check[0] = true;
				}
				if (wheels[1].charAt(2) != wheels[2].charAt(6)) {
					check[2] = true;
				}
				if(check[2] && wheels[2].charAt(2) != wheels[3].charAt(6)) {
					check[3] = true;
				}
				for (int j = 0; j < 4; j++) {
					if(check[j]) {
						if (j % 2 == 0) rotation(j, dir * (-1));
						else rotation(j, dir);
					}
				}
			}
			else if (num == 2) {
				if(wheels[2].charAt(2) != wheels[3].charAt(6)) {
					check[3] = true;
				}
				if (wheels[1].charAt(2) != wheels[2].charAt(6)) {
					check[1] = true;
				}
				if(check[1] && wheels[0].charAt(2) != wheels[1].charAt(6)) {
					check[0] = true;
				}
				for (int j = 0; j < 4; j++) {
					if(check[j]) {
						if (j % 2 == 0) rotation(j, dir);
						else rotation(j, dir * (-1));
					}
				}
			}
			else if (num == 3) {
				if(wheels[2].charAt(2) != wheels[3].charAt(6)) {
					check[2] = true;
				}
				if(check[2] && wheels[1].charAt(2) != wheels[2].charAt(6)) {
					check[1] = true;
				}
				if(check[1] && wheels[0].charAt(2) != wheels[1].charAt(6)) {
					check[0] = true;
				}
				for (int j = 0; j < 4; j++) {
					if(check[j]) {
						if (j % 2 == 0) rotation(j, dir * (-1));
						else rotation(j, dir);
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			ans += ((int)wheels[i].charAt(0) - '0') * (1 << i);
		}
		System.out.println(ans);
	}
	private static void rotation(int num, int dir) {
		if (dir == 1) {
			wheels[num] = wheels[num].charAt(7) + wheels[num].substring(0, 7);
		}
		else {
			wheels[num] = wheels[num].substring(1, 8) + wheels[num].charAt(0);
		}
	}
}
