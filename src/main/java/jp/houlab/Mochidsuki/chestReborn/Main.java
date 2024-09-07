package jp.houlab.Mochidsuki.chestReborn;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

public final class Main extends JavaPlugin {
    static public Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("chestreborn").setExecutor(new CommandListener());
        plugin = this;

        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
