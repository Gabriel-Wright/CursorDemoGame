import gameObjects.handler.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static main.GamePanel.TILE_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import gameObjects.entities.Entity;
import object.SuperObject;
import gameObjects.handler.GameObjectGrid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectGridTest {

    private GameObjectGrid grid;
    private Entity mockEntity;

    @BeforeEach
    public void setUp() {
        //Initialise empty grid before each test
        grid = new GameObjectGrid(5,5);
    }

    @Test
    public void testAddEntityToCell() {
        Entity entity = new TestEntity(); //Create a test entity

        //Add the entity to a specific cell
        grid.addEntityToCell(1,2,entity);
        //Retrieve cell and check if entity is in it
        Cell cell = grid.getCell(1,2);
        Assertions.assertNotNull(cell);
        Assertions.assertTrue(cell.getEntities().contains(entity));
    }

    @Test
    public void testAddEntitiesToCells() {
        Entity entity1 = new TestEntity();
        Entity entity2 = new TestEntity();
        entity1.setX(1);
        entity1.setY(2);
        Rectangle entity1Bounds = new Rectangle(1,2,TILE_SIZE, TILE_SIZE);
        entity1.setBounds(entity1Bounds);
        entity2.setX(3);
        entity2.setY(4);
        Rectangle entity2Bounds = new Rectangle(TILE_SIZE+2,TILE_SIZE+2, TILE_SIZE, TILE_SIZE);
        entity2.setBounds(entity2Bounds);
        List<Entity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        // Add entities to cells based on their bounds
        grid.initialiseGrid(entities, null);

        // Check if both entities are in the appropriate cells
        Cell cell1 = grid.getCell(0, 0);
        Assertions.assertNotNull(cell1);
        assertTrue(cell1.getEntities().contains(entity1));

        Cell cell2 = grid.getCell(2, 2);
        Assertions.assertNotNull(cell2);
        assertTrue(cell2.getEntities().contains(entity2));
    }

    // Define a test entity class that extends Entity for testing purposes
    private static class TestEntity extends Entity {
        public TestEntity() {
            super(0, 0, null, null); // Provide appropriate values for constructor
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
