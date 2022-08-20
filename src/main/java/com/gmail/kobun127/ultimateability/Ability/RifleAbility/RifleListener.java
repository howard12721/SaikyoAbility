package com.gmail.kobun127.ultimateability.Ability.RifleAbility;

import com.gmail.kobun127.ultimateability.Ability.Ability;
import com.gmail.kobun127.ultimateability.Mana.Mana;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class RifleListener implements Listener {
    @EventHandler
    public void onRifleShot(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Action action = event.getAction();
        EquipmentSlot slot = event.getHand();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        //以下を満たしたとき終了
        if (!player.getName().equals("tubugai")) return;
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (slot != EquipmentSlot.HAND) return;
        if (!itemStack.getType().equals(Material.DIAMOND_HORSE_ARMOR)) return;
        if (!Mana.getHolder(player).use(30)) return;

        //弾を発射
        Snowball bullet = player.launchProjectile(Snowball.class);
        bullet.setItem(new ItemStack(Material.FIRE_CHARGE));
        bullet.setVelocity(player.getLocation().getDirection().multiply(5));
        bullet.getPersistentDataContainer().set(Ability.ENTITY_TYPE_KEY, PersistentDataType.STRING, "bullet");
        bullet.setCustomName("§4§l銃弾");
        bullet.setCustomNameVisible(true);
        bullet.setVisualFire(true);

        //音を鳴らす
        world.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5, 2);
        world.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 5, 1);
        world.playSound(player.getLocation(), Sound.BLOCK_CHAIN_BREAK, 2, 1);
        world.playSound(player.getLocation(), Sound.BLOCK_BELL_USE, 5, 2);
    }

    @EventHandler
    public void onRifleHit(ProjectileHitEvent event) {
        //変数定義
        Projectile projectile = event.getEntity();
        Entity entity = event.getHitEntity();
        String entityType = projectile.getPersistentDataContainer().get(Ability.ENTITY_TYPE_KEY, PersistentDataType.STRING);

        //以下の条件を満たしたとき終了
        if (entity == null) return;
        if (entityType == null) return;
        if (!entityType.equals("bullet")) return;
        if (!(entity instanceof LivingEntity)) return;

        //敵にダメージを与える
        LivingEntity target = (LivingEntity) entity;
        target.damage(2, projectile);
        target.setNoDamageTicks(0);
    }
}
