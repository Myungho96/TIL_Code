import java.io.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static String[] temp;
    static String[][] tree;
    public static Map<String, Integer> Alphabet = new HashMap<String, Integer>() {{
        put("A", 0);
        put("B", 1);
        put("C", 2);
        put("D", 3);
        put("E", 4);
        put("F", 5);
        put("G", 6);
        put("H", 7);
        put("I", 8);
        put("J", 9);
        put("K", 10);
        put("L", 11);
        put("M", 12);
        put("N", 13);
        put("O", 14);
        put("P", 15);
        put("Q", 16);
        put("R", 17);
        put("S", 18);
        put("T", 19);
        put("U", 20);
        put("V", 21);
        put("W", 22);
        put("X", 23);
        put("Y", 24);
        put("Z", 25);
    }};

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        tree = new String[N][3];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            tree[Alphabet.get(temp[0])][0] = temp[0];
            tree[Alphabet.get(temp[0])][1] = temp[1];
            tree[Alphabet.get(temp[0])][2] = temp[2];
        }
        preOrder(0);
        bw.write("\n");
        inOrder(0);
        bw.write("\n");
        postOrder(0);
        bw.write("\n");

        bw.flush();
        bw.close();
    }

    private static void postOrder(int i) throws IOException {
        if (!tree[i][1].equals("."))
            postOrder(Alphabet.get(tree[i][1]));
        if (!tree[i][2].equals("."))
            postOrder(Alphabet.get(tree[i][2]));
        bw.write(tree[i][0]);
    }

    private static void inOrder(int i) throws IOException {
        if (!tree[i][1].equals("."))
            inOrder(Alphabet.get(tree[i][1]));
        bw.write(tree[i][0]);
        if (!tree[i][2].equals("."))
            inOrder(Alphabet.get(tree[i][2]));
    }

    private static void preOrder(int i) throws IOException {
        bw.write(tree[i][0]);
        if (!tree[i][1].equals("."))
            preOrder(Alphabet.get(tree[i][1]));
        if (!tree[i][2].equals("."))
            preOrder(Alphabet.get(tree[i][2]));
    }
}