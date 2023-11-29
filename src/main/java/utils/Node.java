package utils;

public class Node {

    //used for A* search algorithm
    Node parent;
    public int col;
    public int row;
    int gCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row, boolean solid) {
        this.col = col;
        this.row = row;
        this.solid = solid;
    }
}
