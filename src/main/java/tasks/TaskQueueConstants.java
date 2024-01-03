//package tasks;
//
//import java.awt.*;
//import java.util.HashMap;
//import java.util.Map;
//
//import static levels.LevelConstants.TEST_DEMICHROME;
//import static main.GamePanel.TILE_SIZE;
//
//public class TaskQueueConstants {
//
//
//    private final int id;
//    TaskQueueConstants(int id) {
//        this.id = id;
//    }
//
//    public Point[] getEntitySpawnLocations() {
//        return switch(id) {
//            case TEST_DEMICHROME -> new Point[]{new Point(2*TILE_SIZE, 15*TILE_SIZE), new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(25*TILE_SIZE,29*TILE_SIZE)};
//            default -> new Point[]{new Point(0,0)};
//        };
//    }
//
//    public int getNumEntitySpawns() {
//        return getEntitySpawnLocations().length;
//    }
//
//    public record PositionalEventSpawnInfo(int x, int y, int width, int height){};
//
//
//    public Map<Integer, PositionalEventSpawnInfo> getDecreaseZoneSpawnLocations() {
//        return switch(id) {
//            case TEST_DEMICHROME -> {
//                Map<Integer, PositionalEventSpawnInfo> map = new HashMap<>();
//                map.put(1, new PositionalEventSpawnInfo(25*TILE_SIZE, 14*TILE_SIZE, (TILE_SIZE*3), (4*TILE_SIZE-3*TILE_SIZE)/4));
//                map.put(1, new PositionalEventSpawnInfo(25*TILE_SIZE, (56*TILE_SIZE+3*TILE_SIZE)/4,TILE_SIZE*3, 4*TILE_SIZE-3*(TILE_SIZE)/4));
//                yield map;
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + id);
//        };
//    }
//
//    public int getNumDecreaseZoneSpawnlocations() {
//        return getDecreaseZoneSpawnLocations().keySet().size();
//    }
//
//    public PositionalEventSpawnInfo[] getCursorBombLocations() {
//        return switch(id) {
//            case TEST_DEMICHROME -> new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(TILE_SIZE * 14, TILE_SIZE * 17, TILE_SIZE * 2, TILE_SIZE * 2),
//                    new PositionalEventSpawnInfo(TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2),
//                    new PositionalEventSpawnInfo(TILE_SIZE * 26, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE)};
//            default -> new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(25*TILE_SIZE,14*TILE_SIZE,TILE_SIZE,TILE_SIZE)};
//        };
//    }
//
//    public int getNumCursorBombLocations() {
//        return getCursorBombLocations().length;
//    }
//}
