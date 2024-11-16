package com.github.PatrykCholewa.FoxKiller;

import com.github.PatrykCholewa.FoxKiller.listeners.CurseOfAnchoringEnchantListener;
import com.github.PatrykCholewa.FoxKiller.listeners.FoxKillListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FoxKillerPlugin extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new FoxKillListener(), instance);
        pluginManager.registerEvents(new CurseOfAnchoringEnchantListener(), instance);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
        HandlerList.unregisterAll(this);
    }

}
