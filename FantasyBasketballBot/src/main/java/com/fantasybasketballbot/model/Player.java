package com.fantasybasketballbot.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String playerId;
    private String playerName;
    private ArrayList<StatsForGame> games; // List of all games
    private ArrayList<Double> seasonAVG_2425; // List of season averages for each stat

    // Static map to store players by their ID
    private static HashMap<String, Player> players = new HashMap<>();

    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.games = new ArrayList<>();
        this.seasonAVG_2425 = new ArrayList<>();
        players.put(playerId, this); // Add the player to the HashMap
    }

    // Constructor to initialize with season averages
    public Player(String playerId, String playerName, ArrayList<Double> seasonAVG_2425) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.games = new ArrayList<>();
        this.seasonAVG_2425 = seasonAVG_2425;
        players.put(playerId, this); // Add the player to the HashMap
    }

    public void addStatsForGame(StatsForGame stats) {
        games.add(stats);
    }

    public void calculateSeasonAverages() {
        double[] totals = new double[14]; // Array to accumulate totals for each stat
        int gameCount = games.size();

        for (StatsForGame game : games) {
            totals[0] += game.getMinutes();
            totals[1] += game.getFgm();
            totals[2] += game.getFga();
            totals[3] += game.getThreePm();
            totals[4] += game.getFtm();
            totals[5] += game.getFta();
            totals[6] += game.getReb();
            totals[7] += game.getAst();
            totals[8] += game.getStl();
            totals[9] += game.getBlk();
            totals[10] += game.getPf();
            totals[11] += game.getTo();
            totals[12] += game.getPts();
            totals[13] += game.getFpts();
        }

        // Calculate averages and store them in seasonAVG_2425
        seasonAVG_2425.clear();
        for (double total : totals) {
            seasonAVG_2425.add(total / gameCount);
        }
    }

    public ArrayList<Double> getSeasonAVG_2425() {
        return seasonAVG_2425;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<StatsForGame> getGames() {
        return games;
    }

    // Static method to retrieve or create a player by ID
    public static Player getOrCreatePlayer(String playerId, String playerName) {
        Player player = players.get(playerId);
        if (player == null) {
            player = new Player(playerId, playerName);
        }
        return player;
    }

    // Static method to get all players
    public static HashMap<String, Player> getAllPlayers() {
        return players;
    }
}
