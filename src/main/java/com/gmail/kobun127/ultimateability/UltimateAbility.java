package com.gmail.kobun127.ultimateability;

import com.gmail.kobun127.ultimateability.Ability.ExplosionAbility.ExplosionListener;
import com.gmail.kobun127.ultimateability.Ability.RifleAbility.RifleListener;
import com.gmail.kobun127.ultimateability.Ability.YaeAbility.YaeListener;
import com.gmail.kobun127.ultimateability.Mana.Mana;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class UltimateAbility extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new RifleListener(), getPlugin());
        pluginManager.registerEvents(new YaeListener(), getPlugin());
        pluginManager.registerEvents(new ExplosionListener(), getPlugin());
        Mana.init();
        new BukkitRunnable() {
            @Override
            public void run() {
                Main.update();
            }
        }.runTaskTimer(UltimateAbility.getPlugin(), 0, 1);
    }

    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
