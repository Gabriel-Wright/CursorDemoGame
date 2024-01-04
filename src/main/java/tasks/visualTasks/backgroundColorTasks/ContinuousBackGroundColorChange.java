package tasks.visualTasks.backgroundColorTasks;

import states.GameState;
import tasks.Task;

import java.awt.*;

public class ContinuousBackGroundColorChange extends Task{

    private Color[] newColors;
    private Color oldColor;
    private Color newColor;
    private Color lerpedColor;
    private int transitionRate;
    private int colorIndex;
    public ContinuousBackGroundColorChange(int transitionRate, Color oldColor, Color[] newColors) {
        this.transitionRate = transitionRate;
        this.oldColor = oldColor;
        this.newColors = newColors;
        newColor = newColors[0];
    }

    @Override
    public void runTask() {
        float progressRatio = (float) (tick) / transitionRate;

        //Interpolate values of rgb, based on ratio
        int r = (int) (oldColor.getRed() * (1 - progressRatio) + newColor.getRed() * progressRatio);
        int g = (int) (oldColor.getGreen() * (1-progressRatio) + newColor.getGreen() * progressRatio);
        int b = (int) (oldColor.getBlue() * (1-progressRatio) + newColor.getBlue() * progressRatio);

        // Create the lerped color
        lerpedColor = new Color(r, g, b);

        reassignColors();
        GameState.updateGameBackground(lerpedColor);

    }

    @Override
    public void updateTask() {
        runTask();
        tick++;
    }

    @Override
    public void checkComplete() {

    }

    private void reassignColors() {
        if(tick%transitionRate==0) {
            tick = 0;
            oldColor = newColor;
            indexSwitch();
            newColor = newColors[colorIndex];
        }
    }

    private void indexSwitch(){
        boolean colorIndexLessThanLength = colorIndex < newColors.length-1;
        if(colorIndexLessThanLength) {
            colorIndex++;
        } else {
            colorIndex = 0;
        }
    }
}