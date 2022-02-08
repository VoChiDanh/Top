package net.danh.top.Data;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.top.Top;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {


    @Override
    public String getAuthor() {
        return Top.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "top";
    }

    @Override
    public String getVersion() {
        return Top.getInstance().getDescription().getVersion();
    }


    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) {
            return "Player not online";
        }
        if (identifier.equalsIgnoreCase("block")) {
            return String.valueOf(Files.getInstance().getBlocks(p));
        }
        if (identifier.equalsIgnoreCase("kill")) {
            return String.valueOf(Files.getInstance().getKill(p));
        }
        return null;
    }
}


