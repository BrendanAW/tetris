package com.epam.prejap.tetris.block;

import java.util.Arrays;

/**
 * @author Dominik Janiga
 * @since 0.8
 */
public enum Color {

    BLACK(1, 30),
    RED(2, 31),
    GREEN(3, 32),
    YELLOW(4, 33),
    BLUE(5, 34),
    MAGENTA(6, 35),
    CYAN(7, 36),
    WHITE(8, 37);

    /**
     * Color identifiers from ANSI escape codes
     *
     * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI Escape codes</a>
     * @since 0.8
     */
    private final int id;
    private final int ansiCode;

    Color(int id, int ansiCode) {
        this.id =  id;
        this.ansiCode =  ansiCode;
    }

    public byte getId() {
        return (byte) id;
    }

    public int getAnsiCode() {
        return ansiCode;
    }

    /**
     * Iterate through the array of enum constants and return first if ID's match
     *
     * @param id        the id of specific enumeration constant
     * @return          an instance of enum constant if there is any, otherwise return Black constant
     * @since           0.8
     */
    public static Color of(int id) {
        return Arrays.stream(values())
                .filter(color -> color.id == id)
                .findFirst()
                .orElse(Color.BLACK);
    }


    /**
     * Set up color to string using Control Sequence Introducer from ANSI escape codes
     *
     * @param blockMark the mark that represent block on the game field
     * @return          painted block's mark string
     * @since           0.8
     * @see             <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI Escape codes</a>
     */
    public String applyFor(String blockMark) {
        String escape = "\u001B[";
        String finalByte = "m";
        String resetColor = escape + "0" + finalByte;
        return escape + ansiCode + finalByte + blockMark + resetColor;
    }
}
