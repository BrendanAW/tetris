package com.epam.prejap.tetris;

import com.epam.prejap.tetris.game.Move;

/**
 * Analyses the command-line input.
 * @author Martyna Rucińska Pereira
 */
class CommandLineAnalyst {

    /**
     * Analyses the first string input from the command line.
     * Checks if custom configuration for navigation keys was provided, if so, modifies them.
     *
     * @param arg0  first string input from the command line
     * @return      navigation keys
     */
    public static char[] checkArgsForNavigationKeys(String arg0) {
        if (arg0 == null || arg0.isBlank()) {
            return Move.DEFAULT_KEYS;
        }
        char[] providedKeys = transformArgToKeys(arg0);
        char[] customKeys = Move.modifyNavigationKeys(providedKeys);
        System.out.println("Custom navigation keys configured with success: \n" +
            Move.navigationKeysConfiguration());
        return customKeys;
    }

    private static char[] transformArgToKeys(String arg0) {
        String[] args = arg0.replaceAll("\\s+", " ").trim().split(" ");
        final int numberOfNavigationKeys = Move.values().length;
        if (args.length != numberOfNavigationKeys)
            throw new IllegalArgumentException("Incorrect amount of values provided for navigation keys.");
        char[] keys = arg0.replaceAll("\\s", "").toCharArray();
        if (keys.length != numberOfNavigationKeys)
            throw new IllegalArgumentException("Each navigation key should be represented by a single character.");
        if (!distinctValuesForNavigationKeys(keys))
            throw new IllegalArgumentException("Each navigation key should be represented by a different character.");
        return keys;
    }

    private static boolean distinctValuesForNavigationKeys(char[] keys) {
        String keysAsString = new String(keys);
        long distinctCount = keysAsString.chars().distinct().count();
        return keys.length == distinctCount;
    }
}
