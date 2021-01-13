package com.epam.prejap.tetris.game;

import java.util.Arrays;

/**
 * Stores moves available in the game with the keys that are used to perform each move.
 */
public enum Move {

    NONE(' '),
    LEFT('h'),
    RIGHT('l'),
    ;

    private int key;
    private final char defaultKey;

    public static final char[] DEFAULT_KEYS = new char[]{NONE.defaultKey, LEFT.defaultKey, RIGHT.defaultKey};
    private static char[] currentKeys = DEFAULT_KEYS;

    Move(char defaultKey) {
        this.defaultKey = defaultKey;
        this.key = defaultKey;
    }

    /**
     * Provides a Move based on the input key.
     * @param key   integer value of a key
     * @return the move associated with a provided key or NONE if no such key was found
     */
    public static Move of(int key) {
        for (var move : values()) {
            if (move.key == key) {
                return move;
            }
        }
        return NONE;
    }

    /**
     * Modifies navigation keys.
     * Replaces default setting with custom navigation keys.
     * @param keys  custom configuration
     * @return      modified navigation keys
     */
    public static char[] modifyNavigationKeys(char[] keys) {
        Arrays.stream(values()).forEach(val -> val.key = keys[val.ordinal()]);
        currentKeys = keys;
        return currentKeys;
    }

    /**
     * Provides current configuration of navigation keys.
     * @return string representation of moves and associated keys
     */
    public static String navigationKeysConfiguration() {
        return Arrays.deepToString(Arrays.stream(values()).map(Move::toString).toArray());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + (char) this.key;
    }
}
