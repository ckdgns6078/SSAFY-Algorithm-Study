import java.io.*;

public class Main {
    static int maxVal = 3 * 3 * 3 * 3 * 3 * 3 * 3 * 3;
    
    static char[][] arr;

    static void func(int a, int b, int s) {
        if (s == 1) {
            arr[a][b] = '*';
            return;
        }
        s /= 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                func(a + s * i, b + s * j, s);
            }
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        arr = new char[n][n];
        func(0, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
