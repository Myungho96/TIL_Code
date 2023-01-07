import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// 입력 받기 위해 선언
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));// 출력하기위해 선언
    static boolean[] visited;//열 방문 체크 배열
    static String[][] arr;//전체 배열
    static int R, C, N, T;//입력받는 변수들
    static Stack<int[]> []stack;
    static StringTokenizer st;// 스트링 토크나이저

    public static void main(String[] args) throws IOException {
        String[] temp;//readLine할때 사용
        st = new StringTokenizer(br.readLine());// 토크나이저로 2개의 값을 스트링으로 받아와서
        R = Integer.parseInt(st.nextToken());// r값을 입력받는다
        C = Integer.parseInt(st.nextToken());// c값을 입력받는다
        arr = new String[R][C];//배열 생성
        visited = new boolean[C];//방문배열 생성
        stack = new Stack[C];
        for (int i = 0; i < C; i++) {
            stack[i] = new Stack<>();
        }
        for (int i = 0; i < R; i++) {//입력 담기
            temp = br.readLine().split("");//한글자씩담는다
            for (int j = 0; j < C; j++) {//한글자씩담는다
                arr[i][j] = temp[j];//한글자씩담는다
            }
        }
        N = Integer.parseInt(br.readLine());// n값을 입력받는다
        for (int i = 0; i < N; i++) {//화산탄 넘버만큼
            T = Integer.parseInt(br.readLine());//받아서
            dfs(0, T - 1);//dfs실행한다.

        }
        for (int i = 0; i < R; i++) {//출력
            for (int j = 0; j < C; j++) {//출력
                bw.write(arr[i][j]);//출력
            }
            bw.write("\n");//개행
        }
        bw.flush();//버퍼에 남은 것 출력
        bw.close();//bw를 닫는다.
    }

    public static void dfs(int r, int c) {
        if (!visited[c]) {// 이 줄에 아무것도 없다는 의미. 시뮬레이션을 수행한다.
            int nr = r;//nr 생성
            int nc = c;//nc 생성
            while (true) {
                stack[c].push(new int[]{nr,nc});
                if (isIn(nr + 1, nc) == false || arr[nr + 1][nc].equals("X")) {// 1. 밑에 장애물 만나거나, 범위를 넘어서면 그자리에 stop
                    visited[c] = true;//이제 이줄에는 뭔가 있음
                    arr[nr][nc] = "O";//화산재를 놓는다.
                    return;//리턴
                } else if (isIn(nr, nc) && arr[nr + 1][nc].equals("O")) {// 범위안에 있고 밑에 화산재 만났으면

                    if (isInAndBlank(nr, nc - 1) && isInAndBlank(nr + 1, nc - 1)) {// 왼쪽이랑 왼쪽아래 확인!
                    	nr++;
                    	nc--;
                        continue;
//                        dfs(nr, nc - 1);//재귀적으로 실행 후
//                        return;//리턴
                    } else if (isInAndBlank(nr, nc + 1) && isInAndBlank(nr + 1, nc + 1)) {// 오른쪽이랑 오른쪽 아래 확인
                    	nr++;
                    	nc++;
                        continue;
//                        dfs(nr, nc + 1);//재귀적으로 실행 후
//                        return;//리턴
                    }else {//왼쪽 오른쪽 다 갈 수 없으면 화산재 위에 멈춘다.
                        visited[c] = true;//이제 이줄에는 뭔가 있음
                        arr[nr][nc] = "O";//화산재를 놓는다.
                        return;//리턴
                    }
                } else {//다 아니면 한칸 내려가본다
                    nr++;//열 증가
                }
            }
        }
        else{//저장된경우
            int []node;
            while(true){
                node = stack[c].pop();
                if(arr[node[0]][node[1]].equals(".")){
                    break;
                }
            }
            int nr = node[0];//nr 생성
            int nc = node[1];//nc 생성
            //도착지점 직전 점부터 탐색하자.
            while (true) {
                stack[c].push(new int[]{nr,nc});
                if (isIn(nr + 1, nc) == false || arr[nr + 1][nc].equals("X")) {// 1. 밑에 장애물 만나거나, 범위를 넘어서면 그자리에 stop
                    visited[c] = true;//이제 이줄에는 뭔가 있음
                    arr[nr][nc] = "O";//화산재를 놓는다.
                    return;//리턴
                } else if (isIn(nr, nc) && arr[nr + 1][nc].equals("O")) {// 범위안에 있고 밑에 화산재 만났으면

                    if (isInAndBlank(nr, nc - 1) && isInAndBlank(nr + 1, nc - 1)) {// 왼쪽이랑 왼쪽아래 확인!
                        nr++;
                    	nc--;
                        continue;
//                        dfs(nr, nc - 1);//재귀적으로 실행 후
//                        return;//리턴
                    } else if (isInAndBlank(nr, nc + 1) && isInAndBlank(nr + 1, nc + 1)) {// 오른쪽이랑 오른쪽 아래 확인
                    	nr++;
                    	nc++;
                        continue;
//                        dfs(nr, nc + 1);//재귀적으로 실행 후
//                        return;//리턴
                    }else {//왼쪽 오른쪽 다 갈 수 없으면 화산재 위에 멈춘다.
                        visited[c] = true;//이제 이줄에는 뭔가 있음
                        arr[nr][nc] = "O";//화산재를 놓는다.
                        return;//리턴
                    }
                } else {//다 아니면 한칸 내려가본다
                    nr++;//열 증가
                }
            }
        }

    }

    public static boolean isIn(int r, int c) {//범위 체크함수
        return r >= 0 && c >= 0 && r < R && c < C;//범위안에 있으면 true반환
    }

    public static boolean isInAndBlank(int r, int c) {//범위 체크 하면서 빈공간도 체크
        return r >= 0 && c >= 0 && r < R && c < C && arr[r][c].equals(".");//범위안에 있고 빈공간이면 true반환
    }

}
