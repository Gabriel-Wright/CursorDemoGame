package ui;

import tasks.Task;
import tasks.TaskRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UITagManager {

    private static ArrayList<UITagTask> UITagMessages;

    public UITagManager() {
        UITagMessages = new ArrayList<>();
    }

    public static void addUITag(UITagTask uiTag) {
        for(UITagTask tag: UITagMessages) {
            tag.updateDestY();
        }
        UITagMessages.add(uiTag);
        TaskRunner.addTask(uiTag);
    }

    public static void removeUITag(UITagTask uiTag) {
        UITagMessages.remove(uiTag);
    }

    public void drawUITags(Graphics g, Font font) {
        if(UITagMessages.isEmpty()) {
            return;
        }

        for(UITagTask uiTag: UITagMessages) {
            uiTag.drawTag(g, font);
        }
    }

}
