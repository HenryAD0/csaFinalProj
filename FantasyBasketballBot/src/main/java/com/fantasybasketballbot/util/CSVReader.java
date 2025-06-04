package com.fantasybasketballbot.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fantasybasketballbot.model.Player;
import com.fantasybasketballbot.model.StatsForGame;

public class CSVReader {

    public static void readCSV(String filePath) {
        String line;
        String csvSplitBy = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length >= 18) { // Ensure there are enough columns
                    String playerId = data[0];
                    String date = data[1];
                    String homeAway = data[2];
                    String opponent = data[3];
                    String gpGs = data[4];
                    String minutes = data[5];
                    String fgmFga = data[6];
                    double fgPct = Double.parseDouble(data[7]);
                    String threePmPa = data[8];
                    double threePct = Double.parseDouble(data[9]);
                    String ftmFta = data[10];
                    double ftPct = Double.parseDouble(data[11]);
                    int offReb = Integer.parseInt(data[12]);
                    int defReb = Integer.parseInt(data[13]);
                    String totReb = data[14];
                    int ast = Integer.parseInt(data[15]);
                    String pfDq = data[16];
                    int stl = Integer.parseInt(data[17]);
                    int to = Integer.parseInt(data[18]);
                    int blk = Integer.parseInt(data[19]);
                    String pts = data[20];

                    StatsForGame stats = new StatsForGame(playerId, date, homeAway, opponent, gpGs, minutes, fgmFga, fgPct, threePmPa, threePct, ftmFta, ftPct, offReb, defReb, totReb, ast, pfDq, stl, to, blk, pts);
                    
                    // Assuming Player class has a method to add stats
                    Player.addStatsForPlayer(playerId, stats);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}