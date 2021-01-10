package com.epam.prejap.tetris.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Test(groups = "Timer")
public class TimerTest {

    @DataProvider
    public Object[][] validTimerDurations() {
        return new Object[][]{{500, 10, "PT5S"},
                {1_000, 60, "PT1M"},
                {1_000, 5840, "PT1H37M20S"},
                {60_000, 60, "PT1H"},
                {1_000, 254, "PT4M14S"}};
    }

    @DataProvider
    public Object[][] invalidDelays() {
        return new Object[][]{{-500},
                {0}};
    }

    @Test(dataProvider = "validTimerDurations")
    public void shouldReturnValidDuration(int tickDurationInMillis,
                                          int cycles,
                                          String durationResult) {
        // given
        Timer timer = new Timer(tickDurationInMillis);
        // when
        for (int i = 0; i < cycles; i++) {
            timer.tick();
        }
        // then
        assertEquals(timer.calculateElapsedDuration(), Duration.parse(durationResult));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "invalidDelays")
    public void shouldThrowIllegalArgumentExceptionForInvalidTickDuration(int tickDurationInMillis) {
        new Timer(tickDurationInMillis);
    }
}
