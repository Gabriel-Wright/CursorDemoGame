package options.menu.nodes;

import options.menu.execute.Executes;

import java.awt.*;
import java.util.ArrayList;

public class MenuNode {

    //Name of the node
    private String label;
    private MenuNode parent;
    private ArrayList<MenuNode> children;
    protected Rectangle triggerBox;
    private boolean focused = false;

    private Executes executeChoice;

    public MenuNode(String label) {
        this.label = label;
        children = new ArrayList<>();
    }

    public void setExecuteChoice(Executes executeChoice) {
        this.executeChoice = executeChoice;
    }

    public void executeChoice() {
        executeChoice.executeChoice();
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
        if(isFocused()) {
            g.setColor(Color.GREEN);
            g.fillRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
        }

        g.setColor(Color.WHITE);
        g.drawString(label,triggerBox.x, triggerBox.y+triggerBox.height);
        g.drawRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
    }

    public Rectangle getTriggerBox() {
        return triggerBox;
    }

    public void checkCursorCollision(int mouseX, int mouseY) {
        if(triggerBox.contains(mouseX, mouseY)) {
            setFocused();
        } else {
            setUnfocused();
        }

    }

    public void setUnfocused() {
        focused = false;
    }

    public void setFocused() {
        focused = true;
    }

    public boolean isFocused() {
        return focused;
    }

    public boolean isExecutable() {
        return executeChoice != null;
    }
}
