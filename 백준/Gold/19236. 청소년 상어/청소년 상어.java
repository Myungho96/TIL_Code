import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] deltas = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int result = 0;

    static class Fish implements Comparable<Fish> {
        int num, dir, i, j;
        boolean isAlive = true;

        public Fish(int num, int dir, int i, int j, boolean isAlive) {
            this.num = num;
            this.dir = dir;
            this.i = i;
            this.j = j;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.num, o.num);
        }
    }

    static class Shark {
        int i, j, dir, eatNum;

        Shark(int i, int j, int dir, int eatNum) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.eatNum = eatNum;
        }
    }


    public static void main(String[] args) throws IOException {

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(temp[2 * j]);
                fishes.add(new Fish(Integer.parseInt(temp[2 * j]), Integer.parseInt(temp[2 * j + 1]) - 1, i, j, true));
            }
        }
        Collections.sort(fishes);
        Fish fish = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, fish.dir, fish.num);
        fish.isAlive = false;
        arr[0][0] = -1;
        dfs(arr, shark, fishes);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        // 최댓값 비교
        if (result < shark.eatNum) {
            result = shark.eatNum;
        }

        // 물고기 이동
        for (Fish fish : fishes) {
            moveFish(fish, arr, fishes);
        }
        // 상어 이동
        for (int dist = 1; dist < 4; dist++) {
            int cr = shark.i + deltas[shark.dir][0] * dist;
            int cc = shark.j + deltas[shark.dir][1] * dist;

            if (0 <= cr && cr < 4 && 0 <= cc && cc < 4 && arr[cr][cc] > 0) {
                // 물고기 잡아먹고 dfs
                int[][] tempArr = new int[4][4];

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        tempArr[i][j] = arr[i][j];
                    }
                }

                List<Fish> tempFish = new ArrayList<>();
                for(Fish fish : fishes){
                    tempFish.add(new Fish(fish.num, fish.dir,fish.i, fish.j,  fish.isAlive));
                }
                //상어 이동
                tempArr[shark.i][shark.j] = 0;
                Fish fish = tempFish.get(arr[cr][cc] - 1);
                Shark newShark = new Shark(fish.i, fish.j, fish.dir, shark.eatNum + fish.num);
                fish.isAlive = false;
                tempArr[fish.i][fish.j] = -1;

                dfs(tempArr, newShark, tempFish);
            }
        }
    }

    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        //죽었으면 pass
        if (!fish.isAlive)
            return;

        for (int i = 0; i < 8; i++) {
            int currentDir = (fish.dir + i) % 8;
            int cr = fish.i + deltas[currentDir][0];
            int cc = fish.j + deltas[currentDir][1];
            //상어가 없고 범위 안이면
            if (0 <= cr && cr < 4 && 0 <= cc && cc < 4 && arr[cr][cc] > -1) {
                //기존 위치 빈칸
                arr[fish.i][fish.j] = 0;
                //갈 위치가 빈칸이면
                if (arr[cr][cc] == 0) {
                    fish.i = cr;
                    fish.j = cc;
                } else {
                    Fish temp = fishes.get(arr[cr][cc] - 1);
                    temp.i = fish.i;
                    temp.j = fish.j;
                    arr[fish.i][fish.j] = temp.num;

                    fish.i = cr;
                    fish.j = cc;
                }

                arr[cr][cc] = fish.num;
                fish.dir = currentDir;
                return;
            }
        }
    }
}
