package me.kapehh.SimpleArenaScript.core;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Karen on 18.08.2014.
 */
public class ArenaCore implements Listener, CommandExecutor {
    List<ArenaQueue> arenaQueues = new ArrayList<ArenaQueue>();

    public ArenaCore() {

    }

    private Player getPlayer(String name) {
        Player[] players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    private ArenaQueue getArenaQueue(String worldName) {
        for (ArenaQueue arenaQueue : arenaQueues) {
            if (arenaQueue.getWorldName().equalsIgnoreCase(worldName)) {
                return arenaQueue;
            }
        }
        return null;
    }

    private ArenaQueue getArenaQueue(World world) {
        if (world != null) {
            return getArenaQueue(world.getName());
        } else {
            return null;
        }
    }

    public void addPlayerToQueue(String arenaName, String playerName) {
        ArenaQueue arenaQueue = getArenaQueue(arenaName);
        Player player = getPlayer(playerName);
        if (arenaQueue != null && player != null) {
            arenaQueue.addPlayerToQueue(player);
        }
    }

    public void removePlayerFromQueue(String arenaName, String playerName) {
        ArenaQueue arenaQueue = getArenaQueue(arenaName);
        Player player = getPlayer(playerName);
        if (arenaQueue != null && player != null) {
            arenaQueue.removePlayerFromQueue(player);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        ArenaQueue arenaQueue = getArenaQueue(event.getEntity().getLocation().getWorld());
        if (arenaQueue != null && arenaQueue.playerInBattle(event.getEntity())) {
            arenaQueue.kickPlayerFromBattle(event.getEntity());
        }
        // Убираем игрока из Battle и пишем всем участникам в очереди ив батл что игрок выбыл
        // Если остался 1 игрок, то отправлять следующую партию
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        ArenaQueue arenaQueue = getArenaQueue(event.getPlayer().getLocation().getWorld());
        if (arenaQueue != null) {
            event.getPlayer().teleport(arenaQueue.getLocationIn());
        }
        // Телепортируем перед входом на арену
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        ArenaQueue arenaQueue = getArenaQueue(event.getFrom().getWorld());
        if (arenaQueue != null && arenaQueue.playerInBattle(event.getPlayer())) {
            if (!event.getTo().getWorld().equals(event.getFrom().getWorld())) {
                arenaQueue.kickPlayerFromBattle(event.getPlayer());
            }
        }
        // Если игрок телепортируется из арены в другое место вне арены
        // Убираем игрока из Battle и пишем всем участникам в очереди ив батл что игрок выбыл
        // Если остался 1 игрок, то отправляем следующую партию
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ArenaMessages.MESSAGE_NEED_PERMISSION);
            return false;
        }
        if (args.length < 3) {
            sender.sendMessage(ArenaMessages.MESSAGE_NEED_ARGS);
            return false;
        }
        String method = args[0];
        String arenaName = args[1];
        String playerName = args[2];
        if (method.equalsIgnoreCase("add")) {
            addPlayerToQueue(arenaName, playerName);
        } else if (method.equalsIgnoreCase("remove")) {
            removePlayerFromQueue(arenaName, playerName);
        }
        return true;
    }
}
