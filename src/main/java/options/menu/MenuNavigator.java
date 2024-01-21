package options.menu;

import java.util.ArrayList;

public class MenuNavigator {

    //Top point of the menu
    private MenuNode displayNode;
    private String title;

    public MenuNavigator(String title, MenuNode displayNode) {
        this.title = title;
        this.displayNode = displayNode;
    }

    public void pickOption(MenuNode menuNode) {
        this.displayNode = menuNode;
        this.title = displayNode.getLabel();
    }

    public void goBack() {
        MenuNode parentNode = displayNode.getParent();
        if(parentNode!=null) {
            this.displayNode = parentNode;
            this.title = displayNode.getLabel();
        }
    }

    public ArrayList<MenuNode> getCurrentMenuOptions() {
        return displayNode.getChildren();
    }

    public MenuNode getDisplayNode() {
        return displayNode;
    }

    public void setDisplayNode(MenuNode displayNode) {
        if(displayNode.getChildren().isEmpty()) {
            //If there are no children then execute command
        } else {
            //Otherwise continue
            this.displayNode = displayNode;
        }
    }
}
