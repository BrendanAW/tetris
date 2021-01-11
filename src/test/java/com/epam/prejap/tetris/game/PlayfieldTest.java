package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "Playfield")
public class PlayfieldTest {
    private int rows = 5;
    private int cols = 10;
    private BlockFeed feed = new BlockFeed();
    private Printer printer = new Printer(System.out, new Timer(500));
    private Grid grid = Grid.getNewGrid(feed, rows, cols, false);
    private Playfield playfield = new Playfield(feed, printer, grid);


}
