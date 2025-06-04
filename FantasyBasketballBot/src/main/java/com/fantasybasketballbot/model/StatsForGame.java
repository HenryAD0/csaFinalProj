package com.fantasybasketballbot.model;

public class StatsForGame {
    private String playerId;
    private String date;
    private String homeAway;
    private String opponent;
    private String gpGs; // Games Played - Games Started
    private String minutes;
    private String fgmFga; // Field Goals Made - Field Goals Attempted
    private double fgPct; // Field Goal Percentage
    private String threePmPa; // Three Pointers Made - Three Pointers Attempted
    private double threePct; // Three Point Percentage
    private String ftmFta; // Free Throws Made - Free Throws Attempted
    private double ftPct; // Free Throw Percentage
    private int offReb; // Offensive Rebounds
    private int defReb; // Defensive Rebounds
    private String totReb; // Total Rebounds + Average
    private int ast; // Assists
    private String pfDq; // Personal Fouls - Disqualifications
    private int stl; // Steals
    private int to; // Turnovers
    private int blk; // Blocks
    private String pts; // Points + Average

    public StatsForGame(String playerId, String date, String homeAway, String opponent, String gpGs, String minutes,
                        String fgmFga, double fgPct, String threePmPa, double threePct, String ftmFta, double ftPct,
                        int offReb, int defReb, String totReb, int ast, String pfDq, int stl, int to, int blk,
                        String pts) {
        this.playerId = playerId;
        this.date = date;
        this.homeAway = homeAway;
        this.opponent = opponent;
        this.gpGs = gpGs;
        this.minutes = minutes;
        this.fgmFga = fgmFga;
        this.fgPct = fgPct;
        this.threePmPa = threePmPa;
        this.threePct = threePct;
        this.ftmFta = ftmFta;
        this.ftPct = ftPct;
        this.offReb = offReb;
        this.defReb = defReb;
        this.totReb = totReb;
        this.ast = ast;
        this.pfDq = pfDq;
        this.stl = stl;
        this.to = to;
        this.blk = blk;
        this.pts = pts;
    }

    // Getters and Setters for each field
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeAway() {
        return homeAway;
    }

    public void setHomeAway(String homeAway) {
        this.homeAway = homeAway;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getGpGs() {
        return gpGs;
    }

    public void setGpGs(String gpGs) {
        this.gpGs = gpGs;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getFgmFga() {
        return fgmFga;
    }

    public void setFgmFga(String fgmFga) {
        this.fgmFga = fgmFga;
    }

    public double getFgPct() {
        return fgPct;
    }

    public void setFgPct(double fgPct) {
        this.fgPct = fgPct;
    }

    public String getThreePmPa() {
        return threePmPa;
    }

    public void setThreePmPa(String threePmPa) {
        this.threePmPa = threePmPa;
    }

    public double getThreePct() {
        return threePct;
    }

    public void setThreePct(double threePct) {
        this.threePct = threePct;
    }

    public String getFtmFta() {
        return ftmFta;
    }

    public void setFtmFta(String ftmFta) {
        this.ftmFta = ftmFta;
    }

    public double getFtPct() {
        return ftPct;
    }

    public void setFtPct(double ftPct) {
        this.ftPct = ftPct;
    }

    public int getOffReb() {
        return offReb;
    }

    public void setOffReb(int offReb) {
        this.offReb = offReb;
    }

    public int getDefReb() {
        return defReb;
    }

    public void setDefReb(int defReb) {
        this.defReb = defReb;
    }

    public String getTotReb() {
        return totReb;
    }

    public void setTotReb(String totReb) {
        this.totReb = totReb;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public String getPfDq() {
        return pfDq;
    }

    public void setPfDq(String pfDq) {
        this.pfDq = pfDq;
    }

    public int getStl() {
        return stl;
    }

    public void setStl(int stl) {
        this.stl = stl;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getBlk() {
        return blk;
    }

    public void setBlk(int blk) {
        this.blk = blk;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }
}