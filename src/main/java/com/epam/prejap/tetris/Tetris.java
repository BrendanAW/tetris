package com.epam.prejap.tetris;

import com.epam.prejap.tetris.block.BlockFeed;
import com.epam.prejap.tetris.game.Move;
import com.epam.prejap.tetris.game.Playfield;
import com.epam.prejap.tetris.game.Printer;
import com.epam.prejap.tetris.game.Waiter;
import com.epam.prejap.tetris.player.Player;
import com.epam.prejap.tetris.player.RandomPlayer;

import java.util.Arrays;

class Tetris {

    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;

    public Tetris(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
    }

    public Score play() {
        boolean moved;
        int score = playfield.starterBlockAmt;
        do {
            moved = false;
            playfield.nextBlock();
            score++;

            boolean nextMove;
            do {
                waiter.waitForIt();
                Move move = player.nextMove().orElse(Move.NONE);
                moved |= (nextMove = playfield.move(move));
            } while (nextMove);

        } while (moved);

        return new Score(score);
    }

    public static void main(String[] args) {
        int rows = 10;
        int cols = 20;
        int delay = 500;
        var isStarterBlocks = Arrays.asList(args).contains("-RB") || Arrays.asList(args).contains("-rb");
        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer, isStarterBlocks);
        var game = new Tetris(playfield, new Waiter(delay), new RandomPlayer());

        var score = game.play();

        System.out.println("Score: " + score.points());
    }

}
