package Kerlab.DeathArena.Commands;

import Kerlab.DeathArena.Utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class DAUtilsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("DeathArena.Modify")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("setcommonarea")) {
                        if (LocationUtils.saveLocation(new File(Bukkit.getServer().getPluginManager().getPlugin("DeathArena-4").getDataFolder() + "/Utils.yml"), player.getLocation(), "Locations.Common")) {
                            player.sendMessage(ChatColor.GREEN + "Common Area location set!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "Something went wrong saving the location!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Arguments!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Syntax!");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to do this!");
                return true;
            }
        }
        sender.sendMessage("Only players may use this command!");
        return false;
    }
}
