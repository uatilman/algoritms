
package ht;

//Иван, добрый день!

//Осилил только поиск длинны длинны графа при обходе (матод widthTraversFindMinLength(char value) ),
// однако он работает только если нет связей от многих родителей.
//Неуспеваю продумать связь от многих родителей, предполагая что нужно либо сбрасывать флаг просмотра когда пришли
// сигналы от всех родителей, или задать у узла число связей, и при каждом посещении снижать это число.
import java.util.Arrays;
import java.util.Vector;

public class Graph {
    private static final int MAX_VERTS = 10;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;
    private int length;




    Graph() {
        vertices = new Vertex[MAX_VERTS];
        adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        this.size = 0;
    }


    public int widthTraversFindMinLength(char value) {
        int length = Integer.MAX_VALUE;
        if (vertices[0].getLabel() == value) {
            return 0;
        }
        Queue<Vector<Integer>> queue = new Queue<>(MAX_VERTS);
        vertices[0].wasVisited = true;
        showVertex(0);

        Vector<Integer> newVector = new Vector<>(Arrays.asList(0, 0));
        queue.add(newVector);
        while (!queue.isEmpty()) {
            Vector<Integer> vector1 = queue.remove();
            int v1 = vector1.get(0);
            int v2;
            while ((v2 = getUncheckedVertex(v1)) != -1) {
                int newLength = vector1.get(1) + adjMatrix[v1][v2];
                if (vertices[v2].getLabel() == value) {
                    length = length < newLength ? length : newLength;
                    break;
                }
                vertices[v2].wasVisited = true;
                showVertex(v2);
                newVector = new Vector<>(Arrays.asList(v2, newLength));
                queue.add(newVector);
            }
        }

        clearVisitFlags();

        return length;
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        addEdge(start, end, 1);
    }


    public void addEdge(int start, int end, int length) {
        adjMatrix[start][end] = length;
        adjMatrix[end][start] = length;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void showVertex(int vertex) {
        System.out.println(vertices[vertex]);
    }

    private int getUncheckedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] > 0 &&
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
        clearVisitFlags();
    }



    private void clearVisitFlags() {
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void widthTravers() {
        Queue queue = new Queue(MAX_VERTS);
        vertices[0].wasVisited = true;
        showVertex(0);
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer v1 = (Integer) queue.remove();
            int v2;
            while ((v2 = getUncheckedVertex(v1)) != -1) {
                vertices[v2].wasVisited = true;
                showVertex(v2);
                queue.add(v2);
            }
        }

        clearVisitFlags();
    }
// **** Реализовать программу,
// в которой задается граф из 10 вершин.
// Задать ребра и попробовать найти
// минимальный кратчайший путь с
// помощью поиска в ширину.
}
