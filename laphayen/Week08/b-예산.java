import java.util.Scanner;

public class Main {
    static int result = 0;  // 출력해야 하는 최대 예산

    public static void binary(int[] lst, int start, int end, int M) {
        if (start > end) {  // 검색 실패
            return;
        } else {
            int mid = (start + end) / 2;  // 중앙 원소 고르기
            int total = 0;  // 예산의 합

            for (int l : lst) {
                if (l > mid) {
                    total += mid;
                } else {
                    total += l;
                }
            }

            if (total <= M) {  // M 이하 이면 중앙값+1 ~ 끝 값 다시 찾기
                result = mid;
                binary(lst, mid + 1, end, M);
            } else {
                binary(lst, start, mid - 1, M);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] lst = new int[N];
        
        for (int i = 0; i < N; i++) {
            lst[i] = sc.nextInt();
        }
        
        int M = sc.nextInt();
        int start = 1;
        int end = getMax(lst);  // 최댓값을 끝으로 설정
        
        binary(lst, start, end, M);
        System.out.println(result);
        
        sc.close();
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }
}
