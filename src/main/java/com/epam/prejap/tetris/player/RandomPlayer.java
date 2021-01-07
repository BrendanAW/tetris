package com.epam.prejap.tetris.player;

import com.epam.prejap.tetris.game.Move;

import java.util.Optional;
import java.util.Random;

public class RandomPlayer implements Player {

    private final Random random;

    public RandomPlayer(Random random) {
        this.random = random;
    }

    /**
     * Generates random move for Player from all available defined in Move enum
     *
     * @return Optional with Move value
     */
    @Override
    public Optional<Move> nextMove() {
        return Optional.of(Move.values()[random.nextInt(Move.values().length)]);
    }
}
