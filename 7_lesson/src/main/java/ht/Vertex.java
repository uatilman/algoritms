package ht;

public class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }

    public char getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex: " + label;
    }
}
