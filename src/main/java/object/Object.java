package object;

import entities.Entity;
import entities.player.Player;
import levels.Level;

import java.awt.image.BufferedImage;

public class Object extends SuperObject{

    public Object(int x, int y, BufferedImage objectImage, boolean collided) {
        super(x, y, objectImage, collided);
    }

    @Override
    public void CollideWithEntity(Entity entity, Level level) {
        //Object vanishes

        System.out.println("Entity");
    }

    public void CollideWithEntity(Player player, Level level) {
        level.getLevelObjects()[x][y] = null;
        System.out.println("Deleted brah");
    }
}

