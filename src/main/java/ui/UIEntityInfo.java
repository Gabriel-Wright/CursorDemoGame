package ui;

import tasks.TaskRunner;

import java.awt.*;
import java.util.ArrayList;

public class UIEntityInfo {

    public static ArrayList<UIWarningTag> warningSpawns;

    public UIEntityInfo() {
        warningSpawns = new ArrayList<>();
    }

    public static void addWarning(UIWarningTag warningTag) {
        warningSpawns.add(warningTag);
        TaskRunner.addTask(warningTag);
    }

    public static void removeWarning(UIWarningTag warningTag) {
        warningSpawns.remove(warningTag);
    }

    public void drawWarnings(Graphics g, Font font) {
        int newSize = font.getSize()*2;
        Font largerFont = font.deriveFont((float) newSize);
        g.setFont(largerFont);
        g.setColor(Color.RED);
        for (UIWarningTag warningTag: warningSpawns) {
            warningTag.drawWarning(g);
        }
    }
}
