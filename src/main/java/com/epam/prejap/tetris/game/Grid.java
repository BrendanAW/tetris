package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    final byte[][] byteGrid;
    private final int rows;
    private final int cols;
    private final BlockFeed feed;

    private int row;
    private int col;

    /**
     * @param feed supplies grid with new blocks
     * @param rows height of byteGrid
     * @param cols width of byteGrid
     */

    public Grid(BlockFeed feed, int rows, int cols) {
        this.feed = feed;
        this.rows = rows;
        this.cols = cols;
        byteGrid = new byte[rows][cols];
    }

    /**
     * Populates grid with random blocks if argument present
     *
     * @param args to be checked for flag -rb or -RB
     */
    public void generateGrid(List<String> args) {
        if (args.isEmpty() || !(args.contains("-rb") || args.contains("-RB")))
            return;
        var starterBlockAmt = (int) (Math.random() * cols / 4) + 1;
        IntStream.rangeClosed(0, starterBlockAmt).forEach(i -> addStarterBlocks());
    }

    void show(Block block) {
        forEachBrick(((i, j, dot) -> byteGrid[i + row][j + col] = dot), block);
    }

    void hide(Block block) {
        forEachBrick(((i, j, dot) -> byteGrid[i + row][j + col] = 0), block);
    }

    boolean isValidMove(Block block, int rowOffset, int colOffset) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    int newRow = row + i + rowOffset;
                    int newCol = col + j + colOffset;
                    if (newRow >= rows || newCol >= cols || byteGrid[newRow][newCol] > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void newBlock(Block block) {
        row = 0;
        col = (cols - block.cols()) / 2;
    }

    void doMove(int rowOffset, int colOffset) {
        row += rowOffset;
        col += colOffset;
    }

    private void addStarterBlocks() {
        var block = feed.nextBlock();
        row = rows - block.rows();
        col = (int) (Math.random() * cols - 1);
        while (blockSpaceIsObstructed(block)) {
            if (row <= rows / 2) {
                col = (int) (Math.random() * cols - 1);
                row = rows - block.rows();
            } else
                row--;
        }
        forEachBrick(((i, j, dot) -> byteGrid[row + i][col + j] = dot), block);
    }

    private boolean blockSpaceIsObstructed(Block block) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                if (byteGrid[row + i][col + j] != 0)
                    return true;
            }
        }
        return false;
    }

    private void forEachBrick(BrickAction action, Block block) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    action.act(i, j, dot);
                }
            }
        }
    }

    private interface BrickAction {
        void act(int i, int j, byte dot);
    }
}
