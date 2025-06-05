package com.fantasybasketballbot.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fantasybasketballbot.model.Team;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "rosters.json";
    private static final Gson gson = new Gson();

    // Save rosters to JSON file
    public static void saveRosters(ArrayList<Team> rosters) {
        try (Writer writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(rosters, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load rosters from JSON file
    public static ArrayList<Team> loadRosters() {
        try (Reader reader = new FileReader(JSON_FILE_PATH)) {
            Type rosterListType = new TypeToken<ArrayList<Team>>() {}.getType();
            return gson.fromJson(reader, rosterListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}



/*
 * example json file:
 *[
 *  {
 *    "teamName": "My Team",
 *    "players": [
 *      {
 *        "playerId": "001",
 *        "playerName": "John Doe",
 *        "games": [
 *          {
 *            "date": "2025-04-11",
 *            "opponent": "Detroit Pistons",
 *            "minutes": 38,
 *            "fgm": 11,
 *            "fga": 22,
 *            "threePm": 0,
 *            "ftm": 10,
 *            "fta": 12,
 *            "reb": 11,
 *            "ast": 15,
 *            "stl": 2,
 *            "blk": 1,
 *            "pf": 0,
 *            "to": 3,
 *            "pts": 32,
 *            "fpts": 50
 *          }
 *        ]
 *      }
 *    ]
 *  }
 *]
 */