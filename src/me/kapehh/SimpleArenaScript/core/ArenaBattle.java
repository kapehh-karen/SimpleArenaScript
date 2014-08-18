package me.kapehh.SimpleArenaScript.core;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karen on 10.08.2014.
 */
public class ArenaBattle {
    private int configTime;

    private List<Player> players = new ArrayList<Player>();
    private int timeLeft;

    public ArenaBattle(int configTime) {
        this.configTime = configTime;
        reset();
    }

    public void reset() {
        timeLeft = configTime;
        players.clear();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean playerInBattle(Player player) {
        return players.contains(player);
    }

    public boolean inArena(Player player) {
        return players.contains(player);
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void downTime() {
        timeLeft--;
    }

    public boolean hasTime() {
        return timeLeft > 0;
    }
}
