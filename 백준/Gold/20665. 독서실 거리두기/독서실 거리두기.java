import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int Result = 720;
    static int[][] Deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int t = Integer.parseInt(temp[1]);
        int p = Integer.parseInt(temp[2]);
        int[] arr = new int[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Node> res = new ArrayList<>();
        for (int i = 1; i <= t; i++) {
            temp = br.readLine().split(" ");
            char[] start = temp[0].toCharArray();
            char[] end = temp[1].toCharArray();
            int tempStart, tempEnd;
            tempStart = ((start[0] - '0') * 10 + (start[1] - '0')) * 60 + (start[2] - '0') * 10 + (start[3] - '0');
            tempEnd = ((end[0] - '0') * 10 + (end[1] - '0')) * 60 + (end[2] - '0') * 10 + (end[3] - '0');
            pq.add(new Node(i, tempStart, tempEnd));
            res.add(new Node(i, tempStart, tempEnd));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            //들어갈 수 있는 자리 찾기
            int idx = findSeat(node, arr, res);
            arr[idx] = node.idx;
            if (idx == p) {
                Result -= (node.end - node.start);
            }
        }

        bw.write(Result + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static int findSeat(Node node, int[] arr, List<Node> res) {
        //현재 노드의 시작 시간에 맞게 자리 현황을 최신화한다.
        int len = arr.length - 1;
        for (int i = 1; i <= len; i++) {
            if (arr[i] != 0 && res.get(arr[i] - 1).end <= node.start) {
                arr[i] = 0;
            }
        }
        //가장 가까운 노드가 가장 큰 자리 찾기
        int max = 0;
        int maxIdx = 0;
        for (int i = 1; i <= len; i++) {
            if (arr[i] != 0)
                continue;
            if (i == 1) {
                int cnt = 1;
                for (int j = 2; j <= len; j++) {
                    if (arr[j] != 0) {
                        break;
                    }
                    cnt++;
                }
                if (max < cnt) {
                    max = cnt;
                    maxIdx = i;
                }
            } else if (i == len) {
                int cnt = 1;
                for (int j = len - 1; j >= 1; j--) {
                    if (arr[j] != 0)
                        break;
                    cnt++;
                }
                if (max < cnt) {
                    max = cnt;
                    maxIdx = i;
                }
            } else {
                int cnt1 = 1;
                for (int j = i + 1; j <= len; j++) {
                    if (arr[j] != 0) {
                        break;
                    }
                    cnt1++;
                }

                int cnt2 = 1;
                for (int j = i - 1; j >= 1; j--) {
                    if (arr[j] != 0) {
                        break;
                    }
                    cnt2++;
                }
                if (max < Math.min(cnt1, cnt2)) {
                    max = Math.min(cnt1, cnt2);
                    maxIdx = i;
                }
            }
        }
        return maxIdx;
    }

    static class Node implements Comparable<Node> {
        int idx, start, end;

        public Node(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }

        public int compareTo(Node o) {
            return this.start - o.start == 0 ? this.end - o.end : this.start - o.start;
        }

    }

}