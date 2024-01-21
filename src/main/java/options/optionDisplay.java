package options;

import gameObjects.entities.player.GameCursor;
import options.sound.SoundSettings;

public class optionDisplay {

    private SoundSettings soundSettings;
    private GameCursor optionCursor;
    public optionDisplay(SoundSettings soundSettings, GameCursor optionCursor) {
        this.soundSettings = soundSettings;
        this.optionCursor = optionCursor;
    }


}
