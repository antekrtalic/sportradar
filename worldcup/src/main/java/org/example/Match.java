package org.example;

import java.util.UUID;

public class Match {
    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private boolean isOngoing;

    public Match(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Team names cannot be null or empty.");
        }
        this.id = UUID.randomUUID().toString();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.isOngoing = true;
    }

    public String getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void updateScore(int homeScore, int awayScore) {
        if (!isOngoing) {
            throw new IllegalStateException("Match is already over!");
        }
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative.");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public void endMatch() {
        this.isOngoing = false;
    }

    public String getFormattedScore() {
        return homeTeam + " " + homeScore + " - " + awayScore + " " + awayTeam;
    }

    @Override
    public String toString() {
        return getFormattedScore();
    }
}
