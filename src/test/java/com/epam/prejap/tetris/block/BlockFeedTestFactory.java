package com.epam.prejap.tetris.block;

import org.testng.annotations.Factory;

/**
 *  Performing the same tests for each new block causes code duplication. To avoid this, a method has been created
 *  that supplies {@link BlockFeedTest} class with concrete types of a {@link Block} class.
 * <p>
 * Thanks to this structure, each test from the BlockFeedTest class will be performed for each type provided
 * by the {@link #factoryMethod()} below.
 * </p><p>
 * Adding tests for a new type of Block requires adding a new type in the method below.
 * </p><p>
 * Sample implementation:<br>
 * <pre>{@code new BlockFeedTest(<NEW_BLOCK_CLASS_NAME>.class) }</pre>
 *
 * @author Łukasz Bulczak
 * @see Block
 * @see BlockFeedTest
 */
public class BlockFeedTestFactory {
    @Factory
    public Object[] factoryMethod() {
        return new Object[]{
                new BlockFeedTest(LBlock.class),
                new BlockFeedTest(OBlock.class),
                new BlockFeedTest(JBlock.class),
                new BlockFeedTest(IBlock.class),
                new BlockFeedTest(SBlock.class)
        };
    }
}
