package tasks.visualTasks.backgroundColorTasks;

import main.GamePanel;
import org.apache.commons.collections.set.PredicatedSet;
import states.GameState;
import tasks.Task;

import java.awt.*;

public class TimedExplosion extends Task{

    private Color[] newColors;
    private Color oldColor;
    private Color newColor;
    private Color lerpedColor;
    private int transitionTick=0;
    private int transitionRate;
    private int explosionRate;
    private int colorIndex;
    public TimedExplosion(int explosionRate, int transitionRate, Color oldColor, Color[] newColors) {
        this.explosionRate = explosionRate;
        this.transitionRate = transitionRate;
        this.oldColor = oldColor;
        this.newColors = newColors;
        newColor = newColors[0];
    }

    @Override
    public void runTask() {

        float progressRatio = (float) (transitionTick) / transitionRate;

        //Interpolate values of rgb, based on ratio
        int r = (int) (oldColor.getRed() * (1 - progressRatio) + newColor.getRed() * progressRatio);
        int g = (int) (oldColor.getGreen() * (1-progressRatio) + newColor.getGreen() * progressRatio);
        int b = (int) (oldColor.getBlue() * (1-progressRatio) + newColor.getBlue() * progressRatio);

        // Create the lerped color
        lerpedColor = new Color(r, g, b);

        reassignColors();
        GameState.updateGameBackground(lerpedColor);
        transitionTick++;
        checkComplete();
    }


    public void checkComplete() {
        if(tick == explosionRate) {
            complete = true;
//            System.out.println("GAME OVER");
            GamePanel.gameOver();
        }
    }

    private void reassignColors() {
        if(transitionTick%transitionRate==0) {
            transitionTick = 0;
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

    public void reset() {
        tick = 0;
        this.transitionTick = 0;
        complete = false;

    }
}