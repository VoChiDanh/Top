package net.danh.top.Commands;

import net.danh.top.Data.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (label.equalsIgnoreCase("top")) {
            if (sender.hasPermission("top.admin")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Files.getInstance().reloadconfig();
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
        return true;
    }
}
