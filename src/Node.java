import jdk.jfr.DataAmount;

import java.util.PriorityQueue;


class Node {
    public int cost;
    public Node parent;
    GAME state;
    int depth;
    int energy;

    public Node(int cost,Node parent,int state,int depth){
        this.cost=cost;
        this.parent=null;
    }
    public void steNextNode(Node node){
        this.parent=node;
    }
    public Node getNextNode(Node node){
        this.parent=node;
        return
    }



}
