package options.sound;

import options.Settings;

public class SoundSettings {

    private Settings settings;
    private SoundConstants soundConstants;
    private final String volumeSetting = "Volume";

    public SoundSettings(Settings settings) {
        this.settings = settings;
    }

    public void loadSoundConstants() {
        soundConstants = new SoundConstants();
        soundConstants.loadAudioClips();
    }

    public void setVolume(float volume) {
        settings.addSetting(volumeSetting, volume);
        adjustSoundConstants();
    }

    public float getVolume() {
        return settings.getSettingFloat(volumeSetting);
    }

    public void adjustSoundConstants() {
        soundConstants.adjustVolume(getVolume());
    }

    public void incrementVolumeUP(float percentage) {
        float currentVolume = getVolume();
        float newVolume = Math.min(currentVolume + percentage, 1.0f);
        setVolume(newVolume);
        System.out.println("Volume at: " + newVolume);
    }

    public void incrementVolumeDOWN(float percentage) {
        float currentVolume = getVolume();
        float newVolume = Math.max(currentVolume - percentage, 0.0f);
        setVolume(newVolume);
        System.out.println("Volume at: " + newVolume);
    }



}
