package tasks.taskQueue;

import gameObjects.events.generic.TimedBomb;
import gameObjects.handler.GameObjectHandler;
import main.GamePanel;
import tasks.Task;

import java.awt.*;
import java.util.Random;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnTimedBombTask extends Task {

    TimedBomb timedBombEvent;
    private int explosionRate = UPS*10;
    private int spawnIndex;
    private Color[] transitionColors;
    public SpawnTimedBombTask(Color[] transitionColors, Random bombSpawnLocation) {
        this.transitionColors = transitionColors;
        spawnIndex = bombSpawnLocation.nextInt(0,3);
    }
    public void initialiseTask() {
        randomiseBombSpawn();
        GameObjectHandler.eventQueue.add(timedBombEvent);
        timedBombEvent.initialEffects();
    }

    private void randomiseBombSpawn() {
        switch(spawnIndex) {
            case 0:
                timedBombEvent = new TimedBomb(transitionColors,TILE_SIZE * 46, TILE_SIZE * 16, TILE_SIZE * 2, TILE_SIZE * 2,this);
                break;
            case 1:
                timedBombEvent = new TimedBomb(transitionColors,TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2,this);
                break;
            case 2:
                timedBombEvent = new TimedBomb(transitionColors, TILE_SIZE * 45, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE * 2, this);
                break;
        }
    }

    @Override
    public void runTask() {
        if(tick%explosionRate==0&&tick!=0) {
//            GamePanel.gameOver();
            System.out.println("would be game over");
        }
    }

    @Override
    public void checkComplete() {

    }
}
