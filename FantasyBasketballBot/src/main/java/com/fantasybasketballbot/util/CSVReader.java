package com.fantasybasketballbot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.fantasybasketballbot.model.Player;
import com.fantasybasketballbot.model.StatsForGame;

public class CSVReader {

    public static void readCSV(String filePath) {
        String line;
        String csvSplitBy = ",";
        
        // Extract player ID from the file name
        String playerId = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
        String playerName = "Unknown"; // Default name if not provided

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header row
                    continue;
                }

                String[] data = line.split(csvSplitBy);
                if (data.length >= 15) { // Ensure there are enough columns for StatsForGame
                    String date = data[0];
                    String opponent = data[1];
                    int minutes = Integer.parseInt(data[2]);
                    int fgm = Integer.parseInt(data[3]);
                    int fga = Integer.parseInt(data[4]);
                    int threePm = Integer.parseInt(data[5]);
                    int ftm = Integer.parseInt(data[6]);
                    int fta = Integer.parseInt(data[7]);
                    int reb = Integer.parseInt(data[8]);
                    int ast = Integer.parseInt(data[9]);
                    int stl = Integer.parseInt(data[10]);
                    int blk = Integer.parseInt(data[11]);
                    int pf = Integer.parseInt(data[12]);
                    int to = Integer.parseInt(data[13]);
                    int pts = Integer.parseInt(data[14]);
                    int fpts = Integer.parseInt(data[15]);

                    // Retrieve or create the Player object
                    Player player = Player.getOrCreatePlayer(playerId, playerName);

                    // Add stats for the player
                    player.addStatsForGame(new StatsForGame(date, opponent, minutes, fgm, fga, threePm, ftm, fta, reb, ast, stl, blk, pf, to, pts, fpts));
                }
            }

            // Calculate averages for all players
            for (Player player : Player.getAllPlayers().values()) {
                player.calculateSeasonAverages();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}