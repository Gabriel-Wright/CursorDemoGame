package gameObjects.entities;

import object.CollectableObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String,Integer> collectables;

    public Inventory() {
        collectables = new HashMap<>();
    }

    public void addCollectable(CollectableObject collectableObject) {
        String collectableName = collectableObject.getObjectName();

        // Check if the collectable name is already in the map
        if (collectables.containsKey(collectableName)) {
            // Get the current value and increment it by 1
            int currentValue = collectables.get(collectableName);
            collectables.put(collectableName, currentValue + 1);
        } else {
            // Add the collectable name to the map with an initial value of 1
            collectables.put(collectableName, 1);
        }
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (Map.Entry<String, Integer> entry : collectables.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append(", ");
        }

        // Remove the trailing ", " if there are entries
        if (!collectables.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

}
