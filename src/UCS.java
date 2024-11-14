import java.util.*;

public class UCS extends GAME {
    public Node search() {
        // Priority Queue to hold nodes based on their cost (low cost has high priority)
        PriorityQueue<Node> frontier = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        HashSet<String> explored = new HashSet<>();

        // Initial node with cost 0, depth 0, and no parent
        Node startNode = new Node(0, null, this, 0);
        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            // Get the node with the lowest cost
            Node currentNode = frontier.poll();
            char[] currentBoard = currentNode.getBoard();

            // Check if the current board configuration is the goal
            if (isGoal()) {
                return currentNode; // Return the goal node to trace back the path
            }

            // Mark the current configuration as explored
            explored.add(Arrays.toString(currentBoard));

            // Expand nodes by generating valid moves
            for (Node child : generateChildren(currentNode)) {
                String childBoardStr = Arrays.toString(child.getBoard());
                if (!explored.contains(childBoardStr)) {
                    frontier.add(child);
                }
            }
        }
        return null; // Return null if no solution is found
    }
    private List<Node> generateChildren(Node parent) {
        List<Node> children = new ArrayList<>();
        char[] parentBoard = parent.getBoard();
        int emptyIndex = returnS(parentBoard);

        // Use the getValidMoves method to get valid moves for the empty space
        int[] moves = COST(emptyIndex);

        for (int move : moves) {
            int newIndex = emptyIndex + move;
            char[] newBoard = parentBoard.clone();
            swap(newBoard, emptyIndex, newIndex);

            GAME newState = new GAME();
            newState.board = newBoard;

            Node child = new Node(parent.cost + 1, parent, newState, parent.depth + 1);
            children.add(child);
        }

        return children;
    }
    public int[] COST(int position){
        char[] boardClone = this.getBoardClone();

    }
}
