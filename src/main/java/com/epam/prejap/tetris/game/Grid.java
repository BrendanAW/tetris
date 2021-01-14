package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Class contains byte array responsible for storing data of {@link Block} locations
 *
 * @author Brendan Williams
 */
public class Grid {
    final byte[][] byteGrid;
    private final int rows;
    private final int cols;
    private final BlockFeed feed;

    private int row;
    private int col;

    private Grid(BlockFeed feed, int rows, int cols) {
        this.feed = feed;
        this.rows = rows;
        this.cols = cols;
        byteGrid = new byte[rows][cols];
    }

    /**
     * @param feed           supplies grid with new blocks
     * @param rows           height of byteGrid
     * @param cols           width of byteGrid
     * @param addExtraBlocks to determine if blocks will be added
     * @return new Grid with updatedByteGrid
     */
    public static Grid getNewGrid(BlockFeed feed, int rows, int cols, boolean addExtraBlocks) {
        var grid = new Grid(feed, rows, cols);
        if (addExtraBlocks) {
            var starterBlockAmt = (int) (Math.random() * cols / 4) + 1;
            IntStream.rangeClosed(0, starterBlockAmt).forEach(i -> grid.addStarterBlocks());
        }
        return grid;
    }

    void show(Block block) {
        forEachBrick(((i, j) -> byteGrid[i + row][j + col] = block.getColorId()), block);
    }

    void hide(Block block) {
        forEachBrick(((i, j) -> byteGrid[i + row][j + col] = 0), block);
    }

    boolean isValidMove(Block block, int rowOffset, int colOffset) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    int newRow = row + i + rowOffset;
                    int newCol = col + j + colOffset;
                    if (newRow >= rows || newCol >= cols || newRow < 0 || newCol < 0 || byteGrid[newRow][newCol] > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void newBlock(int blockCols) {
        row = 0;
        col = (cols - blockCols) / 2;
    }

    void doMove(int rowOffset, int colOffset) {
        row += rowOffset;
        col += colOffset;
    }

    private void addStarterBlocks() {
        var block = feed.nextBlock();
        Supplier<Integer> randomCol = () -> (int) (Math.random() * (cols - block.cols()));
        row = rows - block.rows();
        col = randomCol.get();
        while (blockSpaceIsObstructed(block)) {
            if (row <= rows / 2) {
                col = randomCol.get();
                row = rows - block.rows();
            } else
                row--;
        }
        forEachBrick(((i, j) -> byteGrid[row + i][col + j] = block.getColorId()), block);
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
                    action.act(i, j);
                }
            }
        }
    }

    private interface BrickAction {
        void act(int i, int j);
    }
}
