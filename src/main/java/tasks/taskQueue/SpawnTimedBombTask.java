package tasks.taskQueue;

import gameObjects.events.generic.CursorTimedBomb;
import gameObjects.events.generic.PlayerTimedBomb;
import gameObjects.handler.GameObjectHandler;
import main.GamePanel;
import tasks.Task;

import java.awt.*;
import java.util.Random;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnTimedBombTask extends Task {

    CursorTimedBomb cursorTimedBombEvent;
    PlayerTimedBomb playerTimedBombEvent;
    private int explosionRate = UPS*10;
    private int spawnIndex;
    private Color[] transitionColors;
    public SpawnTimedBombTask(Color[] transitionColors, Random bombSpawnLocation) {
        this.transitionColors = transitionColors;
        spawnIndex = bombSpawnLocation.nextInt(0,6);
    }
    public void initialiseTask() {
        randomiseBombSpawn();
        if(spawnIndex<3) {
            cursorTimedBombEvent.initialEffects();
            GameObjectHandler.eventQueue.add(cursorTimedBombEvent);
        } else {
            playerTimedBombEvent.initialEffects();
            GameObjectHandler.eventQueue.add(playerTimedBombEvent);
        }
    }

    private void randomiseBombSpawn() {
        switch(spawnIndex) {
            case 0:
                cursorTimedBombEvent = new CursorTimedBomb(transitionColors,TILE_SIZE * 14, TILE_SIZE * 17, TILE_SIZE * 2, TILE_SIZE * 2,this);
                break;
            case 1:
                cursorTimedBombEvent = new CursorTimedBomb(transitionColors,TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2,this);
                break;
            case 2:
                cursorTimedBombEvent = new CursorTimedBomb(transitionColors,TILE_SIZE * 26, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE * 1,this);
                break;
            case 3:
                playerTimedBombEvent = new PlayerTimedBomb(transitionColors, TILE_SIZE * 45, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE * 2, this);
                break;
            case 4:
                playerTimedBombEvent = new PlayerTimedBomb(transitionColors, TILE_SIZE * 12, TILE_SIZE * 7, TILE_SIZE * 2, TILE_SIZE * 2, this);
                break;
            case 5:
                playerTimedBombEvent = new PlayerTimedBomb(transitionColors, TILE_SIZE *44, TILE_SIZE * 23, TILE_SIZE, TILE_SIZE * 2, this);
                break;
        }
    }

    @Override
    public void runTask() {
        if(tick%explosionRate==0&&tick!=0) {
            GamePanel.gameOver();
            System.out.println("would be game over");
        }
    }

    @Override
    public void checkComplete() {

    }
}
