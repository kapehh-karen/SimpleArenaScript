package me.kapehh.SimpleArenaScript.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karen on 10.08.2014.
 */
public class ArenaBattle {
    private ArenaQueue arenaQueue;
    private int configTime;

    private List<Player> players = new ArrayList<Player>();
    private int timeLeft;

    public ArenaBattle(ArenaQueue arenaQueue, int configTime) {
        this.arenaQueue = arenaQueue;
        this.configTime = configTime;
        this.timeLeft = configTime;
    }

    public boolean isRunning() {
        return players.size() > 0;
    }

    public void reset() {
        for (Player player : players) {
            player.teleport(arenaQueue.getLocationIn());
        }

        timeLeft = configTime;
        players.clear();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void invitePlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.teleport(arenaQueue.generatePlayerSpawn());
        }
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
