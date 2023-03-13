import java.io.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Node Root;

    public static void main(String[] args) throws IOException {
        String temp;
        Root = null;
        while ((temp = br.readLine()) != null && !temp.isEmpty()) {
            int value = Integer.parseInt(temp);
            if (Root == null) {
                Root = new Node(value);
            } else {
                Node currentNode = Root;
                Node parent;
                while (true) {
                    parent = currentNode;
                    if (value < currentNode.value) {
                        currentNode = currentNode.left;
                        if (currentNode == null) {
                            parent.left = new Node(value);
                            break;
                        }
                    } else {
                        currentNode = currentNode.right;
                        if (currentNode == null) {
                            parent.right = new Node(value);
                            break;
                        }
                    }
                }
            }
        }
        printTree(Root);
        bw.flush();
        bw.close();
    }

    private static void printTree(Node node) throws IOException {
        if (node != null) {
            printTree(node.left);
            printTree(node.right);
            bw.write(node.value + "\n");
        }
    }

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

}
