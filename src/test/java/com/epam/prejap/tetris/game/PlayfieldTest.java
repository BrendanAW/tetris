package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.BlockFeed;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

@Test(groups = "Playfield")
public class PlayfieldTest {
    private final int rows = 10;
    private final BlockFeed feed = new BlockFeed();
    private final Printer printer = new Printer(System.out, new Timer(500));
    private Grid grid;
    private Playfield playfield;

    @BeforeMethod
    public void setUp() {
        int cols = 20;
        grid = Grid.getNewGrid(feed, rows, cols, false);
        playfield = new Playfield(feed, printer, grid);
    }

    @Test(dataProvider = "arrayOfMoves")
    public void givenEmptyGrid_moveCalledProperAmountOfTimes(Move move) {
        playfield.nextBlock();
        int blockStart = 0;
        var length = 0;
        var count = 0;

        //finds if playField.nextBlock generates block on Grid byteField
        while (grid.byteGrid[0][blockStart] != 1)
            blockStart++;

        for (int i = 0; i < rows; i++) {
            if (grid.byteGrid[i][blockStart] != 1)
                break;
            length++;
        }

        while (playfield.move(move))
            count++;

        assertEquals(count, rows - length, "Block moved an illegal number of turns down");
    }

    @DataProvider
    public Object[][] arrayOfMoves() {
        return IntStream.rangeClosed(0, 15).boxed().map(i -> new Object[]{Move.values()[i % 3]}).toArray(Object[][]::new);
    }

}
