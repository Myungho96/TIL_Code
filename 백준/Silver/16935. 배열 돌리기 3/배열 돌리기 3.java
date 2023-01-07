import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static List<Integer[]> list = new LinkedList<>();
    public static List<Integer[]> tempList;
    public static int N, M, T, rot = 0;

    public static List<Integer[]> group1;
    public static List<Integer[]> group2;
    public static List<Integer[]> group3;
    public static List<Integer[]> group4;

    public static void main(String[] args) throws IOException {
        String[] tempString = br.readLine().split(" ");
        N = Integer.parseInt(tempString[0]);
        M = Integer.parseInt(tempString[1]);
        T = Integer.parseInt(tempString[2]);
        for (int i = 0; i < N; i++) {

        }
        for (int i = 0; i < N; i++) {
            tempString = br.readLine().split(" ");
            Integer[] tempInt = new Integer[M];
            for (int j = 0; j < M; j++) {
                tempInt[j] = Integer.parseInt(tempString[j]);

            }
            list.add(tempInt);
        }
        tempString = br.readLine().split(" ");
        for (int i = 0; i < T; i++) {
            int order = Integer.parseInt(tempString[i]);
            Rotation(order);
        }


        //test
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).length; j++) {
                bw.write(list.get(i)[j] + " ");
            }
            bw.write("\n");
        }


        bw.flush();
        bw.close();
    }

    public static void Rotation(int order) {
        switch (order) {
            case 1:
                Collections.reverse(list);
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    List<Integer> temp = Arrays.asList(list.get(i));
                    Collections.reverse(temp);
                    list.set(i, temp.toArray(list.get(i)));
                }
                break;
            case 3:
                tempList = new LinkedList<>();
                for (int j = 0; j < list.get(0).length; j++) {
                    Integer[] tempInt = new Integer[list.size()];
                    for (int i = list.size() - 1; i >= 0; i--) {
                        tempInt[list.size() - 1 - i] = list.get(i)[j];
                    }
                    tempList.add(tempInt);
                }
                list = tempList;
                break;
            case 4:
                tempList = new LinkedList<>();
                for (int j = list.get(0).length - 1; j >= 0; j--) {
                    Integer[] tempInt = new Integer[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        tempInt[i] = list.get(i)[j];
                    }
                    tempList.add(tempInt);
                }
                list = tempList;
                break;
            case 5:
                group1 = new LinkedList<>();
                group2 = new LinkedList<>();
                group3 = new LinkedList<>();
                group4 = new LinkedList<>();
                tempList = new LinkedList<>();
                for (int i = 0; i < list.size(); i++) {//N
                    Integer[] temp1 = new Integer[list.get(0).length / 2];
                    Integer[] temp2 = new Integer[list.get(0).length / 2];
                    Integer[] temp3 = new Integer[list.get(0).length / 2];
                    Integer[] temp4 = new Integer[list.get(0).length / 2];
                    for (int j = 0; j < list.get(0).length; j++) {
                        if (i < list.size() / 2 && j < list.get(0).length / 2) {//1번그룹
                            temp1[j] = list.get(i)[j];
                        } else if (i < list.size() / 2 && j >= list.get(0).length / 2) {//1번그룹
                            temp2[j - list.get(0).length / 2] = list.get(i)[j];
                        } else if (i >= list.size() / 2 && j >= list.get(0).length / 2) {//3번그룹
                            temp3[j - list.get(0).length / 2] = list.get(i)[j];
                        }else if (i >= list.size() / 2 && j < list.get(0).length / 2) {//4번그룹
                            temp4[j] = list.get(i)[j];
                        }
                    }
                    if(temp1[0]!=null){
                        group1.add(temp1);
                    }
                    if(temp2[0]!=null){
                        group2.add(temp2);
                    }
                    if(temp3[0]!=null){
                        group3.add(temp3);
                    }
                    if(temp4[0]!=null){
                        group4.add(temp4);
                    }

                }

                for (int i = 0; i < list.size(); i++) {
                    Integer [] arr1 = new Integer[list.get(0).length];
                    for (int j = 0; j < list.get(0).length; j++) {
                        if (i < list.size() / 2 && j < list.get(0).length / 2) {//4번그룹
                            arr1[j] = group4.get(i)[j];
                        } else if (i < list.size() / 2 && j >= list.get(0).length / 2) {//1번그룹
                            arr1[j] = group1.get(i)[j-list.get(0).length/2];
                        } else if (i >= list.size() / 2 && j >= list.get(0).length / 2) {//2번그룹
                            arr1[j] = group2.get(i-list.size()/2)[j-list.get(0).length/2];
                        }else if (i >= list.size() / 2 && j < list.get(0).length / 2) {//3번그룹
                            arr1[j] = group3.get(i-list.size()/2)[j];
                        }
                    }
                    tempList.add(arr1);

                }
                list = tempList;
                break;
            case 6:
                group1 = new LinkedList<>();
                group2 = new LinkedList<>();
                group3 = new LinkedList<>();
                group4 = new LinkedList<>();
                tempList = new LinkedList<>();
                for (int i = 0; i < list.size(); i++) {
                    Integer[] temp1 = new Integer[list.get(0).length / 2];
                    Integer[] temp2 = new Integer[list.get(0).length / 2];
                    Integer[] temp3 = new Integer[list.get(0).length / 2];
                    Integer[] temp4 = new Integer[list.get(0).length / 2];
                    for (int j = 0; j < list.get(0).length; j++) {
                        if (i < list.size() / 2 && j < list.get(0).length / 2) {//1번그룹
                            temp1[j] = list.get(i)[j];
                        } else if (i < list.size() / 2 && j >= list.get(0).length / 2) {//1번그룹
                            temp2[j - list.get(0).length / 2] = list.get(i)[j];
                        } else if (i >= list.size() / 2 && j >= list.get(0).length / 2) {//3번그룹
                            temp3[j - list.get(0).length / 2] = list.get(i)[j];
                        }else if (i >= list.size() / 2 && j < list.get(0).length / 2) {//4번그룹
                            temp4[j] = list.get(i)[j];
                        }
                    }
                    if(temp1[0]!=null){
                        group1.add(temp1);
                    }
                    if(temp2[0]!=null){
                        group2.add(temp2);
                    }
                    if(temp3[0]!=null){
                        group3.add(temp3);
                    }
                    if(temp4[0]!=null){
                        group4.add(temp4);
                    }

                }

                for (int i = 0; i < list.size(); i++) {
                    Integer [] arr1 = new Integer[list.get(0).length];
                    for (int j = 0; j < list.get(0).length; j++) {
                        if (i < list.size() / 2 && j < list.get(0).length / 2) {//4번그룹
                            arr1[j] = group2.get(i)[j];
                        } else if (i < list.size() / 2 && j >= list.get(0).length / 2) {//1번그룹
                            arr1[j] = group3.get(i)[j-list.get(0).length/2];
                        } else if (i >= list.size() / 2 && j >= list.get(0).length / 2) {//2번그룹
                            arr1[j] = group4.get(i-list.size()/2)[j-list.get(0).length/2];
                        }else if (i >= list.size() / 2 && j < list.get(0).length / 2) {//3번그룹
                            arr1[j] = group1.get(i-list.size()/2)[j];
                        }
                    }
                    tempList.add(arr1);

                }
                list = tempList;
                break;


        }


//
    }


}
