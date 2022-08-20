package com.gmail.kobun127.ultimateability.Ability.ExplosionAbility;

import com.gmail.kobun127.ultimateability.HowaDraw.HowaDraw;
import com.gmail.kobun127.ultimateability.HowaDraw.Line.HowaLine;
import com.gmail.kobun127.ultimateability.Mana.Mana;
import com.gmail.kobun127.ultimateability.UltimateAbility;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

public class ExplosionListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        //変数定義
        Player player = event.getPlayer();
        Action action = event.getAction();
        World world = player.getWorld();
        EquipmentSlot slot = event.getHand();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        final int ticks = 20;

        //以下を満たしたとき終了
        if (!player.getName().equals("tubugai")) return;
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (slot != EquipmentSlot.HAND) return;
        if (!itemStack.getType().equals(Material.RED_DYE)) return;

        RayTraceResult result = world.rayTrace(player.getEyeLocation(), player.getLocation().getDirection(), 100, FluidCollisionMode.ALWAYS, false, 1, entity -> !entity.equals(player));
        if (result == null) return;

        if (!Mana.getHolder(player).use(2)) return;

        Location targetPos = result.getHitPosition().toLocation(world).add(0, -2, 0);
        Location circle1 = targetPos.clone().add(0, 18, 0);
        Location circle2 = targetPos.clone().add(0, 23, 0);
        Location circle3 = targetPos.clone().add(0, 27, 0);
        Location circle4 = targetPos.clone().add(0, 31, 0);
        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(targetPos)
                .setEnd(targetPos.clone().add(0, 24, 0))
                .setParticle(Particle.FLAME)
                .draw();
        for (double deg = 0; deg < 360; deg += 20) {
            Location begin = targetPos.clone();
            begin.setY(-64);
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(begin.add(HowaDraw.getPolar(1, deg)))
                    .setEnd(targetPos.clone().add(0, 200, 0).add(HowaDraw.getPolar(1, deg)))
                    .setParticle(Particle.FLAME)
                    .draw();
        }
        for (double deg = 0; deg < 360; deg += 3) {
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(circle1.clone().add(HowaDraw.getPolar(12, deg)))
                    .setEnd(circle1.clone().add(HowaDraw.getPolar(10, deg)))
                    .setParticle(Particle.FLAME)
                    .setTicks(ticks / 2)
                    .draw();
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(circle2.clone().add(HowaDraw.getPolar(4, deg)))
                    .setEnd(circle2.clone().add(HowaDraw.getPolar(2, deg)))
                    .setParticle(Particle.FLAME)
                    .setTicks(ticks / 2)
                    .draw();
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(circle3.clone().add(HowaDraw.getPolar(6, deg)))
                    .setEnd(circle3.clone().add(HowaDraw.getPolar(4, deg)))
                    .setParticle(Particle.FLAME)
                    .setTicks(ticks / 2)
                    .draw();
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(circle4.clone().add(HowaDraw.getPolar(9, deg)))
                    .setEnd(circle4.clone().add(HowaDraw.getPolar(7, deg)))
                    .setParticle(Particle.FLAME)
                    .setTicks(ticks / 2)
                    .draw();
        }
        world.playSound(targetPos, Sound.ITEM_TRIDENT_THUNDER, 5, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                targetPos.setY(-64);
                for (int i = 0; i < 300; i++) {
                    world.createExplosion(targetPos, 3, false, false, player);
                    targetPos.add(0, 1, 0);
                }
            }
        }.runTaskLater(UltimateAbility.getPlugin(), ticks);
    }
}
