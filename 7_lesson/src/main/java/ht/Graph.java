package ht;


public class Graph {
    private static final int MAX_VERTS = 10;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;

    Graph() {
        vertices = new Vertex[MAX_VERTS];
        adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        this.size = 0;
    }

    // 20:35
    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }


    public void addEdge(int start, int end, int length) {
        adjMatrix[start][end] = length;
        adjMatrix[end][start] = length;
    }

    public void showVertex(int vertex) {
        System.out.println(vertices[vertex]);
    }

    private int getUncheckedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] == 1 &&
                    !vertices[i].wasVisited)
                return i;
        }
        return -1;
    }


    public void depthTravers() {
        Stack stack = new Stack(MAX_VERTS);
        vertices[0].wasVisited = true;
        showVertex(0);
        stack.push((char) 0);
        while (!stack.isEmpty()) {
            int v = getUncheckedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertices[v].wasVisited = true;
                showVertex(v);
                stack.push((char) v);
            }
        }
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void widthTraversFind(char value) {
        if (vertices[0].equals(value)) {

        }

        Queue queue = new Queue(MAX_VERTS);
        vertices[0].wasVisited = true;
        showVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            int v2;
            while ((v2 = getUncheckedVertex(v1)) != -1) {
                vertices[v2].wasVisited = true;
                showVertex(v2);
                queue.insert(v2);
            }
        }

        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void widthTravers() {
        Queue queue = new Queue(MAX_VERTS);
        vertices[0].wasVisited = true;
        showVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            int v2;
            while ((v2 = getUncheckedVertex(v1)) != -1) {
                vertices[v2].wasVisited = true;
                showVertex(v2);
                queue.insert(v2);
            }
        }

        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }
// **** Реализовать программу,
// в которой задается граф из 10 вершин.
// Задать ребра и попробовать найти
// минимальный кратчайший путь с
// помощью поиска в ширину.
}
