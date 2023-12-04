package utils;

import levels.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {

    Node[][] nodes;
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Point> path = new ArrayList<>();
    boolean goalReached = false;
    int numCols;
    int numRows;
    //Takes in levelData in constructor and runs loadNodesInfo to input info from levelTiles into nodes
    public PathFinder(Level level) {
        loadNodes(level);
    }

    public void reloadPathFinder(Level level) {
        loadNodes(level);
        openList = new ArrayList<>();
        numCols = nodes.length;
        numRows = nodes[0].length;
    }

    public void findPath(Point startIndex, Point endIndex) {
        goalReached = false;
        startNode = nodes[startIndex.x][startIndex.y];
        goalNode = nodes[endIndex.x][endIndex.y];
        openList = new ArrayList<>();
        path = new ArrayList<>();
        currentNode = startNode;
        resetNodeValues();
//        System.out.println("startIndex:"+startIndex);
        search();
        trackTheRoute();
//        System.out.println("Found route startnode:"+startNode.point.x+","+startNode.point.y);
//        printRouteToString();
    }
    public void findPath(Node startNode, Node goalNode) {
        goalReached = false;
        this.startNode = startNode;
        this.goalNode = goalNode;
        currentNode = startNode;
        resetNodeValues();
        search();
        trackTheRoute();
        printRouteToString();
    }
    private void loadNodes(Level level) {
        int numNodesX = level.getLevelTileData().length;
        int numNodesY = level.getLevelTileData()[0].length;
        nodes = new Node[numNodesX][numNodesY];
        int i,j;
        for(i=0; i<numNodesX; i++) {
            for(j=0;j<numNodesY; j++) {
                nodes[i][j] = new Node(i,j, level.isSolidTile(i,j));
            }
        }
        numCols = nodes.length;
        numRows = nodes[0].length;
    }

    private void resetNodeValues() {
        int i,j;
        for(i=0; i<numCols; i++) {
            for(j=0;j<numRows; j++) {
                setCost(nodes[i][j]);
                nodes[i][j].setUnchecked();
                nodes[i][j].setUnopen();
                nodes[i][j].parent = null;
            }
        }
    }

    private void setCost(Node node) {

        //Get gCost (The distance from the start node)
        setGCost(node);
        //Get fCost (The distance from the end node)
        setFCost(node);
    }

    private void setGCost(Node node) {
        //Get gCost (The distance from the start node)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
    }

    private void setFCost(Node node) {
        //Get fCost (The distance from the end node)
        int xDistance = Math.abs(node.col - goalNode.col);
        int yDistance = Math.abs(node.row - goalNode.row);
        node.fCost = xDistance + yDistance;
    }

    public void search() {
        while (!goalReached) {
            int col = currentNode.col;
            int row = currentNode.row;

            //Set current node as check
            checkCurrentNode(currentNode);
            openList.remove(currentNode);
            openNode(col, row);

            iterateBestNode();

            // Check if the goal is reached and set the starting node's parent
            checkGoalReached();
            if (currentNode == startNode) {
                startNode.parent = startNode;  // Set starting node's parent to itself
                goalReached = true;
            }
        }
    }

    private void iterateBestNode() {
        Node bestNode = null;
        int bestNodefCost = 10000;

        for (Node node : openList) {
            if (node.fCost < bestNodefCost) {
                bestNode = node;
                bestNodefCost = node.fCost;
            } else if (node.fCost == bestNodefCost && node.gCost < bestNode.gCost) {
                bestNode = node;
            }
        }

        if (bestNode != null) {
            currentNode = bestNode;
        }
    }


    private void checkGoalReached() {
        if(currentNode == goalNode) {
            goalReached = true;
        }
    }
    private void openNode(int col, int row){
            //Adjust this for cases where node is at edges
            //Open above node
            if(row-1>=0) {
                openNode(nodes[col][row - 1]);
            }
            //Open left node
            if(col-1 >=0) {
                openNode(nodes[col-1][row]);
            }
            //Open bekiw node
            if(row+1<numRows) {
                openNode(nodes[col][row+1]);
            }
            //Open right node
            if(col+1<numCols) {
                openNode(nodes[col + 1][row]);
            }
        }
    private void checkCurrentNode(Node node) {
        node.checked=true;
    }

    private void openNode(Node node) {
        if(!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }


    //Resolves the path as an arraylist
    private void trackTheRoute() {
        Node current = goalNode;
        while(current != startNode) {
            path.add(new Point(current.col, current.row));
            current = current.parent;
//            if(current.parent ==null) {
//                System.out.println("null Parent:"+current.point);
//            }
        }
        path.add(new Point(startNode.col,startNode.row));
        Collections.reverse(path);
    }

    private void printRouteToString() {
        for(Point point:path) {
            System.out.println(point.toString());
        }
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public Node[][] getNodes() {
        return nodes;
    }
}
