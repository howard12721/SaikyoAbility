package com.gmail.kobun127.ultimateability.HUD;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HUDManager {
    private static final Map<UUID, HUDData> dataContainer = new HashMap<>();

    public static HUDData getHUDData(Player player) {
        dataContainer.putIfAbsent(player.getUniqueId(), new HUDData());
        return dataContainer.get(player.getUniqueId());
    }

    public static void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getName().equals("tubugai")) continue;
            HUDData data = getHUDData(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§lMana: " + data.getMana() + " / " + data.getMaxMana()));
        }
    }
}
