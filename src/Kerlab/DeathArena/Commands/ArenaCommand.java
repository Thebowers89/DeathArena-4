package Kerlab.DeathArena.Commands;

import Kerlab.DeathArena.Utils.ArenaManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                String input = args[0];
                String name = args[1];
                if (input.equalsIgnoreCase("create")) {
                    if (player.isOp() || player.hasPermission("DeathArena-4.Modify")) {
                        if (!ArenaManager.doesExist(name)) {
                            ArenaManager.createArena(name);
                            player.sendMessage(ChatColor.GREEN + "Arena created!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "That arena already exists!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not allowed to do this!");
                        return true;
                    }
                } else if (input.equalsIgnoreCase("delete")) {
                    if (player.isOp() || player.hasPermission("DeathArena-4.Modify")) {
                        if (ArenaManager.doesExist(name)) {
                            ArenaManager.deleteArena(name);
                            player.sendMessage(ChatColor.GREEN + "Arena deleted!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "That arena does not exist!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not allowed to do this!");
                        return true;
                    }
                } else if (input.equalsIgnoreCase("load")) {
                    if (player.isOp() || player.hasPermission("DeathArena-4.Modify")) {
                        if (ArenaManager.doesExist(name)) {
                            ArenaManager.loadArena(name);
                            player.sendMessage(ChatColor.GREEN + "Arena loaded!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "That Arena does not exist!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not allowed to do this!");
                        return true;
                    }
                } else if (input.equalsIgnoreCase("unload")) {
                    if (player.isOp() || player.hasPermission("DeathArena-4.Modify")) {
                        if (ArenaManager.doesExist(name)) {
                            ArenaManager.unloadArena(name);
                            player.sendMessage(ChatColor.GOLD + "Arena unloaded!");
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "That Arena does not exist!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not allowed to do this!");
                        return true;
                    }
                } else if (input.equalsIgnoreCase("join")) {

                }
            } else {
                if (args[0].equalsIgnoreCase("leave")) {

                } else if (args[0].equalsIgnoreCase("list")) {

                }
            }
            player.sendMessage(ChatColor.RED + "Invalid Syntax!");
            return true;
        }
        sender.sendMessage("Only players may execute this command!");
        return false;
    }
}
