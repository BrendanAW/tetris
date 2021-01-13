package com.epam.prejap.tetris.block;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.*;

@Test(groups = "BlockFeed")
public class BlockFeedTest {

    private final Class<? extends Block> blockChildClass;

    public BlockFeedTest(Class<? extends Block> blockChildClass) {
        this.blockChildClass = blockChildClass;
    }

    @Test
    public void shallContainSpecificBlock() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        boolean containsLBlock = feed.blocks()
                .stream()
                .map(Supplier::get)
                .anyMatch(blockChildClass::isInstance);

        //then
        assertTrue(containsLBlock);
    }

    @Test(dependsOnMethods = "shallContainSpecificBlock")
    public void shallContainOnlyOneBlockOfThatType() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        var numOfBlocks = feed.blocks()
                .stream()
                .map(Supplier::get)
                .filter(blockChildClass::isInstance)
                .count();

        //then
        assertEquals(numOfBlocks, 1);
    }
}
