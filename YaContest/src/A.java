import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {
    public static void main(String[] args) throws IOException {
        System.out.println((-1) % 26);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<String>> dictionary = new HashMap<>();
        for (String a : reader.readLine().split(" ")) {
            int m = a.length();
            List<String> w = dictionary.get(m);
            if (w == null) {
                w = new ArrayList<>();
                dictionary.put(m, w);
            }
            w.add(a);
        }
        List<String> answers = new ArrayList<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            int l = s.length();
            for (String a : dictionary.get(l)) {
                int diff = (s.charAt(0) - a.charAt(0)) % 26;
                boolean flag = true;
                for (int j = 0; j < l; j++) {
                    flag = diff == (s.charAt(j) - a.charAt(j)) % 26;
                    if (!flag) break;
                }
                if (flag) {
                    answers.add(a);
                    break;
                }
            }
        }
        for (String a : answers) {
            System.out.println(a);
        }

    }
}
