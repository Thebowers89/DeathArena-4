package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager implements Listener {

    public static ArrayList<Game> games = new ArrayList<>();
    public static HashMap<Player, Game> playerGames = new HashMap<>();

    public static Game createGame(Sign sign) {
        Game game = new Game(ArenaManager.getRandomArena(), sign, games.size());
        games.add(game);
        return game;
    }

    public static Game createGame(Arena arena, Sign sign) {
        Game game = new Game(arena, sign, games.size());
        games.add(game);
        return game;
    }

    public static Game getGame(int id) {
        for (Game g : games) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public static void createGameSign(Sign sign, Game game) {
        sign.setLine(0, ChatColor.RED + "[DA] " + game.getId());
        sign.setLine(1, ChatColor.AQUA + game.getArena().getName());
        sign.setLine(2, game.getState().getString());
        //TODO add player limits to games
        sign.setLine(3, ChatColor.BLUE + "NYI");
        sign.update();
    }

    public static void correctSign(Sign sign) {
        sign.setLine(1, ChatColor.GOLD + "Right-Click");
        sign.setLine(2, ChatColor.GOLD + "to create game");
        sign.update();
    }

    @EventHandler
    public void onSign(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.WALL_SIGN) || e.getClickedBlock().getType().equals(Material.SIGN_POST)) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(1).equals(ChatColor.GOLD + "Right-Click") && sign.getLine(2).equals(ChatColor.GOLD + "to create game")) {
                    Game game = GameManager.createGame(sign);
                    createGameSign(sign, game);
                    game.addPlayer(e.getPlayer());
                    playerGames.put(e.getPlayer(), game);
                    e.getPlayer().sendMessage(ChatColor.GOLD + "Joining game!");
                } else if (sign.getLine(0).split(" ")[0].equals(ChatColor.RED + "[DA]")) {
                    String[] line1 = sign.getLine(0).split(" ");
                    if (getGame(Integer.parseInt(line1[1])) != null) {
                        Game game = getGame(Integer.parseInt(line1[1]));
                        game.addPlayer(e.getPlayer());
                        playerGames.put(e.getPlayer(), game);
                        e.getPlayer().sendMessage(ChatColor.GOLD + "Joining game!");
                    } else {
                        correctSign(sign);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSign2(PlayerInteractEvent e) {
        //System.out.println("test1");
        if (e.getPlayer().isOp() || e.getPlayer().hasPermission("DeathArena-4.Modify")) {
           //System.out.println("test2");
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
               //System.out.println("test3");
                if (e.getClickedBlock().getType().equals(Material.WALL_SIGN) || e.getClickedBlock().getType().equals(Material.SIGN_POST)) {
                    //System.out.println("test4");
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    if (sign.getLine(0).equals("create game sign")) {
                        //System.out.println("test5");
                        sign.setLine(0, "");
                        sign.setLine(1, ChatColor.GOLD + "Right-Click");
                        sign.setLine(2, ChatColor.GOLD + "to create game");
                        sign.update();
                    }
                }
            }
        }
    }

}
