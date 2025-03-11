package org.example;

public class Main {
    public static void main(String[] args) {
        Scoreboard scoreboard = new Scoreboard();

        // Adding matches
        Match match1 = scoreboard.addMatch("Croatia", "Germany");
        Match match2 = scoreboard.addMatch("Brazil", "Argentina");

        // Updating scores
        match1.updateScore(3, 2);
        match2.updateScore(1, 4);

        // Display matches sorted by total score
        System.out.println("\nMatches sorted by total score:");
        scoreboard.displaySortedMatches();

        // Ending and removing a match
        match1.endMatch();
        scoreboard.removeMatch(match1.getId());

        System.out.println("\nAfter removing ended match:");
        scoreboard.displaySortedMatches();
    }
}