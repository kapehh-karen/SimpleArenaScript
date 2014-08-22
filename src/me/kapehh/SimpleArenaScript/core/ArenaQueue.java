package me.kapehh.SimpleArenaScript.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karen on 10.08.2014.
 */
public class ArenaQueue {
    private ArenaBattle arenaBattle;
    private String worldName;
    private Location locationIn;
    private Location locationLobby;
    private List<Location> spawnLocations = new ArrayList<Location>();
    private int needPlayers;
    private List<Player> playerList = new ArrayList<Player>();

    public ArenaQueue(String worldName, Location locationIn, Location locationLobby, List<Location> spawnLocations, int needPlayers) {
        this.worldName = worldName;
        this.locationIn = locationIn;
        this.locationLobby = locationLobby;
        this.spawnLocations = spawnLocations;
        this.needPlayers = needPlayers;
        this.arenaBattle = new ArenaBattle(this);
    }

    public void sendPlayersToArena() {
        if (arenaBattle.isRunning()) {
            return;
        }
        if (needPlayers <= playerList.size()) {
            // TODO: Print all players in Battle, who entered to battle
            for (int i = 0; i < needPlayers; i++) {
                Player player = playerList.get(0);
                arenaBattle.invitePlayer(player);
                playerList.remove(0);
            }
        }
    }

    public void checkPlayerInArena() {
        if (arenaBattle.isRunning() && arenaBattle.isSinglePlayer()) {
            // TODO: Print all player in Battle, who winner
            arenaBattle.reset();
            sendPlayersToArena();
        }
    }

    public void kickPlayerFromBattle(Player player) {
        arenaBattle.kickPlayer(player);
        checkPlayerInArena();
    }

    public boolean playerInBattle(Player player) {
        return arenaBattle.inArena(player);
    }

    public String getWorldName() {
        return worldName;
    }

    public int getNeedPlayers() {
        return needPlayers;
    }

    public Location generatePlayerSpawn() {
        return spawnLocations.get(0); // TODO: Random spawn
    }

    public Location getLocationIn() {
        return locationIn;
    }

    public boolean playerInQueue(Player player) {
        return playerList.contains(player);
    }

    public void addPlayerToQueue(Player player) {
        if (!playerList.contains(player)) {
            playerList.add(player);
            player.teleport(locationLobby);
            sendPlayersToArena();
        }
    }

    public void removePlayerFromQueue(Player player) {
        if (playerList.contains(player)) {
            playerList.remove(player);
            player.teleport(locationIn);
            checkPlayerInArena();
        }
    }

    public void addSpawnLocation(Location location) {
        spawnLocations.add(location);
    }

    public void removeSpawnLocation(Location location) {
        spawnLocations.remove(location);
    }
}
