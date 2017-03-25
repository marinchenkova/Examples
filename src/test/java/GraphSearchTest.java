import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marinchenko V. A.
 */
public class GraphSearchTest {
    @Test
    public void bfs() throws Exception {
        int graph[][] = new int[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                graph[i][j] = 0;
        graph[0][1] = 1;
        graph[1][0] = 1;
        graph[1][2] = 1;
        graph[2][1] = 1;

        int marked = 0;

        assertEquals(2, GraphSearch.bfs(graph, marked, 2));

    }

}