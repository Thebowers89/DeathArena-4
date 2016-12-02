package Kerlab.DeathArena.Commands;

import Kerlab.DeathArena.Utils.ArenaManager;
import Kerlab.DeathArena.Utils.ArenaUtils;
import Kerlab.DeathArena.Utils.Game;
import Kerlab.DeathArena.Utils.GameManager;
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
                    //TODO add logic for players to join games by id -- probably wont be used much
                } else if (input.equalsIgnoreCase("config")) {
                    if (player.isOp() || player.hasPermission("DeathArena-4.Modify")) {
                        if (ArenaManager.doesExist(name)) {
                            player.sendMessage("Configuring " + name);
                            ArenaUtils.players.put(player, ArenaManager.getArena(name));
                            player.openInventory(ArenaUtils.configInventory());
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "That Arena does not exist!");
                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not allowed to do this!");
                        return true;
                    }
                }
            } else {
                if (args[0].equalsIgnoreCase("leave")) {
                    if (GameManager.playerGames.containsKey(player)) {
                        Game game = GameManager.playerGames.get(player);
                        game.removePlayer(player);
                        player.sendMessage(ChatColor.GOLD + "Leaving game!");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "You are not in a game!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    player.sendMessage(ChatColor.GREEN + "Showing all active games:");
                    for (Game game : GameManager.games) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Game: ");
                        sb.append(game.getId());
                        sb.append(" State: ");
                        sb.append(game.getState().getString());
                        player.sendMessage("    " + sb.toString());
                    }
                    return true;
                }
            }
            player.sendMessage(ChatColor.RED + "Invalid Syntax!");
            return true;
        }
        sender.sendMessage("Only players may execute this command!");
        return false;
    }
}
