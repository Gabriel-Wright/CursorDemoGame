package events;

import entities.player.Player;
import levels.Level;
import object.CollectableObject;
import sound.SoundConstants;
import sound.SoundManager;

public class CollectObjectEvent implements Event{

    private SoundManager collectObjectSounds;
    private CollectableObject object;
    public CollectObjectEvent(CollectableObject object) {
        this.object = object;
//        collectObjectSounds = new SoundManager(SoundConstants.getSoundFilePath(object.getName()));
    };

//    public void load
    @Override
    public void runEvent() {

    }

    public void runEvent(Player player, Level level) {

    }
}
