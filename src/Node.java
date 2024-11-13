import jdk.jfr.DataAmount;

import java.util.PriorityQueue;


class Node {
    public int cost;
    public Node parent;

    public Node(int cost){
        this.cost=cost;
        this.parent=null;
    }
    public void steNextNode(Node node){
        this.parent=node;
    }
    public Node getNextNode(Node node){
        this.parent=node;
    }



}
