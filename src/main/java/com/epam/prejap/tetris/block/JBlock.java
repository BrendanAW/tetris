package com.epam.prejap.tetris.block;

/**
 * J-shaped block implementation of the {@link Block} abstract class.
 * <br>
 *
 * @author Łukasz Bulczak
 * @implNote This class implements static 2d array in order to create required "J" shape block.
 * @see Block
 */
final class JBlock extends Block {

    private static final byte[][] IMAGE = {
            {0, 1},
            {0, 1},
            {1, 1},
    };

    JBlock() {
        super(IMAGE);
    }
}
