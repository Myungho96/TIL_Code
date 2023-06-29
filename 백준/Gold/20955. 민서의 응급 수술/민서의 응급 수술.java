import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static int[] Parents;
    public static HashSet<Integer> Sets = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        int cnt = 0;
        make();
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            if (!union(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])))
                cnt++;
        }

        for (int i = 1; i <= N; i++){
//            if(!Sets.contains(Parents[i])){
//                Sets.add(i);
//            }
            if(i==find(i)) cnt++;
        }
        bw.write( - 1 + cnt+"\n");
//        bw.write( - 1 + cnt  + Sets.size()+"\n");
        bw.flush();
        bw.close();
    }

    public static void make() {
        Parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Parents[i] = i;
        }
    }

    public static int find(int num) {
        if (num == Parents[num])
            return num;
        return Parents[num] = find(Parents[num]);
    }

    public static boolean union(int num1, int num2) {
        int temp1 = find(num1);
        int temp2 = find(num2);
        if (temp1 == temp2)
            return false;
        else if (find(temp1) <= find(temp2)) {
            Parents[temp2] = temp1;
        } else {
            Parents[temp1] = temp2;
        }

        return true;
    }


}

