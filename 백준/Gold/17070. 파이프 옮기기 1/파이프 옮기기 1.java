import java.io.*;
import java.util.*;
import java.lang.*;
 
public class Main {
    static int N;
    static int[][] arr;
    static int ans;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        ans = 0;
        DFS(1, 2, 0);
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 

    public static void DFS(int r, int c, int direction) {
        if (r == N && c == N) { // 종료 조건
            ans++;
            return;
        }else if(direction == 0) {//현재 좌표가 범위 안이고 방향이 가로라면
        	if(isIn(r,c+1) && arr[r][c+1]==0) {
        		DFS(r, c+1, 0);
        	}
        	if(isIn(r,c+1)&&isIn(r+1,c) &&isIn(r+1,c+1) &&arr[r][c+1]==0 && arr[r+1][c+1]==0 && arr[r+1][c]==0) {
        		DFS(r+1, c+1, 2);
        	}
        	
        }else if(direction == 1) {//현재 좌표가 범위 안이고 방향이 세로라면
        	if(isIn(r+1,c) && arr[r+1][c]==0) {
        		DFS(r+1, c, 1);
        	}
        	if(isIn(r,c+1)&&isIn(r+1,c) &&isIn(r+1,c+1) &&arr[r][c+1]==0 && arr[r+1][c+1]==0 && arr[r+1][c]==0) {
        		DFS(r+1, c+1, 2);
        	}
        }else if(direction == 2) {//현재 좌표가 범위 안이고 방향이 대각선이라면
        	if(isIn(r,c+1) && arr[r][c+1]==0) {
        		DFS(r, c+1, 0);
        	}
        	if(isIn(r+1,c) &&arr[r+1][c]==0) {
        		DFS(r+1, c, 1);
        	}
        	
        	if(isIn(r,c+1)&&isIn(r+1,c) &&isIn(r+1,c+1) &&arr[r][c+1]==0 && arr[r+1][c+1]==0 && arr[r+1][c]==0) {
        		DFS(r+1, c+1, 2);
        	}
        }
        
    }
    public static boolean isIn(int r, int c) {
    	return r>0 && c>0 && r<=N && c<=N;
    }
 
}