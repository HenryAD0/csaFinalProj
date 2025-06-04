package com.fantasybasketballbot.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String playerId;
    private String playerName;
    public static ArrayList<StatsForGame> games = new ArrayList<>();
    private ArrayList<StatsForGame> statsForGames;

    // Static map to store players by their ID
    private static HashMap<String, Player> players = new HashMap<>();

    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        statsForGames = new ArrayList<>();
    }

    public Player() {
        statsForGames = new ArrayList<>();
    }

    public static void addStatsForPlayer(String playerId, StatsForGame stats) {
        Player player = players.get(playerId);
        if (player == null) {
            player = new Player(playerId, "Unknown"); // Default name if not provided
            players.put(playerId, player);
        }
        player.statsForGames.add(stats);
    }

    public void loadGamesFromCSV(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            boolean isFirstLine = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                StatsForGame game = new StatsForGame(
                    nextLine[0], // date
                    nextLine[1], // opponent
                    parseMinutes(nextLine[2]), // minutes as int
                    Integer.parseInt(nextLine[3]), // FGM
                    Integer.parseInt(nextLine[4]), // FGA
                    Integer.parseInt(nextLine[5]), // 3PM
                    Integer.parseInt(nextLine[6]), // FTM
                    Integer.parseInt(nextLine[7]), // FTA
                    Integer.parseInt(nextLine[8]), // REB
                    Integer.parseInt(nextLine[9]), // AST
                    Integer.parseInt(nextLine[10]), // STL
                    Integer.parseInt(nextLine[11]), // BLK
                    Integer.parseInt(nextLine[12]), // PF
                    Integer.parseInt(nextLine[13]), // TO
                    Integer.parseInt(nextLine[14]), // PTS
                    Integer.parseInt(nextLine[15])  // FPTS
                );

                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parseMinutes(String time) {
        try {
            String[] parts = time.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return minutes + (seconds >= 30 ? 1 : 0); // round up if 30+ sec
        } catch (Exception e) {
            return 0; // fallback
        }
    }
    
    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static ArrayList<StatsForGame> getSeasonSFG() {
        return seasonSFG_2425;
    }

    public static void addStatsForGame(StatsForGame stats) {
        seasonSFG_2425.add(stats);
    }
}
