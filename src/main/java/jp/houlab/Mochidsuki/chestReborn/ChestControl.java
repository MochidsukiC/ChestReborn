package jp.houlab.Mochidsuki.chestReborn;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.loot.LootTable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static jp.houlab.Mochidsuki.chestReborn.Main.plugin;

public class ChestControl {
    static public HashMap<Block,LootTable> chests = new HashMap<>();

    static public void replace(Block block){
        Chest chest = (Chest) block.getState();
        chest.getBlock().setType(Material.AIR);
        Location loc = block.getLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                LootTable lootTable = chests.get(block);
                loc.getBlock().setType(Material.CHEST);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Chest chest1 = (Chest) loc.getBlock().getState();
                        chest1.setLootTable(lootTable);
                        chest1.update(true);
                    }
                }.runTaskLater(plugin,1);
            }
        }.runTaskLater(plugin,1L);
    }

    static public void replace(Location loc){
        if(loc.getBlock().getType().equals(Material.CHEST)) {
            replace(loc.getBlock());
        }
    }

    static public void replaceAll(){
        for(Block block : chests.keySet()){
            replace(block);
        }
    }

    static public void replaceRandom(int quantity){
        Block[] chests = ChestControl.chests.keySet().toArray(Block[]::new);
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < quantity) {
            int number = random.nextInt(chests.length);
            uniqueNumbers.add(number);
        }

        for(int i : uniqueNumbers){
            replace(chests[i]);
        }
    }

}
