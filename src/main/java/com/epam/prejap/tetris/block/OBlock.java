package com.epam.prejap.tetris.block;

/**
 * O-shaped block implementation of the {@link Block} abstract class.
 * <br>
 *
 * @author Pawel Kierat
 * @implNote This class implements static 2d array in order to create required "O" shape block.
 * @see Block
 */
final class OBlock extends Block {

    private static final byte[][] IMAGE = {
        {1, 1},
        {1, 1},
    };

    public OBlock() {
        super(IMAGE, Color.BLUE);
    }

}
