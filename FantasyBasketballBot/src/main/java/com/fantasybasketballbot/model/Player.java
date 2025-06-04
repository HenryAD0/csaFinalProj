package com.fantasybasketballbot.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String playerId;
    private String playerName;
    public static ArrayList<StatsForGame> seasonSFG_2425 = new ArrayList<>();
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