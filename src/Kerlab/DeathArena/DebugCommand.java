package Kerlab.DeathArena;

import Kerlab.DeathArena.Utils.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            ArenaManager.debug((Player) sender);
            return true;
        }
        sender.sendMessage("You are not a player!");
        return false;
    }
}
