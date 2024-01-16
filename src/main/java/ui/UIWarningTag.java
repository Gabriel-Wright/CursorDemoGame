package ui;

import tasks.Task;

import java.awt.*;

import static main.GamePanel.UPS;

public class UIWarningTag extends Task {

    String warning;
    private int x;
    private int y;
    private Color warningColor;

    private final int flickerRate = UPS/2;
    private final int maxDisplayTime = UPS*3;
    private final int disappearStart = maxDisplayTime - flickerRate;

    public UIWarningTag(String warning) {
        this.warning = warning;
        warningColor = new Color(255,0,0);
    }

    @Override
    public void runTask() {
        updateColor();
        if(tick == maxDisplayTime) {
            setComplete();
            UIEntityInfo.removeWarning(this);
        }
    }

    private void updateColor() {
        if(tick > disappearStart) {
            int alphaValue = (tick - disappearStart)*255 / (maxDisplayTime - disappearStart);
            warningColor = new Color(255, 0, 0, alphaValue);
        }
    }

    public void drawWarning(Graphics g) {
        g.setColor(warningColor);
        g.drawString(warning, x, y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
