package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

@Test(groups = "Grid")
public class GridTest {
    private int rows = 10;
    private int cols = 20;
    private final BlockFeed feed = new BlockFeed();

    @Test(dataProvider = "emptyGrid")
    public void givenNoFlag_gridsEmpty_onGenerateGrid(Grid grid) {
        assertTrue(Arrays.deepEquals(new byte[rows][cols], grid.byteGrid), "Given no args, byteGrid should be empty, is not");
    }

    @Test(dataProvider = "gridWithRandomBlocks")
    public void givenFlag_startBlockAmtGTZero_onPlayFieldConstructor(Grid grid) {
        assertFalse(Arrays.deepEquals(new byte[rows][cols], grid.byteGrid), "Given args, byteGrid shouldn't be empty, but is");
    }

    @Test(dataProvider = "emptyGrid")
    public void givenBlock_gridChanges_onShowAndHide(Grid grid) {
        //Both arrays begin empty
        byte[][] original = Arrays.stream(grid.byteGrid).map(byte[]::clone).toArray(byte[][]::new);
        var block = feed.nextBlock();
        grid.newBlock(block);
        grid.show(block);
        //Test to show block added
        assertFalse(Arrays.deepEquals(original, grid.byteGrid), "Next block called, yet no changes occured");

        grid.hide(block);
        assertTrue(Arrays.deepEquals(original, grid.byteGrid), "After hiding block, arrays still not equal");
    }

    @Test(dataProvider = "emptyGrid")
    public void givenNewBlock_blockHasMove_afterGridGenerated(Grid grid) {
        var rows = grid.byteGrid.length;
        var cols = grid.byteGrid[0].length;
        var block = feed.nextBlock();

        //potential moves
        var count = getCount(block, grid);

        //assertion takes calculated area of options and compares to actual options
        assertEquals(count, ((rows - block.rows() + 1) * (cols - block.cols() + 1)), "Count should be equal to calculated potential moves");

        grid = Grid.getNewGrid(feed, rows, cols, true);

        count = getCount(block, grid);
        //assertion takes total calculation of potential area to place blocks, disregarding placed blocks and compares to actual options
        assertNotSame(count, ((rows - block.rows() + 1) * (cols - block.cols() + 1)), "Count should not be equal to total");
    }

    private int getCount(Block block, Grid grid) {
        var count = 0;
        var rows = grid.byteGrid.length;
        var cols = grid.byteGrid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.isValidMove(block, i, j))
                    count++;
            }
        }
        return count;
    }

    @DataProvider
    public Object[][] emptyGrid() {
        return new Object[][]{{Grid.getNewGrid(feed, rows, cols, false)}};
    }

    @DataProvider
    public Object[][] gridWithRandomBlocks() {
        return new Object[][]{{Grid.getNewGrid(feed, rows, cols, true)}};
    }
}
