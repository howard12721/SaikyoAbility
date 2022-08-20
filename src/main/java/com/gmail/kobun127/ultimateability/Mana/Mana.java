package com.gmail.kobun127.ultimateability.Mana;

import com.gmail.kobun127.ultimateability.HUD.HUDData;
import com.gmail.kobun127.ultimateability.HUD.HUDManager;
import com.gmail.kobun127.ultimateability.UltimateAbility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Mana {
    public static void init() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Mana.updateMana();
            }
        }.runTaskTimerAsynchronously(UltimateAbility.getPlugin(), 0, 1);
    }

    public static void updateMana() {
        for (ManaHolder manaData : ManaContainer.getValues()) {
            manaData.regenerate();
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            HUDData data = HUDManager.getHUDData(player);
            ManaHolder manaData = ManaContainer.getManaHolder(player.getUniqueId());
            data.setMana(Integer.toString(manaData.getMana() / 10));
            data.setMaxMana(Integer.toString(manaData.getMaxMana() / 10));
        }
    }

    public static ManaHolder getHolder(Player player) {
        return ManaContainer.getManaHolder(player.getUniqueId());
    }
}
