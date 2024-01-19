package options.menu;

import java.awt.*;
import java.util.ArrayList;

import static main.GamePanel.TILE_SIZE;

public class MenuNode {

    //Name of the node
    private String label;
    private MenuNode parent;
    private ArrayList<MenuNode> children;
    private Rectangle triggerBox;
    public MenuNode(String label) {
        this.label = label;
        children = new ArrayList<>();
    }

    public void setParent(MenuNode parentNode) {
        this.parent = parentNode;
    }

    public void setTriggerBox(Rectangle triggerBox) {
        this.triggerBox = triggerBox;
    }

    public void addChildNode(MenuNode child) {
        children.add(child);
        child.setParent(this);
    }

    public String getLabel() {
        return label;
    }

    public MenuNode getParent() {
        return parent;
    }

    public ArrayList<MenuNode> getChildren() {
        return children;
    }

    public void renderNode(Graphics g) {
        g.drawString(label,triggerBox.x, triggerBox.y+triggerBox.height);
        g.drawRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);

    }
}
