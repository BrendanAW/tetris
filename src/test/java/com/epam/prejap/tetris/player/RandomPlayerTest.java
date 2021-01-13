package com.epam.prejap.tetris.player;

import com.epam.prejap.tetris.game.Move;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

@Test(groups = "RandomPlayer")
public class RandomPlayerTest {

    class MockRandom extends Random {
        private final int mockRandomInt;

        MockRandom(int mockRandomInt) {
            this.mockRandomInt = mockRandomInt;
        }

        @Override
        public int nextInt(int bound) {
            return mockRandomInt;
        }
    }

    @DataProvider
    public Object[] allMoveEnumValues() {
        return Move.values();
    }

    @Test(dataProvider = "allMoveEnumValues")
    public void nextMoveShouldGenerateEachPossibleValue(Move expectedMove) {
        Random mockRandom = new MockRandom(expectedMove.ordinal());
        RandomPlayer testingRandomizer = new RandomPlayer(mockRandom);

        var actualMove = testingRandomizer.nextMove().get();

        assertEquals(actualMove, expectedMove);
    }
}
