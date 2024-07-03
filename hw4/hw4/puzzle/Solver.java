package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode initNode = new SearchNode(initial, null);
        pq.insert(initNode);
        while (!pq.isEmpty()) {
            SearchNode node = pq.delMin();
            if (node.currState.isGoal()) {
                getSolution(node);
                return;
            }
            for (WorldState neighbor: node.currState.neighbors()) {
                if (node.prevNode == null || !neighbor.equals(node.prevNode.currState)) {
                    SearchNode nodeNew = new SearchNode(neighbor, node);
                    pq.insert(nodeNew);
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle
     * starting at the initial WorldState.
     * */
    public int moves() {
        return minMoves;
    }

    /**
     * Returns a sequence of WorldStates from the initial
     * WorldState to the solution.
     * */
    public Iterable<WorldState> solution() {
        return solution;
    }

    private void getSolution(SearchNode node) {
        minMoves = node.movesMade;
        solution = new LinkedList<>();
        while (node != null) {
            solution.addFirst(node.currState);
            node = node.prevNode;
        }
    }

    private Deque<WorldState> solution;
    private int minMoves;


    private class SearchNode implements Comparable {
        private WorldState currState;
        private int movesMade;
        private SearchNode prevNode;

        public SearchNode(WorldState state, SearchNode node) {
            currState = state;
            prevNode = node;
            if (node == null) {
            movesMade = 0;
            } else {
                movesMade = node.movesMade + 1;
            }
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof SearchNode) {
                return this.priority() - ((SearchNode) o).priority();
            }
            throw new ClassCastException();
        }

        private int priority() {
            return movesMade + currState.estimatedDistanceToGoal();
        }
    }
}
