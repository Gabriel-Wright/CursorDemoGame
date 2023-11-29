package utils;

import levels.Level;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class PathFinder {

    Node[][] nodes;
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> path = new ArrayList<>();
    boolean goalReached = false;

    //Takes in levelData in constructor and runs loadNodesInfo to input info from levelTiles into nodes
    public PathFinder(Level level) {
        loadNodes(level);
    }

    public void reloadPathFinder(Level level) {
        loadNodes(level);
        openList = new ArrayList<>();
    }

    public void findPath(Point startIndex, Point endIndex) {
        this.startNode = nodes[startIndex.x][startIndex.y];
        this.goalNode = nodes[endIndex.x][endIndex.y];
        currentNode = startNode;
        loadCost();
        search();
        trackTheRoute();
        printRouteToString();
    }
    public void findPath(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        currentNode = startNode;
        loadCost();
        search();
        trackTheRoute();
        printRouteToString();
    }
    private void loadNodesAndCost(Level level) {
        int numNodesX = level.getLevelTileData().length;
        int numNodesY = level.getLevelTileData()[0].length;
        nodes = new Node[numNodesX][numNodesY];
        int i,j;
        for(i=0; i<numNodesX; i++) {
            for(j=0;j<numNodesY; j++) {
                nodes[i][j] = new Node(i,j, level.isSolidTile(i,j));
            }
        }
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
    }

    private void loadCost() {
        int numNodesX = nodes.length;
        int numNodesY = nodes[0].length;
        int i,j;
        for(i=0; i<numNodesX; i++) {
            for(j=0;j<numNodesY; j++) {
                setCost(nodes[i][j]);
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

            openNode(col, row);

            iterateBestNode();

            checkGoalReached();
        }
    }

    private void iterateBestNode() {
        //Should not cause issues with this being a null method
        Node bestNode = null;
        //Maybe need to handle writing a different value instead of 999?
        int bestNodefCost = 999;

        for (Node node : openList) {
            if (node.fCost < bestNodefCost) {
                bestNode = node;
                bestNodefCost = node.fCost;
            } else if (node.fCost == bestNode.fCost && node.gCost < bestNode.gCost) {
                bestNode = node;
            }
        }
        currentNode = bestNode;
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
                openNode(nodes[col][row - 1]);
            }
            //Open bekiw node
            if(row+1<nodes[0].length) {
                openNode(nodes[col - 1][row]);
            }
            //Open right node
            if(col+1<nodes.length) {
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
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
    }

    private void printRouteToString() {
        for(Node node:path) {
            System.out.println("("+node.col+","+node.row+")");
        }
    }
}
