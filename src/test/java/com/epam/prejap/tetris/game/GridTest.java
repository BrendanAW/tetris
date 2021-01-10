package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Block;
import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.AssertJUnit.*;

@Test(groups = "Grid")
public class GridTest {
    private int rows = 10;
    private int cols = 20;
    private final BlockFeed feed = new BlockFeed();
    private Grid grid;

    @BeforeMethod
    public void setUp() {
        grid = new Grid(feed, rows, cols);
    }

    @Test
    public void givenNoFlag_gridsEmpty_onGenerateGrid() {
        grid.generateGrid(Collections.emptyList());
        assertTrue("Given no args, byteGrid should be empty, is not", Arrays.deepEquals(new byte[rows][cols], grid.byteGrid));
    }

    @Test
    public void givenFlag_startBlockAmtGTZero_onPlayFieldConstructor() {
        grid.generateGrid(List.of("-rb"));
        assertFalse("Given args, byteGrid shouldn't be empty, but is", Arrays.deepEquals(new byte[rows][cols], grid.byteGrid));
    }

    @Test
    public void givenBlock_gridChanges_onShowAndHide() {
        //Both arrays begin empty
        byte[][] original = Arrays.stream(grid.byteGrid).map(byte[]::clone).toArray(byte[][]::new);
        var block = feed.nextBlock();
        grid.newBlock(block);
        grid.show(block);
        //Test to show block added
        assertFalse("Next block called, yet no changes occured", Arrays.deepEquals(original, grid.byteGrid));

        grid.hide(block);
        assertTrue("After hiding block, arrays still not equal", Arrays.deepEquals(original, grid.byteGrid));
    }

    @Test
    public void givenNewBlock_blockHasMove_afterGridGenerated() {
        rows = 10;
        cols = 10;
        grid = new Grid(feed, rows, cols);
        grid.generateGrid(Collections.emptyList());
        var block = feed.nextBlock();

        //potential moves
        var count = getCount(block);

        //assertion takes calculated area of options and compares to actual options
        assertEquals("Count should be equal to calculated potential moves", count, ((rows - block.rows() + 1) * (cols - block.cols() + 1)));

        grid.generateGrid(List.of("-rb"));

        count = getCount(block);
        //assertion takes total calculation of potential area to place blocks, disregarding placed blocks and compares to actual options
        assertNotSame("Count should not be equal to total", count, ((rows - block.rows() + 1) * (cols - block.cols() + 1)));
    }

    private int getCount(Block block) {
        var count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.isValidMove(block, i, j))
                    count++;
            }
        }
        return count;
    }
}
