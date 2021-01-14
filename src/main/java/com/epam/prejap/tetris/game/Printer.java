package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.block.Color;

import java.io.PrintStream;
import java.time.Duration;

public class Printer {

    private static final String TIME_FORMAT = "%02d:%02d:%02d";
    private static final String BLOCK_MARK = "#";
    final PrintStream out;
    private final Timer timer;

    public Printer(PrintStream out, Timer timer) {
        this.out = out;
        this.timer = timer;
    }

    void draw(byte[][] grid) {
        clear();
        header();
        border(grid[0].length);
        for (byte[] bytes : grid) {
            startRow();
            for (byte aByte : bytes) {
                print(aByte);
            }
            endRow();
        }
        border(grid[0].length);
    }

    void clear() {
        out.print("\u001b[2J\u001b[H");
    }

    /**
     * Print block mark with appropriate color, leave
     * uncoloured empty string in case of zero in game's grid
     *
     * @param colorId   id of specific Color enumeration constant
     * @since           0.8
     * @see             Color
     */
    void print(byte colorId) {
        String colored = Color.of(colorId).applyFor(BLOCK_MARK);
        out.format(colorId == 0 ? " " : colored);
    }

    void startRow() {
        out.print("|");
    }

    void endRow() {
        out.println("|");
    }

    void border(int width) {
        out.println("+" + "-".repeat(width) + "+");
    }

    /**
     * Prints elapsed time in hh:mm:ss format.
     */
    void header() {
        Duration duration = timer.calculateElapsedDuration();
        String elapsedTime = String.format(TIME_FORMAT, duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        out.println("Time: " + elapsedTime);
    }
}
