package jp.houlab.Mochidsuki.chestReborn;

import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(command.getName().equalsIgnoreCase("chestreborn")) {
            if(strings.length == 1){
                if(strings[0].equalsIgnoreCase("debug")){
                    Player player = (Player) commandSender;
                    ChestControl.replace(player.getTargetBlockExact(10));
                }
                if(strings[0].equalsIgnoreCase("setloot")){
                }
            }
        }
        return false;
    }
}
