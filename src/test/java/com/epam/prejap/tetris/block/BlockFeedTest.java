package com.epam.prejap.tetris.block;

import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "Block")
public class BlockFeedTest {

    @Test
    public void shallContainLBlock() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        boolean containsLBlock = feed.blocks()
                .stream()
                .map(Supplier::get)
                .anyMatch(e -> e instanceof LBlock);

        //then
        assertTrue(containsLBlock);
    }

    @Test(dependsOnMethods = "shallContainLBlock")
    public void shallContainOnlyOneLBlock() {
        //given
        BlockFeed feed = new BlockFeed();

        //when
        List<Block> blocks = feed.blocks()
                .stream()
                .map(Supplier::get)
                .filter(e -> e instanceof LBlock)
                .collect(Collectors.toList());

        //then
        assertEquals(blocks.size(), 1);
    }
}