import java.util.*;

public class IDS extends GAME {
   public Node search(int maxLimit) {
        // Iteratively increase depth limit
        for (int limit = 0; limit <= maxLimit; limit++) {
            System.out.println("Depth limit: " + limit);

            Stack<Node> frontier = new Stack<>(); // Frontier for depth-limited search
            HashSet<String> explored = new HashSet<>(); // Track explored nodes

            // Initial node with cost 0, depth 0, and no parent
            Node startNode = new Node(0, null, this, 0);
            frontier.push(startNode);

            while (!frontier.isEmpty()) {
                Node currentNode = frontier.pop();
                char[] currentBoard = currentNode.getBoard();

                // Check if the current node is the goal state
                if (isGoal(currentNode)) {
                    return currentNode; // Return the goal node to trace back the path
                }

                // Mark the current configuration as explored
                explored.add(Arrays.toString(currentBoard));

                // Expand nodes only if within the depth limit
                if (currentNode.depth < limit) {
                    for (Node child : generateChildren(currentNode)) {
                        String childBoardStr = Arrays.toString(child.getBoard());
                        if (!explored.contains(childBoardStr)) {
                            frontier.push(child);
                        }
                    }
                }
            }
        }

        System.out.println("No solution found within depth limit " + maxLimit);
        return null; // Return null if no solution is found
    }
    private List<Node> generateChildren(Node parent) {
        Stack<Node> children = new Stack<>();
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
            newState.PrintBoard();
            int moveCost = Math.abs(move); //to Kostos einai h apolyth timh ths metakinhshs

            Node child = new Node(parent.cost + moveCost, parent, newState, parent.depth + 1);
            children.push(child);
        }

        return children;
    }
    public int[] COST(int position){ //prepei na epsitrefei ton pinaka ton pithanon kinhsewn gia th nantistoixh thesh
                         // meta to costos einai h metakinhsh se apolyth timh giati  borei na metainhthei -3 -2 -1 1 1 2 3 thewrtika
        List<Integer> validMoves = new ArrayList<>();
        char[] boardClone = this.getBoardClone();

        for (int k = 3; k >= 1; k--) {
            if (position - k >= 0) {  // Ensure the move stays within bounds to the left
                validMoves.add(-k);  // Add the left-side move
            }
        }

        // Generate valid moves to the right (+1, +2, +3)
        for (int k = 1; k <= 3; k++) {
            if (position + k < boardClone.length) { // Ensure the move stays within bounds
                validMoves.add(k);
            }
        }
        int[] costs = new int[validMoves.size()];
        for ( int i = 0; i < validMoves.size(); i++) {
            costs[i] = validMoves.get(i);
        }
        /*for( int i=0 ;i < costs.length;i++){
            System.out.print("Cost"+costs[i]+" ");
        }*/
        return costs;
    }
}
