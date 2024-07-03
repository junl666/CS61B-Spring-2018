package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        announce();

        while (!q.isEmpty()) {
            int qSize = q.size();
            int v = q.remove();
            for (int i = 0; i < qSize; i++) {
                for (int w : maze.adj(v)) {
                    if (targetFound) {
                        return;
                    }
                    if (!marked[w]) {
                        q.add(w);
                        marked[w] = true;
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        announce();
                        if (w == t) {
                            targetFound = true;
                        }
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

