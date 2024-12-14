/*
Problem Details
Maze Representation:

The maze is a grid (e.g., a 2D matrix) where each cell contains a coin value or is empty (0).
Example:
lua
Copy code
[[1, 3, 1],
 [2, 0, 4],
 [0, 6, 5]]
Movement:

You can move in four directions: up, down, left, or right.
Rules:

You can only collect a coin if its value is greater than or equal to the last coin collected.
Goal:

Maximize the sum of collected coins.

*/

import java.util.*;

public class MaxCoinsMaze {

    public int maxCoins(int[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        // Memoization map to store state (x, y, lastCoinValue)
        Map<String, Integer> memo = new HashMap<>();
        int maxCoins = 0;

        // Start DFS from all possible starting points
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxCoins = Math.max(maxCoins, dfs(maze, i, j, 0, memo));
            }
        }

        return maxCoins;
    }

    private int dfs(int[][] maze, int x, int y, int lastCoin, Map<String, Integer> memo) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Out of bounds or invalid move (coin value less than last collected)
        if (x < 0 || x >= rows || y < 0 || y >= cols || maze[x][y] < lastCoin) {
            return 0;
        }

        // Create a unique key for memoization
        String key = x + "," + y + "," + lastCoin;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Current coin value
        int currentCoin = maze[x][y];

        // Explore all four directions
        int maxCoinsCollected = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            maxCoinsCollected = Math.max(maxCoinsCollected,
                    dfs(maze, x + dir[0], y + dir[1], currentCoin, memo));
        }

        // Add the current coin value to the maximum coins collected from the next moves
        int totalCoins = currentCoin + maxCoinsCollected;
        memo.put(key, totalCoins);
        return totalCoins;
    }

    public static void main(String[] args) {
        MaxCoinsMaze solver = new MaxCoinsMaze();
        int[][] maze = {
                {1, 3, 1},
                {2, 0, 4},
                {0, 6, 5}
        };

        System.out.println("Maximum coins collected: " + solver.maxCoins(maze));
    }
}
