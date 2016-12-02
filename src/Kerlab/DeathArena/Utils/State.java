package Kerlab.DeathArena.Utils;

import org.bukkit.ChatColor;

public enum State {

    STOPPED(ChatColor.RED + "Stopped"),
    RUNNING(ChatColor.GREEN + "Running"),
    ENDING(ChatColor.AQUA + "ENDING");

    String string;

    State(String string) {
        this.string = string;
    }

    public String getString() {
        return this.string;
    }

}
