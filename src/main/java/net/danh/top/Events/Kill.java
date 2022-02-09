package net.danh.top.Events;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import net.danh.top.Data.Files;
import net.danh.top.Top;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Kill implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        LivingEntity mob = e.getEntity();
        String vanillamobs = e.getEntityType().toString();
        if (p == null) {
            return;
        }
        if (Top.getInstance().getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            BukkitAPIHelper mythicMobsAPI = MythicMobs.inst().getAPIHelper();
            if (mythicMobsAPI.isMythicMob(mob)) {
                return;
            }
        }

        int max = Files.getInstance().getconfig().getInt("Vanilla.Default.max");
        int min = Files.getInstance().getconfig().getInt("Vanilla.Default.min");
        if (max == 0) {
            if (min == 0) {
                return;
            }
        }
        for (String getEntityType : Files.getInstance().getconfig().getConfigurationSection("Vanilla.").getKeys(false)) {
            if (vanillamobs.equalsIgnoreCase(getEntityType)) {
                max = Files.getInstance().getconfig().getInt("Vanilla." + vanillamobs + ".max");
                min = Files.getInstance().getconfig().getInt("Vanilla." + vanillamobs + ".min");
                break;
            }
        }
        if (mob instanceof Monster || mob instanceof Animals) {
            Files.getInstance().addKill(p, 1);
            Integer money = Files.getInstance().getRandomNumber(min, max);
            Top.economy.depositPlayer(p, money);
            p.sendMessage(Files.getInstance().convert("&aBạn nhận được $" + money));
        }
    }

    @EventHandler
    public void onkillmm(MythicMobDeathEvent mme) {
        Player p = (Player) mme.getKiller();
        String mobname = mme.getMobType().getInternalName();

        if (p == null) {
            return;
        }
        int max = Files.getInstance().getconfig().getInt("MythicMobs.Default.max");
        int min = Files.getInstance().getconfig().getInt("MythicMobs.Default.min");
        if (max == 0) {
            if (min == 0) {
                return;
            }
        }
        for (String getstring : Files.getInstance().getconfig().getConfigurationSection("MythicMobs.").getKeys(false)) {
            if (mobname.equalsIgnoreCase(getstring)) {
                max = Files.getInstance().getconfig().getInt("MythicMobs." + mobname + ".max");
                min = Files.getInstance().getconfig().getInt("MythicMobs." + mobname + ".min");
                break;
            }
        }
        Files.getInstance().addKill(p, 1);
        Integer money = Files.getInstance().getRandomNumber(min, max);
        Top.economy.depositPlayer(p, money);
        p.sendMessage(Files.getInstance().convert("&aBạn nhận được $" + money));
    }
}

