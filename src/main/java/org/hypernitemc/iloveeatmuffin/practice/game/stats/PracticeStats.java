package org.hypernitemc.iloveeatmuffin.practice.game.stats;

import com.ericlam.mc.minigames.core.gamestats.GameStatsEditor;

public class PracticeStats implements GameStatsEditor {

    private int kills;
    private int deaths;
    private int played;
    private int wins;

    public PracticeStats(int kills, int deaths, int played, int wins) {
        this.kills = kills;
        this.deaths = deaths;
        this.played = played;
        this.wins = wins;
    }

    public PracticeStats() {
        this(0, 0, 0, 0);
    }


    @Override
    public void setKills(int i) {
        this.kills = i;

    }

    @Override
    public void setDeaths(int i) {
        this.deaths = i;

    }

    @Override
    public void setPlayed(int i) {
        this.kills = i;

    }

    @Override
    public void setWins(int i) {
        this.wins = i;
    }

    @Override
    public void setScores(double scores) {

    }

    @Override
    public int getPlayed() {
        return played;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public int getDeaths() {
        return deaths;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public double getScores() {
        return kills * 3 - deaths * 2 + wins * 5; //分數不知道怎麼計好ewe
    }

    @Override
    public String[] getInfo() {
        return new String[]{
                "&d殺數: &f".concat(kills + ""),
                "&d勝數: &f".concat(wins + ""),
                "&d死亡: &f".concat(deaths + ""),
                "&d遊玩: &f".concat(played + ""),
                "&a分數: &f".concat(getScores() + "")
        };
    }
}
