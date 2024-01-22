package options.menu;

import options.menu.nodes.MenuNode;

import java.awt.*;

public class MenuNodeRenderer {

    private MenuNode displayedNode;

    public MenuNodeRenderer(MenuNode displayedNode) {
        this.displayedNode = displayedNode;
    }

    public void renderNode(Graphics g) {
        displayedNode.renderNode(g);
    }
}
