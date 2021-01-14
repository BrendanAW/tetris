package com.epam.prejap.tetris.block;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

@Test(groups = "Color")
public class ColorTest {

    @Test(dataProvider = "colors")
    public void checkIfApplyForMethodReturnStringWithAppropriateColor(Color color) {
        //given
        int ansiCode = color.getAnsiCode();
        String escape =  "\u001B[";
        String finalByte = "m";
        String resetColor = escape + "0" + finalByte;
        String blockMark = "#";
        String expected = escape + ansiCode + finalByte + blockMark + resetColor;

        //when
        String actual = color.applyFor(blockMark);

        //then
        assertEquals(actual, expected);
    }

    @DataProvider
    private Object[] colors() {
        return Color.values();
    }
}
