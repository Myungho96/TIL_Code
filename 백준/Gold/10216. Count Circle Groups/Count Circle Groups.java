import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    static void make(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    static int find(int num1) {
        if (parents[num1] == num1)
            return num1;
        return parents[num1] = find(parents[num1]);
    }

    public static void union(int num1, int num2) {
        num1 = find(num1);
        num2 = find(num2);

        if (num1 != num2) {
            if (num1 < num2) {
                parents[num2] = num1;
            } else {
                parents[num1] = num2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] temp;
            List<int[]> lists = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp = br.readLine().split(" ");
                lists.add(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])});
            }

            make(n);
            int result = n;
            for (int i = 0; i < n; i++) {
                int[] arr1 = lists.get(i);
                for (int j = i + 1; j < n; j++) {
                    int[] arr2 = lists.get(j);
                    if ((arr1[2] + arr2[2])*(arr1[2] + arr2[2]) >= Math.abs(arr1[0] - arr2[0])*Math.abs(arr1[0] - arr2[0]) + Math.abs(arr1[1] - arr2[1])*Math.abs(arr1[1] - arr2[1])) {
                        if (find(i) != find(j)) {
                            result--;
                            union(i, j);
                        }
                    }
                }
            }
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}