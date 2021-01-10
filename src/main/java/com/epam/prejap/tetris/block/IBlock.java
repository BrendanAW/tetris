package com.epam.prejap.tetris.block;

/**
 * I-shaped block implementation of the {@link Block} abstraction class.
 * <br>
 * @implNote This class implements static 2d array in order to create required "I" shape block
 * <br>
 *
 * @author Łukasz Prokop
 * @see Block
 */
final class IBlock extends Block {

    private static final byte[][] IMAGE = {
            {1},
            {1},
            {1},
            {1},
    };

    IBlock() {
        super(IMAGE);
    }

}
