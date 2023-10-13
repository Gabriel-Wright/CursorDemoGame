package tasks.backgroundColorTasks;

import main.GamePanel;
import states.GameState;
import tasks.Task;

import java.awt.*;

public class BackGroundColorChange extends Task {

    private Color newColor;
    private Color oldColor;
    private Color lerpedColor;

    public BackGroundColorChange(int maxTick, Color oldColor, Color newColor) {
        super(maxTick);
        this.newColor = newColor;
        this.oldColor = oldColor;
    }

    @Override
    public void runTask() {
        float progressRatio = (float) (getTick()) / getMaxTick();

        //Interpolate values of rgb, based on ratio
        int r = (int) (oldColor.getRed() * (1 - progressRatio) + newColor.getRed() * progressRatio);
        int g = (int) (oldColor.getGreen() * (1-progressRatio) + newColor.getGreen() * progressRatio);
        int b = (int) (oldColor.getBlue() * (1-progressRatio) + newColor.getBlue() * progressRatio);

        // Create the lerped color
        lerpedColor = new Color(r, g, b);

        GameState.updateGameBackground(lerpedColor);
    }
}
