package net.danh.top.Data;


import net.danh.top.Top;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Files {

    private static Files instance;
    private File configFile, dataFile;
    private FileConfiguration config, data;

    public static Files getInstance() {

        if (instance == null) {
            instance = new Files();
        }
        return instance;
    }

    public void createconfig() {
        configFile = new File(Top.getInstance().getDataFolder(), "config.yml");
        dataFile = new File(Top.getInstance().getDataFolder(), "data.yml");

        if (!configFile.exists()) Top.getInstance().saveResource("config.yml", false);
        if (!dataFile.exists()) Top.getInstance().saveResource("data.yml", false);
        config = new YamlConfiguration();
        data = new YamlConfiguration();

        try {
            config.load(configFile);
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getconfig() {
        return config;
    }

    public FileConfiguration getdata() {
        return data;
    }


    public void reloadconfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }


    public void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public Integer getBlocks(Player p) {
        return getdata().getInt("players." + p.getName() + ".Blocks");
    }

    public void setBlocks(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Blocks", number);
        savedata();
    }

    public void addBlocks(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Blocks", getBlocks(p) + number);
        savedata();
    }

    public void removeBlocks(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Blocks", getBlocks(p) - number);
        savedata();
    }

    public Integer getKill(Player p) {
        return getdata().getInt("players." + p.getName() + ".Kills");
    }

    public void setKill(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Kills", number);
        savedata();
    }

    public void addKill(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Kills", getKill(p) + number);
        savedata();
    }

    public void removeKill(Player p, Integer number) {
        getdata().set("players." + p.getName() + ".Kills", getKill(p) - number);
        savedata();
    }


    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        int randomNumber = r.nextInt(max - min) + min;
        return randomNumber;
    }

    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
