import gameObjects.entities.Entity;
import org.junit.jupiter.api.Test;
import utils.FindOvelapTiles;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static utils.FindOvelapTiles.FindOverlapTiles;

public class FindOverlapTilesTest {

    @Test
    public void testFindOverlapTilesWithOffsets() {
        Entity entity = new TestEntity();
        entity.setX(2*TILE_SIZE+1); // Set the entity's X position
        entity.setY(3*TILE_SIZE+1); // Set the entity's Y position
        Rectangle entityBounds = new Rectangle(TILE_SIZE/4,TILE_SIZE/4,TILE_SIZE/2 , TILE_SIZE/2);
        entity.setBounds(entityBounds);
        // Calculate overlap tiles with offsets
        Point[] overlapTiles = FindOverlapTiles(entity, TILE_SIZE, TILE_SIZE);
        System.out.println("Overlap Tiles:");
        for (Point point : overlapTiles) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        // Define the expected overlap tiles
        Point[] expectedTiles = {
                new Point(3, 4)
        };
        System.out.println("Expected Tiles:");
        for (Point point : expectedTiles) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        // Check if the calculated overlap tiles match the expected tiles
        assertArrayEquals(expectedTiles, overlapTiles);
    }

    @Test
    public void testFindOverlapTilesWithoutOffsets() {
        Entity entity = new TestEntity();
        entity.setX(2*TILE_SIZE); // Set the entity's X position
        entity.setY(3*TILE_SIZE); // Set the entity's Y position
        Rectangle entityBounds = new Rectangle(TILE_SIZE/4,TILE_SIZE/4,TILE_SIZE/2, TILE_SIZE/2);
        entity.setBounds(entityBounds);

        // Calculate overlap tiles without offsets
        Point[] overlapTiles = FindOverlapTiles(entity);
        System.out.println("Overlap Tiles:");
        for (Point point : overlapTiles) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        // Define the expected overlap tiles
        Point[] expectedTiles = {
            new Point(2,3)
        };
        System.out.println("Expected Tiles:");
        for (Point point : expectedTiles) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        // Check if the calculated overlap tiles match the expected tiles
        assertArrayEquals(expectedTiles, overlapTiles);
    }

    private static class TestEntity extends Entity {
        public TestEntity() {
            super(0, 0, null); // Provide appropriate values for constructor
        }

        @Override
        protected void handleEntityCollision(Entity entity) {

        }

        @Override
        protected void updatePos() {
            // Implement updatePos for testing
        }

        @Override
        protected void updateAction() {
            // Implement updateAction for testing
        }
    }

}
