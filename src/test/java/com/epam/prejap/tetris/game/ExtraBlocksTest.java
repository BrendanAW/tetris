package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Test
public class ExtraBlocksTest {
    int rows = 10;
    int cols = 20;
    BlockFeed feed = new BlockFeed();
    Printer printer = new Printer(System.out);

    @Test
    public void givenNoFlag_gridsEqual_onPlayFieldConstructor() {
        var p1 = new Playfield(rows, cols, feed, printer, false);
        assertEquals("PlayField generated without starter blocks, should return 0", 0, p1.starterBlockAmt);
    }

    @Test
    public void givenFlag_startBlockAmtGTZero_onPlayFieldConstructor() {
        var p1 = new Playfield(rows, cols, feed, printer, true);
        assertTrue(p1.starterBlockAmt > 0);
    }
}
