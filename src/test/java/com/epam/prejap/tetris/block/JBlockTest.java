package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

@Test(groups = {"Block", "JBlock"})
public class JBlockTest {
    @Test
    public void shallCreateJBlockWithCorrectDimensions() {
        //given
        SoftAssert softAssert = new SoftAssert();
        Block jBlock = new JBlock();
        byte[][] expectedImage = new byte[3][2];

        //when
        int actualRows = jBlock.rows;
        int actualCols = jBlock.cols;

        //then
        softAssert.assertEquals(actualRows, expectedImage.length,
                String.format("The expected height for a J-shaped block should be %d, but was %d.",
                        expectedImage.length, actualRows));

        softAssert.assertEquals(actualCols, expectedImage[0].length,
                String.format("The expected width for a J-shaped block should be %d, but was %d.",
                        expectedImage[0].length, actualCols));

        softAssert.assertAll("Shall create J block with correct dimensions, but did not");
    }

    @Test(dataProvider = "dotsForJBlock")
    public void shallCreateJBlockWithJShapedDots(int row, int col) {
        //given
        Block jBlock = new JBlock();
        int dotMark = 1;

        //when
        byte actualDot = jBlock.dotAt(row, col);

        //then
        assertEquals(actualDot, dotMark, "Shall create J block with correct shaped dots, but did not");
    }

    @Test(dataProvider = "emptySpacesForJBlock")
    public void shallCreateJBlockWithCorrectEmptySpaces(int row, int col) {
        //given
        Block jBlock = new JBlock();
        int emptyMark = 0;

        //when
        byte actualEmptySpace = jBlock.dotAt(row, col);

        //then
        assertEquals(actualEmptySpace, emptyMark, "Shall create J block with correct shaped empty marks, but did not");
    }

    @DataProvider()
    public static Object[] dotsForJBlock() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {2, 0},
                {2, 1},
        };
    }

    @DataProvider()
    public static Object[] emptySpacesForJBlock() {
        return new Object[][]{
                {0, 0},
                {1, 0},
        };
    }
}
