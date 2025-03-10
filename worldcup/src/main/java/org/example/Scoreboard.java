package org.example;

import java.util.LinkedList;
import java.util.List;

public class Scoreboard {

    private final LinkedList<Match> ongoingMatches = new LinkedList<>();
    public Match addMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        ongoingMatches.add(match);

        return match;
    }

    public Match getMatchById(String matchId) {
        return ongoingMatches.stream()
                .filter(match -> match.getId().equals(matchId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Match not found!"));
    }

    public boolean removeMatch(String matchId) {
        Match currentMatch = ongoingMatches.stream()
                .filter(match -> match.getId().equals(matchId) && !match.isOngoing())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Match not found or still ongoing!"));

        return ongoingMatches.remove(currentMatch);
    }

    public List<Match> getSortedMatches() {
        return ongoingMatches.stream()
                .sorted((matchOne, matchTwo) -> {
                    int scoreOne = matchOne.getHomeScore() + matchOne.getAwayScore();
                    int scoreTwo = matchTwo.getHomeScore() + matchTwo.getAwayScore();

                    return (scoreTwo != scoreOne) ? Integer.compare(scoreTwo, scoreOne) : -1;
                })
                .toList();
    }

    public void displaySortedMatches() {
        getSortedMatches().forEach(System.out::println);
    }
}
