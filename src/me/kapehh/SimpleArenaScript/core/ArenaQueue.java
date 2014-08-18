package me.kapehh.SimpleArenaScript.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karen on 10.08.2014.
 */
public class ArenaQueue {
    private String name;
    private Location locationIn;
    private Location locationLobby;
    private List<Location> spawnLocations = new ArrayList<Location>();
    private int needPlayers;
    private List<Player> playerList = new ArrayList<Player>();

    public ArenaQueue(String name, Location locationIn, Location locationLobby, List<Location> spawnLocations, int needPlayers) {
        this.name = name;
        this.locationIn = locationIn;
        this.locationLobby = locationLobby;
        this.spawnLocations = spawnLocations;
        this.needPlayers = needPlayers;
    }

    public boolean playerInQueue(Player player) {
        return playerList.contains(player);
    }

    public void addPlayerToQueue(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
            player.teleport(locationLobby);
        }
    }

    public void removePlayerToQueue(Player player) {
        playerList.remove(player);
        player.teleport(locationIn);
    }
}
