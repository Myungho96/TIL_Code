import java.lang.*;
import java.io.*;
import java.util.*;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int totalCnt = 0;
        int[] arr = new int[M];
        temp = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
//        System.out.println(Arrays.toString(arr));
        //2중 포문으로 처음부터 끝까지 탐색하며 먼저 그 위치의 바로 옆이 비었는지를 체크하고, 체크하려는 높이가 첫번째보다 작은지를 체크하고(크면 물이 그냥 나가버림), 작다면 왼쪽으로 한칸씩 살펴본다.
        //만약 살펴본 위치가 마지막인데 체크하는 높이보다 작다면 break(조건 성립안함), 그게 아니면 벽을 만날때까지 체크해서 누적한 값을 고인물로 추가한다.
        //이후
        for (int i = 0; i < M-1; i++) {//마지막에서는 수행할 필요가 없음.
            for (int j = arr[i]; j > 0; j--) {
                if(j>arr[i+1] && i+1!=M-1){//앞의 벽보다 작고 마지막이 아니라면
                    int curCnt = 0;
                    int pos = i;
                    while(true){//쭉 가보면서 고이는 물 누적해보기
                        pos++;
                        if(pos==M-1 && j>arr[pos]){
                            break;
                        }else{
                            if(j>arr[pos]){
                                curCnt++;
                            }else{
                                totalCnt+=curCnt;
                                break;
                            }
                        }

                    }
                }
            }
        }
        bw.write(totalCnt+"\n");
        bw.flush();
        bw.close();
    }
}