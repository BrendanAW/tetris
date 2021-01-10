package com.epam.prejap.tetris.game;

import java.time.Duration;

/**
 * The Timer class is responsible for calculating elapsed time.
 * <p>
 * The class includes methods for counting ticks and calculating elapsed time.
 * @author Wiktoria Majchrzak
 */
public class Timer {

    private final Duration tickDuration;
    private int tickCount = 0;

    /**
     * Constructs a new object.
     * @param tickDurationInMillis tick delay in milliseconds
     * @throws IllegalArgumentException if the tickDurationInMillis is not a positive number
     */
    public Timer(int tickDurationInMillis) {
        if (tickDurationInMillis <= 0) {
            throw new IllegalArgumentException("Tick duration should be a positive number.");
        }
        this.tickDuration = Duration.ofMillis(tickDurationInMillis);
    }

    /**
     * Calculate duration by multiplying ticks and game delay.
     * @return elapsed duration
     */
    public Duration calculateElapsedDuration() {
        return tickDuration.multipliedBy(tickCount);
    }

    /**
     * Counts ticks by incrementing counter.
     */
    public void tick() {
        tickCount++;
    }
}
