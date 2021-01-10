package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test(groups = "Block")
public class LBlockTest {

    @Test
    public void shallCreateLBlockWithCorrectDimensions() {
        //given
        SoftAssert softAssert = new SoftAssert();
        LBlock lBlock = new LBlock();
        byte[][] expectedImage = new byte[3][2];

        //when
        int actualRows = lBlock.rows;
        int actualCols = lBlock.cols;

        //then
        softAssert.assertTrue(actualRows == expectedImage.length);
        softAssert.assertTrue(actualCols == expectedImage[0].length);
        softAssert.assertAll("Shall create L block with correct dimensions, but did not");
    }

    @Test(dataProvider = "dotsForLBlock")
    public void shallCreateLBlockWithLShapedDots(int row, int col) {
        //given
        LBlock lBlock = new LBlock();
        int dotMark = 1;

        //when
        byte actualDot = lBlock.dotAt(row, col);

        //then
        assertEquals(actualDot, dotMark, "Shall create L block with correct shaped dots, but did not");
    }

    @Test(dataProvider = "emptySpacesForLBlock")
    public void shallCreateLBlockWithCorrectEmptySpaces(int row, int col) {
        //given
        LBlock lBlock = new LBlock();
        int emptyMark = 0;

        //when
        byte actualEmptySpace = lBlock.dotAt(row, col);

        //then
        assertEquals(actualEmptySpace, emptyMark, "Shall create L block with correct shaped empty marks, but did not");
    }

    @DataProvider()
    public static Object[] dotsForLBlock() {
        return new Object[][]{
                {0, 0},
                {1, 0},
                {2, 0},
                {2, 1},
        };
    }

    @DataProvider()
    public static Object[] emptySpacesForLBlock() {
        return new Object[][]{
                {0, 1},
                {1, 1},
        };
    }

}
