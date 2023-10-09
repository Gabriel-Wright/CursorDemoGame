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
import java.util.Set;

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
        grid.initialiseGrid(entities, null, null);

        // Check if both entities are in the appropriate cells
        Cell cell1 = grid.getCell(0, 0);
        Assertions.assertNotNull(cell1);
        assertTrue(cell1.getEntities().contains(entity1));

        Cell cell2 = grid.getCell(2, 2);
        Assertions.assertNotNull(cell2);
        assertTrue(cell2.getEntities().contains(entity2));
    }

    @Test
    public void testReassignEntityCells() {
        //Entity moved to 2,1 1,1

        //Entity starts at 0,0 1,0 0,1 1,1 and then moves to
        Entity entity = new TestEntity();
        entity.setX(1);
        entity.setY(1);
        Rectangle entityBounds = new Rectangle(1, 1, TILE_SIZE, TILE_SIZE);
        //Entity should exist in cells 0,0 1,0 0,1 1,1.
        entity.setBounds(entityBounds);
        List<Entity> entities = new ArrayList<>();
        entities.add(entity);

        // Add entities to cells based on their bounds
        grid.initialiseGrid(entities, null, null);
        System.out.println(grid.toString());

        //Entity moves a tile in either axis
        entity.setX(1+TILE_SIZE);
        entity.setY(1+TILE_SIZE);
        // Move the entity by a small amount
        grid.reassignEntityCells(entity, -TILE_SIZE, -TILE_SIZE);

        System.out.println(grid.toString());
        // Check if the entity is in the new cell and removed from the old cell
        Cell oldCell = grid.getCell(0, 0);
        Cell newCell = grid.getCell(1, 1);


        Assertions.assertNull(oldCell);
        Assertions.assertNotNull(newCell);
        Assertions.assertTrue(newCell.getEntities().contains(entity));
    }

    @Test
    public void testGetAssignedCells() {
        Entity entity = new TestEntity();
        entity.setX(1);
        entity.setY(1);
        Rectangle entityBounds = new Rectangle(1, 1, TILE_SIZE, TILE_SIZE);
        //Entity should exist in 0,0 1,0 0,1 1,1
        entity.setBounds(entityBounds);
        List<Entity> entities = new ArrayList<>();
        entities.add(entity);

        grid.initialiseGrid(entities, null, null);

        Point[] expectedCells = new Point[] {new Point(0,0),
            new Point(1,0), new Point(0,1),
            new Point(1,1)
        };

        for(Point point: expectedCells) {
            System.out.println(point);
        }

        Point[] actualCells = grid.getAssignedCells(entity).toArray(new Point[0]);

        //Switched 1 and 2 indexes because of order arrays was looped is different
        Assertions.assertEquals(expectedCells[0], actualCells[0]);
        Assertions.assertEquals(expectedCells[1], actualCells[2]);
        Assertions.assertEquals(expectedCells[2], actualCells[1]);
        Assertions.assertEquals(expectedCells[3], actualCells[3]);

    }

    // Define a test entity class that extends Entity for testing purposes
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
