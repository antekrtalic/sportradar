package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Match match;

    @BeforeEach
    void setUp() {
        match = new Match("Croatia", "Brazil");
    }

    @Test
    void shouldInitializeWithZeroScore() {
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void shouldUpdateScoreCorrectly() {
        match.updateScore(3, 2);
        assertEquals(3, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void shouldEndMatchCorrectly() {
        match.endMatch();
        assertFalse(match.isOngoing());
    }

    @Test
    void shouldNotUpdateScoreAfterMatchEnds() {
        match.endMatch();
        Exception exception = assertThrows(IllegalStateException.class, () -> match.updateScore(2, 1));
        assertEquals("Match is already over!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNegativeScore() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 2));
        assertEquals("Score cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidTeamNames() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Match("", "Germany"));
        assertEquals("Team names cannot be null or empty.", exception.getMessage());
    }
}