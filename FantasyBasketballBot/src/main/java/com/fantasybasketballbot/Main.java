package com.fantasybasketballbot;

import com.fantasybasketballbot.model.Player;
import com.fantasybasketballbot.model.StatsForGame;
import com.fantasybasketballbot.ml.MLModel;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Player> trainingPlayers = new ArrayList<>();
    private static MLModel model;

    public static void main(String[] args) {
        // Initialize training players
        initializeTrainingPlayers();

        // Train the model
        System.out.println("Training the model with hardcoded player data...");
        model = new MLModel(trainingPlayers);
        model.train();

        // Command-line interface
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select two players to compare their fantasy points in the next game:");

        // Display player list
        for (int i = 0; i < trainingPlayers.size(); i++) {
            Player player = trainingPlayers.get(i);
            System.out.println((i + 1) + ". " + player.getPlayerName());
        }

        // Get user input
        System.out.print("Enter the number of the first player: ");
        int firstPlayerIndex = scanner.nextInt() - 1;
        System.out.print("Enter the number of the second player: ");
        int secondPlayerIndex = scanner.nextInt() - 1;

        // Compare players
        Player player1 = trainingPlayers.get(firstPlayerIndex);
        Player player2 = trainingPlayers.get(secondPlayerIndex);
        Player betterPlayer = model.comparePlayers(player1, player2);

        // Output result
        System.out.println("Based on the model, the better player for the next game is: " + betterPlayer.getPlayerName());
    }

    private static void initializeTrainingPlayers() {
        trainingPlayers.add(new Player("001", "John Doe", createStatsForJohnDoe()));
        trainingPlayers.add(new Player("002", "Jane Smith", createStatsForJaneSmith()));
        trainingPlayers.add(new Player("003", "Mike Johnson", createStatsForMikeJohnson()));
        trainingPlayers.add(new Player("004", "Emily Davis", createStatsForEmilyDavis()));
        trainingPlayers.add(new Player("005", "Chris Brown", createStatsForChrisBrown()));
        trainingPlayers.add(new Player("006", "Sarah Wilson", createStatsForSarahWilson()));
        trainingPlayers.add(new Player("007", "David Lee", createStatsForDavidLee()));
        trainingPlayers.add(new Player("008", "Anna Taylor", createStatsForAnnaTaylor()));
        trainingPlayers.add(new Player("009", "James White", createStatsForJamesWhite()));
        trainingPlayers.add(new Player("010", "Laura Green", createStatsForLauraGreen()));
    }

    private static ArrayList<StatsForGame> createStatsForJohnDoe() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Detroit Pistons", 38, 11, 22, 0, 10, 12, 11, 15, 2, 1, 0, 3, 32, 50));
        stats.add(new StatsForGame("2025-04-10", "New Orleans Pelicans", 26, 11, 17, 1, 5, 6, 11, 5, 1, 0, 1, 0, 28, 45));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForJaneSmith() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Miami Heat", 40, 12, 20, 2, 8, 10, 10, 12, 3, 2, 1, 4, 35, 55));
        stats.add(new StatsForGame("2025-04-10", "Chicago Bulls", 30, 10, 18, 1, 6, 8, 9, 7, 2, 1, 2, 3, 29, 48));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForMikeJohnson() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Philadelphia 76ers", 35, 9, 16, 3, 7, 9, 12, 10, 1, 0, 1, 2, 30, 52));
        stats.add(new StatsForGame("2025-04-10", "Minnesota Timberwolves", 28, 8, 14, 2, 5, 7, 8, 6, 2, 1, 0, 1, 25, 47));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForEmilyDavis() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Boston Celtics", 42, 13, 22, 1, 9, 11, 14, 11, 3, 2, 0, 3, 37, 60));
        stats.add(new StatsForGame("2025-04-10", "Brooklyn Nets", 36, 11, 19, 2, 7, 9, 12, 9, 2, 1, 1, 2, 33, 54));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForChrisBrown() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Golden State Warriors", 38, 10, 18, 3, 6, 8, 11, 8, 2, 1, 1, 3, 32, 50));
        stats.add(new StatsForGame("2025-04-10", "Los Angeles Lakers", 34, 9, 17, 2, 5, 7, 10, 7, 1, 0, 2, 2, 30, 48));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForSarahWilson() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Toronto Raptors", 39, 12, 21, 1, 8, 10, 13, 10, 3, 2, 1, 4, 36, 58));
        stats.add(new StatsForGame("2025-04-10", "Houston Rockets", 32, 10, 18, 2, 7, 9, 11, 8, 2, 1, 2, 3, 31, 50));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForDavidLee() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Dallas Mavericks", 37, 11, 20, 2, 7, 9, 12, 9, 2, 1, 1, 3, 33, 52));
        stats.add(new StatsForGame("2025-04-10", "Phoenix Suns", 33, 10, 19, 1, 6, 8, 10, 8, 1, 0, 2, 2, 30, 50));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForAnnaTaylor() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Sacramento Kings", 40, 13, 23, 3, 9, 11, 15, 12, 3, 2, 0, 4, 38, 62));
        stats.add(new StatsForGame("2025-04-10", "Portland Trail Blazers", 35, 11, 20, 2, 8, 10, 13, 10, 2, 1, 1, 3, 34, 56));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForJamesWhite() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Atlanta Hawks", 38, 12, 21, 2, 8, 10, 14, 11, 3, 2, 1, 3, 36, 58));
        stats.add(new StatsForGame("2025-04-10", "Orlando Magic", 33, 10, 19, 1, 7, 9, 12, 9, 2, 1, 2, 2, 31, 50));
        return stats;
    }

    private static ArrayList<StatsForGame> createStatsForLauraGreen() {
        ArrayList<StatsForGame> stats = new ArrayList<>();
        stats.add(new StatsForGame("2025-04-11", "Utah Jazz", 41, 14, 24, 3, 10, 12, 16, 13, 4, 3, 0, 4, 40, 65));
        stats.add(new StatsForGame("2025-04-10", "Denver Nuggets", 36, 12, 21, 2, 9, 11, 14, 11, 3, 2, 1, 3, 37, 60));
        return stats;
    }
}