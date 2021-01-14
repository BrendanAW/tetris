package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Color;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "Timer")
public class PrinterTest {

    private ByteArrayOutputStream bos;
    private final byte[][] emptyGrid = new byte[][]{new byte[]{}};

    @BeforeMethod
    public void setUp() {
        bos = new ByteArrayOutputStream();
    }

    @DataProvider
    public Object[][] validTimerDurations() {
        return new Object[][]{{500, 14, "Time: 00:00:07"},
                {1000, 167, "Time: 00:02:47"},
                {1000, 4224, "Time: 01:10:24"},
                {3600000, 25, "Time: 25:00:00"},
                {1000, 25, "Time: 00:00:25"},
                {1000, 84, "Time: 00:01:24"},
                {1000, 5840, "Time: 01:37:20"},
                {3600000, 26, "Time: 26:00:00"}};
    }

    @Test(dataProvider = "validTimerDurations")
    public void drawShouldPrintValidHeader(int tickDurationInMillis, int cycles, String message) {
        // given
        Timer timer = new Timer(tickDurationInMillis);
        Printer printer = Mockito.spy(new Printer(new PrintStream(bos), timer));
        for (int i = 0; i < cycles; i++) {
            timer.tick();
        }
        // when
        printer.draw(emptyGrid);
        // then
        Mockito.verify(printer).header();
        assertTrue(bos.toString().contains(message));
    }



    @Test(groups = "Color", dataProvider = "colors")
    public void checkIfPrintMethodPrintsStringWithAppropriateColor(Color color) {
        // given
        Timer timer = Mockito.mock(Timer.class);
        Printer printer = Mockito.spy(new Printer(new PrintStream(bos), timer));
        int ansiCode = color.getAnsiCode();
        String escape =  "\u001B[";
        String finalByte = "m";
        String resetColor = escape + "0" + finalByte;
        String blockMark = "#";
        String expected = escape + ansiCode + finalByte + blockMark + resetColor;

        // when
        printer.print(color.getId());

        // then
        assertEquals(bos.toString(), expected);
    }


    @DataProvider
    private Object[] colors() {
        return Color.values();
    }
}
