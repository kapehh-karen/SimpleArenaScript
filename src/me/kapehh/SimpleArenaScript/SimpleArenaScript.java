package me.kapehh.SimpleArenaScript;

import me.kapehh.SimpleArenaScript.core.ArenaCore;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 10.08.2014.
 */
public class SimpleArenaScript extends JavaPlugin {

    /*
    класс симпл арены имеет:
    название арены, координата входа, координата лобби (игроки в лобби имеют год мод),
    координаты спауна на арене, требуемое количество игроков, очередь игроков записанных на арену.

    а в нем битва:
    список игроков кто записан в битве
    текущее время которое осталось до начала
    поле отвечающее на вопрос "на арене ли игроки, или ещё в очереди"

    вышеописанный класс будет находиться в очереди для этой арены

    при выходе игроков которые "на арене" их телепортирует в точку входа для арены
     */

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        ArenaCore arenaCore = new ArenaCore();
        getServer().getPluginManager().registerEvents(arenaCore, this);
        getCommand("arenascript").setExecutor(arenaCore);
    }
}
