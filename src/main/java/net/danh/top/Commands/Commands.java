package net.danh.top.Commands;

import me.NoChance.PvPManager.Managers.PlayerHandler;
import me.NoChance.PvPManager.PvPManager;
import me.NoChance.PvPManager.PvPlayer;
import net.danh.top.Data.Files;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (label.equalsIgnoreCase("top")) {
            if (sender.hasPermission("top.admin")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Files.getInstance().reloadconfig();
                        sender.sendMessage(Files.getInstance().convert("&6Reloaded"));
                    }
                }
                if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("blocks")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("kills")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setKill(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addKill(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeKill(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert("&aDone"));
                            }
                        }
                    }
                }
            }
        }
        if (label.equalsIgnoreCase("danhnhau")) {
            if (sender instanceof Player) {
                for (ItemStack item : Objects.requireNonNull(((Player) sender).getPlayer()).getInventory().getContents()) {
                    if (item.getType() != Material.ACACIA_LOG
                            || item.getType() != Material.BIRCH_LOG
                            || item.getType() != Material.DARK_OAK_LOG
                            || item.getType() != Material.JUNGLE_LOG
                            || item.getType() != Material.OAK_LOG
                            || item.getType() != Material.SPRUCE_LOG) {
                        continue;
                    }
                    if (item.getAmount() < 5) {
                        continue;
                    }

                    if (item.getAmount() == 5) {
                        ((Player) sender).getPlayer().getInventory().remove(item);
                    } else {
                        item.setAmount(item.getAmount() - 5);
                    }
                    break;
                }
                PvPManager pvpmanager = PvPManager.getInstance();
                PlayerHandler ph = pvpmanager.getPlayerHandler();
                PvPlayer pvpPlayer = ph.get(((Player) sender).getPlayer());
                pvpPlayer.setPvP(!pvpPlayer.hasPvPEnabled());
                if (pvpPlayer.hasPvPEnabled()){
                    sender.sendMessage(Files.getInstance().convert("&aBạn đã bật PvP"));
                } else {
                    sender.sendMessage(Files.getInstance().convert("&aBạn đã &ctắt&a PvP"));
                }
            }
        }
        return true;
    }
}
