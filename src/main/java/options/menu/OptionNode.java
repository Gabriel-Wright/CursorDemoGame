package options.menu;

import options.sound.SoundSettings;

import java.awt.*;

import static inputs.MouseHandler.mouseClicked;
import static options.menu.MenuListRenderer.optionClicked;

//Same as MenuNode but has some adjustable things
public class OptionNode extends MenuNode {

    private SoundSettings soundSettings;
    private Rectangle sliderWidthBox;
    private Rectangle sliderHeightBox;
    private boolean withinZone;

    public OptionNode(String label, SoundSettings soundSettings) {
        super(label);
        this.soundSettings = soundSettings;
    }

    @Override
    public void setTriggerBox(Rectangle triggerBox) {
        this.triggerBox = triggerBox;
        sliderWidthBox = new Rectangle(triggerBox.x+triggerBox.width/4, triggerBox.y+triggerBox.height/2, triggerBox.width/2, triggerBox.height/8);
        float volume = soundSettings.getVolume();
        System.out.println("Initial volume" + volume);
        sliderHeightBox = new Rectangle((int) (sliderWidthBox.x - sliderWidthBox.x/16 + volume * (float) sliderWidthBox.width),
                sliderWidthBox.y-(sliderWidthBox.height)/2,
                sliderWidthBox.width/8,
                sliderWidthBox.height*2);
        System.out.println("SliderWidthBox.x:" + sliderWidthBox.x);
        System.out.println("SliderHeightBox.x:"+sliderHeightBox.x);
    }

    @Override
    public void renderNode(Graphics g) {
        int volume = (int) (soundSettings.getVolume()*100);
        if(withinZone) {
            g.setColor(Color.GREEN);
            g.fillRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
            g.setColor(Color.WHITE);
            g.drawString("Volume:" + volume , triggerBox.x, triggerBox.y+triggerBox.height);
        } else {
            g.drawRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
            g.drawString("Volume:" + volume , triggerBox.x, triggerBox.y+triggerBox.height);
        }
        g.fillRect(sliderWidthBox.x, sliderWidthBox.y, sliderWidthBox.width, sliderWidthBox.height);
        g.setColor(Color.BLUE);
        g.fillRect(sliderHeightBox.x, sliderHeightBox.y, sliderHeightBox.width, sliderHeightBox.height);
    }

    public void checkCursorCollision(int mouseX, int mouseY) {
        if (sliderWidthBox.contains(mouseX, mouseY)) {
            if (mouseClicked && !optionClicked) {
                // Calculate the percentage of cursor position relative to the slider width
                float percentage = (float) (mouseX - sliderWidthBox.x) / (float) sliderWidthBox.width;

                // Ensure the percentage is within the valid range [0.0, 1.0]
                percentage = Math.max(0.0f, Math.min(1.0f, percentage));

                System.out.println("Percentage: " + percentage);

                // Set the volume based on the percentage
                soundSettings.setVolume(percentage);

                // Update the slider position based on the new percentage
                updateSliderPos(percentage);

                optionClicked = true;
            }
        }
        withinZone = triggerBox.contains(mouseX,mouseY);
    }

    private void updateSliderPos(float percentage) {
        sliderHeightBox.x = (int) (sliderWidthBox.x - sliderWidthBox.x/16 + percentage * (float) sliderWidthBox.width);
    }
}
