package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game {

    private int id;
    private UUID bossId;
    private Sign sign;
    private Arena arena;
    private HashMap<Player, Boolean> players = new HashMap<>();

    private State state = State.STOPPED;

    public Game(Arena arena, Sign sign, int id) {
        this.id = id;
        this.arena = arena;
        this.sign = sign;
    }

    public void addPlayer(Player player) {
        if (this.state.equals(State.STOPPED)) {
            players.put(player, false);
            player.teleport(arena.getLobby());
        } else {
            player.sendMessage(ChatColor.RED + "You cannot join a game in progress!");
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        if (players.size() == 0) {
            end();
        }
    }

    public void preparationTick(Player player) {
        if (players.get(player)) {
            players.put(player, false);
        } else {
            players.put(player, true);
            readyCheck();
        }
    }

    public void readyCheck() {
        for (Map.Entry<Player, Boolean> map : players.entrySet()) {
            if (!map.getValue()) {
                break;
            }
        }
        start();
    }

    public void start() {
        this.state = State.RUNNING;
        //TODO Start game logic here
    }

    public void end() {
        this.state = State.ENDING;
        //TODO End game logic here
        GameManager.correctSign(this.sign);
        GameManager.games.remove(this);
    }

    public void setBossId(UUID id) {
        this.bossId = id;
    }

    public int getId() {
        return this.id;
    }

    public Sign getSign() {
        return this.sign;
    }

    public Arena getArena() {
        return this.arena;
    }

    public UUID getBossId() {
        return this.bossId;
    }

    public State getState() {
        return this.state;
    }

}
