package jp.houlab.Mochidsuki.chestReborn;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.loot.LootTable;

/**
 * イベントリスナー
 * @author Mochidsuki
 */
public class Listener implements org.bukkit.event.Listener {
    /**
     * チェストのルートテーブルをバックアップする
     * @param event イベント
     */
    @EventHandler
    public void onClickChest(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(event.getClickedBlock().getType() == Material.CHEST){
                Chest chest = (Chest) event.getClickedBlock().getState();
                LootTable lootTable = chest.getLootTable();
                if(lootTable != null) {
                    ChestControl.chests.put(event.getClickedBlock(), lootTable);
                }
            }
        }
    }
}
