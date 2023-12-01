package utils;

import java.awt.*;

public class Node {

    //used for A* search algorithm
    Node parent;
    Node childAgroPath;
    public int col;
    public int row;
    public Point point;
    int gCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;
    boolean addedToCell;

    public Node(int col, int row, boolean solid) {
        this.col = col;
        this.row = row;
        this.solid = solid;
        point = new Point(row,col);
    }

    public boolean getAddedToCell() {
        return addedToCell;
    }
    public void setAddedToCell() {
        addedToCell = true;
    }

    public void setChildAgroPath(Node childAgroPath) {
        this.childAgroPath = childAgroPath;
    }

    public Node getChildAgroPath() {
        return childAgroPath;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setUnopen(){
        this.open = false;
    }

    public void setUnchecked() {
        this.checked = false;
    }
}
