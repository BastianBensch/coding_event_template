package com.klosebros.kata;

import java.util.Arrays;

public class GameOfLife {

    public static final String DEAD_CELL = "0";
    private static final String LIVE_CELL = "X";

    public String[][] nextGeneration(String[][] gameMap) {
        int rows = gameMap.length;
        int columns = gameMap[0].length;

        var nextGen = new String[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                nextGen[y][x] = calcNextCell(gameMap, y, x);
            }
        }
        for (final String[] row : nextGen) {
            System.out.println(Arrays.toString(row));
        }
        return nextGen;
    }

    private static String calcNextCell(final String[][] gameMap, final int y, final int x) {
        int neighborCount = countNeighbors(gameMap, y, x);
        final var cell = gameMap[y][x];
        if (neighborCount == 0) {
            return DEAD_CELL;
        } else if (neighborCount == 3) {
            return LIVE_CELL;
        } else if (neighborCount == 2 && cell.equals(LIVE_CELL)) {
            return LIVE_CELL;
        }
        return DEAD_CELL;
    }

    private static int countNeighbors(final String[][] gameMap, final int y, final int x) {
        int count = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i == y && j == x) {
                    continue;
                }
                try {
                    count += gameMap[i][j].equalsIgnoreCase(LIVE_CELL) ? 1 : 0;
                } catch (Exception e) {
                    //ignore
                }
            }
        }
        return count;
    }
}
