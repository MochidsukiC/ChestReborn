package jp.houlab.Mochidsuki.chestReborn;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;

/**
 * メインクラス
 */
public final class Main extends JavaPlugin {
    static public Plugin plugin;

    /**
     * 起動時の初期化処理
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("chestreborn").setExecutor(new CommandListener());
        plugin = this;

        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    /**
     * 終了
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
