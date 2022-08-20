package com.gmail.kobun127.ultimateability.Ability.YaeAbility;

import com.gmail.kobun127.ultimateability.Mana.Mana;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class YaeListener implements Listener {
    @EventHandler
    public void onCrystalPlace(PlayerInteractEvent event) {
        //変数定義
        Player player = event.getPlayer();
        World world = player.getWorld();
        Action action = event.getAction();
        EquipmentSlot slot = event.getHand();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        //以下を満たしたとき終了
        if (!player.getName().equals("tubugai")) return;
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (slot != EquipmentSlot.HAND) return;
        if (!itemStack.getType().equals(Material.AMETHYST_SHARD)) return;

        Block block = player.getTargetBlock(null, 12);

        if (block.getType().equals(Material.AIR)) return;
        Location location = block.getLocation();
        location.add(0.5, 0, 0.5);
        for (int i = 1; i <= 4; i++) {
            if (!world.getBlockAt(location.clone().add(0, i, 0)).getType().equals(Material.AIR)) return;
        }

        if (!Mana.getHolder(player).use(400)) return;

        Location targetPos = location.clone().add(0, 3, 0);

        YaeAbility.spawnShooter(targetPos, player);
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType().equals(EntityType.SHULKER)) event.getDrops().clear();
    }
}
