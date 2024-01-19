package options;

import java.util.prefs.Preferences;
public class Settings {

    //This is the preferences object that stores user settings
    private Preferences preferences;

    public Settings() {
        //This will create a node for your application
        preferences = Preferences.userRoot().node("com.2dRPG.gameSettings");
    }

    public void addSetting(String settingName, int settingValue) {
        preferences.putInt(settingName, settingValue);
    }

    public void addSetting(String settingName, float settingValue) {
        preferences.putFloat(settingName, settingValue);
    }

    public int getSettingInt(String settingName) {
        //Defaults to 0 if no setting found
        return preferences.getInt(settingName, 0);
    }

    public float getSettingFloat(String settingName) {
        return preferences.getFloat(settingName, 0.1f);
    }
    public void resetSettings() {
        // Obtain the user root node
        Preferences userRoot = Preferences.userRoot();
        // Clear all preferences
        try {
            userRoot.clear();
            System.out.println("Preferences cleared successfully.");
        } catch (Exception e) {
            System.err.println("Error clearing preferences: " + e.getMessage());
        }
    }


}
