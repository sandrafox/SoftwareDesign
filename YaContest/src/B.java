import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int maxw = 0, maxh = 0;
        int[][] v = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] a = reader.readLine().split(" ");
            v[i][0] = Integer.parseInt(a[0]);
            v[i][1] = Integer.parseInt(a[1]);
            if (v[i][0] > v[i][1]) {
                int t = v[i][0];
                v[i][0] = v[i][1];
                v[i][1] = t;
            }
            if (maxw < v[i][0]) maxw = v[i][0];
            if (maxh < v[i][1]) maxh = v[i][1];
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (v[i][0] == maxw || v[i][1] == maxh) k++;
        }
        System.out.println(k);
    }
}
