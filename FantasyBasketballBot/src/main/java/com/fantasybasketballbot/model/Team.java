package com.fantasybasketballbot.model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> players;

    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public int getPlayerCount() {
        return players.size();
    }
}