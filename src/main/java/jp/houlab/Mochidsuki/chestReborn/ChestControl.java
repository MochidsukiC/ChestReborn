package jp.houlab.Mochidsuki.chestReborn;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.loot.LootTable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static jp.houlab.Mochidsuki.chestReborn.Main.plugin;

/**
 * チェストをコントロールするクラス
 * @author Mochidsuki
 */
public class ChestControl {
    /**
     * プレイヤーがインタラクトしたチェストのルートテーブルのバックアップ
     */
    static public HashMap<Block,LootTable> chests = new HashMap<>();

    /**
     * ルートテーブルを指定してチェストを再設置する
     * @param block 再設置するチェスト
     * @param table ルートテーブル
     * @return 再設置に成功したか
     */
    static public boolean replaceWithTable(Block block,LootTable table){
        if(block.getType() == Material.CHEST) {
            Chest chest = (Chest) block.getState();
            chest.getBlock().setType(Material.AIR);
            BlockData data = chest.getBlockData();
            BlockFace blockFace = ((Directional) data).getFacing();
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
                            BlockData data1 = chest1.getBlockData();
                            ((Directional) data1).setFacing(blockFace);
                            chest1.setBlockData(data1);
                            chest1.setLootTable(lootTable);
                            chest1.update(true);
                            loc.getWorld().spawnParticle(Particle.LAVA,loc,15);
                        }
                    }.runTaskLater(plugin, 1);
                }
            }.runTaskLater(plugin, 1L);
            return true;
        }else {
            return false;
        }
    }

    /**
     * ルートテーブルを指定してチェストを再設置する
     * @param loc 再設置するチェストの座標
     * @param table ルートテーブル
     * @return 再設置に成功したか
     */
    static public boolean replaceWithTable(Location loc,LootTable table){
        if(loc.getBlock().getType().equals(Material.CHEST)) {
            replaceWithTable(loc.getBlock(),table);
            return true;
        }else {
            return false;
        }
    }

    /**
     * ルートテーブルをロールバックしてチェストを再設置する
     * @param block 再設置するチェスト
     * @return 再設置に成功したか
     */
    static public boolean replaceReturn(Block block){
        if(block.getType().equals(Material.CHEST)) {
            replaceWithTable(block, chests.get(block));
            return true;
        }else {
            return false;
        }
    }

    /**
     * ルートテーブルをロールバックしてチェストを再設置する
     * @param loc 再設置する場所
     * @return 再設置に成功したか
     */
    static public boolean replaceReturn(Location loc){
        Block block = loc.getBlock();
        if(block.getType().equals(Material.CHEST)) {
            replaceWithTable(block, chests.get(block));
            return true;
        }else{
            return false;
        }
    }

    /**
     * ルートテーブルを指定してチェストを全て再設置する
     * @param table ルートテーブル
     */
    static public void replaceAllWithTable(LootTable table){
        for(Block block : chests.keySet()){
            replaceWithTable(block,table);
        }
    }

    /**
     * ルートテーブルを指定してチェストを指定した数再設置する
     * @param quantity 再設置する数
     * @param table ルートテーブル
     */
    static public void replaceRandomWithTable(int quantity,LootTable table){
        Block[] chests = ChestControl.chests.keySet().toArray(Block[]::new);
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < quantity) {
            int number = random.nextInt(chests.length);
            uniqueNumbers.add(number);
        }

        for(int i : uniqueNumbers){
            replaceWithTable(chests[i],table);
        }
    }

    /**
     * ルートテーブルをロールバックしてチェストを指定した数再設置する
     * @param quantity 再設置する数
     */
    static public void replaceRandomReturn(int quantity){
        Block[] chests = ChestControl.chests.keySet().toArray(Block[]::new);
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < quantity) {
            int number = random.nextInt(chests.length);
            uniqueNumbers.add(number);
        }

        for(int i : uniqueNumbers){
            replaceReturn(chests[i]);
        }
    }

}
