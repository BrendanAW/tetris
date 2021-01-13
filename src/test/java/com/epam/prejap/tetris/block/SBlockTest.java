package com.epam.prejap.tetris.block;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = {"Block", "SBlock"})
public class SBlockTest {

    @Test
    public void blockShouldHaveThreeColumns() {
        //given
        SBlock block = new SBlock();

        //when
        int expectedNumbersOfColumns = 3;

        //then
        assertEquals(block.cols, expectedNumbersOfColumns, "Block should have three columns");
    }

    @Test
    public void blockShouldHaveTwoRows() {
        //given
        SBlock block = new SBlock();

        //when
        int expectedNumberOfRows = 2;

        //then
        assertEquals(block.rows, expectedNumberOfRows, "Block should have two rows");
    }

    @Test(dataProvider = "blockPoints")
    public void shouldHaveSShape(int x, int y, int testValue) {
        //given
        SBlock block = new SBlock();

        //when
        byte blockValue = block.dotAt(x, y);

        //then
        assertEquals(blockValue, testValue, "Block should have different value here");
    }

    @DataProvider()
    public static Object[] blockPoints() {
        return new Object[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 2, 1},
                {1, 0, 1},
                {1, 1, 1},
                {1, 2, 0},
        };
    }
}
