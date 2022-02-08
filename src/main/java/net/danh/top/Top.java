package net.danh.top;

import net.danh.top.Commands.Commands;
import net.danh.top.Data.Files;
import net.danh.top.Data.PlaceholderAPI;
import net.danh.top.Events.Break;
import net.danh.top.Events.Join;
import net.danh.top.Events.Kill;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Top extends JavaPlugin {

    private static Top instance;
    public static Economy economy;

    public static Top getInstance() {
        return instance;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }

    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Hooked with PlaceholderAPI");
            new PlaceholderAPI().register();
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Hooked with Vault");
        }
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            getLogger().log(Level.INFO, "Hooked with MythicMobs");
        }
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Kill(), this);
        getServer().getPluginManager().registerEvents(new Break(), this);
        getCommand("top").setExecutor(new Commands());
        Files.getInstance().createconfig();
    }

    @Override
    public void onDisable() {
        Files.getInstance().savedata();
        Files.getInstance().saveconfig();
        Files.getInstance().savelanguage();
    }
}
