import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [][]Deltas = {{0,1},{-1,0},{0,-1},{1,0}};
    public static void main(String args[]) throws IOException{
        int n = Integer.parseInt(br.readLine());
        int Result = 0;
        boolean [][]visited = new boolean[101][101];
        for(int i=0;i<n;i++){
            String []temp = br.readLine().split(" ");
            int r = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[0]);
            visited[r][c] = true;
            List<Integer> lists = getDir(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
            //방향 정리한 대로 이동하면서 체크해주기
            for(int d=0;d<lists.size();d++){
                r+=Deltas[lists.get(d)][0];
                c+=Deltas[lists.get(d)][1];
                visited[r][c] = true;
            }
        }
        
        for(int i=0;i<100;i++)
            for(int j=0;j<100;j++){
                if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
                    // System.out.println(i+" "+j);
                    Result++;
                }
                    
            }
        bw.write(Result+"\n");
        bw.flush();
        bw.close();
    }
    
    public static List<Integer> getDir(int d, int g) {
        List<Integer> lists = new ArrayList<>();
        lists.add(d);
 
        while (g-- > 0) {
            for (int i = lists.size() - 1; i >= 0; i--) {
                lists.add((lists.get(i) + 1) % 4);
            }
        }
        return lists;
    }

}