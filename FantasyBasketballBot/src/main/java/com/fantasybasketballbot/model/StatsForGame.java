package com.fantasybasketballbot.model;

public class StatsForGame {
    private String date;
    private String opponent;
    private int minutes;
    private int fgm;
    private int fga;
    private int threePm;
    private int ftm;
    private int fta;
    private int reb;
    private int ast;
    private int stl;
    private int blk;
    private int pf;
    private int to;
    private int pts;
    private int fpts;

    // Constructor with initialization
    public StatsForGame(String date, String opponent, int minutes, int fgm, int fga, int threePm, int ftm, int fta, int reb, int ast, int stl, int blk, int pf, int to, int pts, int fpts) {
        this.date = date;
        this.opponent = opponent;
        this.minutes = minutes;
        this.fgm = fgm;
        this.fga = fga;
        this.threePm = threePm;
        this.ftm = ftm;
        this.fta = fta;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.pf = pf;
        this.to = to;
        this.pts = pts;
        this.fpts = fpts;
    }

    // Getters for all fields (optional, if needed)
    public String getDate() {
        return date;
    }

    public String getOpponent() {
        return opponent;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getFgm() {
        return fgm;
    }

    public int getFga() {
        return fga;
    }

    public int getThreePm() {
        return threePm;
    }

    public int getFtm() {
        return ftm;
    }

    public int getFta() {
        return fta;
    }

    public int getReb() {
        return reb;
    }

    public int getAst() {
        return ast;
    }

    public int getStl() {
        return stl;
    }

    public int getBlk() {
        return blk;
    }

    public int getPf() {
        return pf;
    }

    public int getTo() {
        return to;
    }

    public int getPts() {
        return pts;
    }

    public int getFpts() {
        return fpts;
    }
}