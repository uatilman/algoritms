package ht;


public class MainGraph {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex('a'); //0 -> 1 ; 0 -> 2
        g.addVertex('b'); //1 -> 3 ; 1 -> 4
        g.addVertex('c'); //2 -> 0
        g.addVertex('d'); //3 -> 0 ; 3 ->1
        g.addVertex('e'); //4

        g.addVertex('f'); //5
        g.addVertex('g'); //6
        g.addVertex('h'); //7
        g.addVertex('k'); //8
        g.addVertex('l'); //9



        g.addEdge(0, 1,5);//0->1->3 5+15=20
        g.addEdge(0, 2,10);
        g.addEdge(1, 3,15);
        g.addEdge(1, 4,20);
        g.addEdge(3, 0,25);

        g.addEdge(4, 5,5);
        g.addEdge(5, 6,6);
        g.addEdge(6, 7,7);
        g.addEdge(6, 8,8);
        g.addEdge(7, 9,9);

        g.depthTravers();
        System.out.println("--------------");
       g.widthTravers();

        System.out.println("Minimum length to finish "
                + g.widthTraversFindMinLength('l'));
    }
}
