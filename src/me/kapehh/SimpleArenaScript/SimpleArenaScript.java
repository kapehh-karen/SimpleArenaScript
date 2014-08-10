package me.kapehh.SimpleArenaScript;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 10.08.2014.
 */
public class SimpleArenaScript extends JavaPlugin {

    /*
    класс симпл арены имеет:
    название арены, координата входа, координата куда телепортирует, список координат дверей, требуемое количество игроков, очередь игроков записанных на арену.
    а в нем список битв:
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


    }
}
