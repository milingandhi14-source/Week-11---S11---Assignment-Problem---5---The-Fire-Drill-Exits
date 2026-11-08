
import java.util.Queue;
import java.util.LinkedList;

public class q5 {

    public static int[][] exitDistance(char[][] plan) {

        int rows = plan.length;
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];

        Queue<int[]> queue = new LinkedList<>();

        // Initialize all cells with -1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                distance[i][j] = -1;

                // Add all exits to the queue
                if (plan[i][j] == 'E') {

                    distance[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // BFS traversal
        while (!queue.isEmpty()) {

            int[] cell = queue.poll();

            int r = cell[0];
            int c = cell[1];

            // Visit all four neighboring cells
            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                // Check boundaries and walls
                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    plan[nr][nc] != '#' &&
                    distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;

                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {

        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        System.out.println("Exit Distances:");

        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[i].length; j++) {

                System.out.print(result[i][j] + " ");
            }

            System.out.println();
        }
    }
}
