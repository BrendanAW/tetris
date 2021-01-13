package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

@Test(groups = {"Block", "OBlock"})
public class OBlockTest {

    @Test
    public void shallCreateOBlockWithCorrectDimensions() {
        //given
        SoftAssert softAssert = new SoftAssert();
        Block oBlock = new OBlock();
        byte[][] expectedImage = new byte[2][2];

        //when
        int actualRows = oBlock.rows;
        int actualCols = oBlock.cols;

        //then
        softAssert.assertEquals(actualRows, expectedImage.length,
                String.format("The expected height for a O-shaped block should be %d, but was %d.",
                        expectedImage.length, actualRows));

        softAssert.assertEquals(actualCols, expectedImage[0].length,
                String.format("The expected width for a O-shaped block should be %d, but was %d.",
                        expectedImage[0].length, actualCols));

        softAssert.assertAll("Shall create O block with correct dimensions, but did not");
    }

    @Test(dataProvider = "dotsForOBlock")
    public void shallCreateOBlockWithOShapedDots(int row, int col) {
        //given
        Block oBlock = new OBlock();
        int dotMark = 1;

        //when
        byte actualDot = oBlock.dotAt(row, col);

        //then
        assertEquals(actualDot, dotMark, "Shall create O block with correct shaped dots, but did not");
    }

    @DataProvider()
    public static Object[] dotsForOBlock() {
        return new Object[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1},
        };
    }
}
