package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void shouldAddMatchesAndSortByTotalScore() {
        Match match1 = scoreboard.addMatch("Croatia", "Germany");
        Match match2 = scoreboard.addMatch("Brazil", "Argentina");

        match1.updateScore(3, 2);
        match2.updateScore(1, 4);

        List<Match> sortedMatches = scoreboard.getSortedMatches();

        assertEquals("Brazil", sortedMatches.get(0).getHomeTeam());
        assertEquals("Croatia", sortedMatches.get(1).getHomeTeam());
    }

    @Test
    void shouldRemoveMatchAfterItEnds() {
        Match match = scoreboard.addMatch("Spain", "France");
        match.endMatch();

        boolean removed = scoreboard.removeMatch(match.getId());

        assertTrue(removed);
        assertFalse(scoreboard.getSortedMatches().contains(match));
    }

    @Test
    void shouldNotRemoveOngoingMatch() {
        Match match = scoreboard.addMatch("Italy", "Portugal");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> scoreboard.removeMatch(match.getId()));
        assertEquals("Match not found or still ongoing!", exception.getMessage(), "Should throw exception when trying to remove an ongoing match");
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonexistentMatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> scoreboard.removeMatch("invalid-id"));
        assertEquals("Match not found or still ongoing!", exception.getMessage());
    }

    @Test
    void shouldRetrieveMatchById() {
        Match match = scoreboard.addMatch("England", "Netherlands");

        Match retrievedMatch = scoreboard.getMatchById(match.getId());

        assertNotNull(retrievedMatch);
        assertEquals("England", retrievedMatch.getHomeTeam());
        assertEquals("Netherlands", retrievedMatch.getAwayTeam());
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchesExist() {
        List<Match> sortedMatches = scoreboard.getSortedMatches();

        assertTrue(sortedMatches.isEmpty());
    }
}