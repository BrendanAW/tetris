package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Playfield {

    private final byte[][] grid;
    private final int rows;
    private final int cols;
    private final Printer printer;
    private final BlockFeed feed;
    public final int starterBlockAmt;

    private Block block;
    private int row;
    private int col;

    /**
     * Sole constructor of Playfield
     *
     * @param rows            height of grid
     * @param cols            width of grid
     * @param feed            block generator
     * @param printer         displays grid to user via System.out
     * @param isStarterBlocks determines how many blocks Playfield will start with
     */
    public Playfield(int rows, int cols, BlockFeed feed, Printer printer, boolean isStarterBlocks) {
        this.rows = rows;
        this.cols = cols;
        this.feed = feed;
        this.printer = printer;
        this.starterBlockAmt = isStarterBlocks ? (int) (Math.random() * rows / 2) + 1 : 0;
        grid = new byte[this.rows][this.cols];
        IntStream.rangeClosed(0, starterBlockAmt).forEach(i -> addStarterBlocks());
    }

    /**
     * Generates new block starting at row 0
     * in the center column
     */
    public void nextBlock() {
        block = feed.nextBlock();
        row = 0;
        col = (cols - block.cols()) / 2;
        show();
    }

    /**
     * Shifts current block left or right one column, then down one row
     *
     * @param move direction of movement
     * @return block moved down one row
     */
    public boolean move(Move move) {
        hide();
        boolean moved;
        switch (move) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
        moved = moveDown();
        show();
        return moved;
    }

    private void addStarterBlocks() {
        block = feed.nextBlock();
        row = rows - block.rows();
        col = (int) (Math.random() * cols - 1);
        while (blockSpaceIsObstructed()) {
            if (row <= rows / 2) {
                col = (int) (Math.random() * cols - 1);
                row = rows - block.rows();
            } else
                row--;
        }
        forEachBrick((i, j, dot) -> grid[row + i][col + j] = dot);
    }

    private boolean blockSpaceIsObstructed() {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                if (grid[row + i][col + j] != 0)
                    return true;
            }
        }
        return false;
    }

    private void moveRight() {
        move(0, 1);
    }

    private void moveLeft() {
        move(0, -1);
    }

    private boolean moveDown() {
        return move(1, 0);
    }

    private boolean move(int rowOffset, int colOffset) {
        boolean moved = false;
        if (isValidMove(block, rowOffset, colOffset)) {
            doMove(rowOffset, colOffset);
            moved = true;
        }
        return moved;
    }

    private boolean isValidMove(Block block, int rowOffset, int colOffset) {
        for (int i = 0; i < block.rows(); i++) {
            for (int j = 0; j < block.cols(); j++) {
                var dot = block.dotAt(i, j);
                if (dot > 0) {
                    int newRow = row + i + rowOffset;
                    int newCol = col + j + colOffset;
                    if (newRow >= rows || newCol >= cols || grid[newRow][newCol] > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void hide() {
        forEachBrick((i, j, dot) -> grid[row + i][col + j] = 0);
    }

    private void show() {
        forEachBrick((i, j, dot) -> grid[row + i][col + j] = dot);
        printer.draw(grid);
    }

    private void doMove(int rowOffset, int colOffset) {
        row += rowOffset;
        col += colOffset;
    }

    private void forEachBrick(BrickAction action) {
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
