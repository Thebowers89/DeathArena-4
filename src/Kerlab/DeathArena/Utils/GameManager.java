package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class GameManager implements Listener {

    public static ArrayList<Game> games = new ArrayList<>();

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

    public static void correctSign(Sign sign) {
        //TODO Change sign back to default
    }

    @EventHandler
    public void onSign(PlayerInteractEvent e) {
        if (e.getClickedBlock().getType().equals(Material.SIGN)) {
            Sign sign = (Sign) e.getClickedBlock();
            if (sign.getLine(1).equals(ChatColor.GOLD + "Right-Click") && sign.getLine(2).equals(ChatColor.GOLD + "to create game")) {
                Game game = GameManager.createGame(sign);
                game.addPlayer(e.getPlayer());
                e.getPlayer().sendMessage(ChatColor.GOLD + "Joining game!");
            } else if (sign.getLine(0).split(" ")[0].equals(ChatColor.RED + "[DA]")) {
                String[] line1 = sign.getLine(0).split(" ");
                Game game = getGame(Integer.parseInt(line1[1]));
                game.addPlayer(e.getPlayer());
                e.getPlayer().sendMessage(ChatColor.GOLD + "Joining game!");
            }
        }
    }

}
