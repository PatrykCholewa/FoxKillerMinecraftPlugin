package com.github.PatrykCholewa.FoxKiller;

import com.github.PatrykCholewa.FoxKiller.listeners.FoxKillListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class FoxKillerPlugin extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    public FoxKillerPlugin() {

    }

    public FoxKillerPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new FoxKillListener(), instance);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
        HandlerList.unregisterAll(this);
    }

}
