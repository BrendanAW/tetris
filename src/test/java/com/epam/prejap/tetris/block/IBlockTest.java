package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = "Block")
public class IBlockTest {

    //given
    @Test(dataProvider = "iBlockShape")
    public void testIBlockShape(byte[][] expected) {
        //when
        byte[][] actual = new IBlock().image;

        //then
        assertEquals(actual, expected);
    }

    //given
    @Test(dataProvider = "iBlockShape")
    public void testIfBlockConstructorAcceptIBlock(byte[][] expected) {
        //when
        byte[][] actual = new IBlock().image;

        //then
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dotsForIBlock")
    public void shallCreateIBlockWithIShapedDots(int row, int col) {
        //given
        Block iBlock = new IBlock();
        int dotMark = 1;

        //when
        byte actualDot = iBlock.dotAt(row, col);

        //then
        assertEquals(actualDot, dotMark, "Shall create I block with correct shaped dots, but did not");
    }

    @DataProvider()
    public static Object[] dotsForIBlock() {
        return new Object[][]{
                {0, 0},
                {1, 0},
                {2, 0},
                {3, 0},
        };
    }

    @DataProvider
    public static Object[] iBlockShape() {
        byte[][] iBlock = {
                {1},
                {1},
                {1},
                {1}};
        return new Object[]{iBlock};
    }
}
