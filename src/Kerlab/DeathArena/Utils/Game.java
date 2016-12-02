package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Game {

    private int id;
    private UUID bossId;
    private Sign sign;
    private Arena arena;
    private ArrayList<Player> players = new ArrayList<>();

    private State state = State.STOPPED;

    public Game(Arena arena, Sign sign, int id) {
        this.id = id;
        this.arena = arena;
        this.sign = sign;
    }

    public void addPlayer(Player player) {
        if (this.state.equals(State.STOPPED)) {
            players.add(player);
        } else {
            player.sendMessage(ChatColor.RED + "You cannot join a game in progress!");
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void start() {
        this.state = State.RUNNING;
        //TODO Start game logic here
    }

    public void end() {
        this.state = State.PAUSED;
        //TODO End game logic here
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
