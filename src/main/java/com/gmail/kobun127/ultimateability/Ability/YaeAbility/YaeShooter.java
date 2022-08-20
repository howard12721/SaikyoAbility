package com.gmail.kobun127.ultimateability.Ability.YaeAbility;

import com.gmail.kobun127.ultimateability.HowaDraw.HowaDraw;
import com.gmail.kobun127.ultimateability.HowaDraw.Line.HowaLine;
import com.gmail.kobun127.ultimateability.UltimateAbility;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.*;

public class YaeShooter {
    public YaeShooter(Location center, Entity owner) {
        this.center = center;
        this.owner = owner;
        this.core = ShooterCore.spawn(center);
    }

    private final Location center;
    private final Entity owner;
    private final ShooterCore core;
    private double rotation = 0;
    private int health = 600;
    private final int attackRate = 40;
    private final int damage = 4;
    private final int range = 8;
    private int attackCooldown = attackRate;

    public boolean isDead() {
        return health <= 0 || core.isDead();
    }

    public void update() {
        if (attackCooldown <= 0) {
            attack();
            attackCooldown = attackRate;
        }
        attackCooldown--;

        Location top = center.clone().add(0, 1.8, 0);
        Location bottom = center.clone().add(0, -1.8, 0);
        final Set<Location> sides = new HashSet<>(Arrays.asList(
                center.clone().add(HowaDraw.getPolar(1.5, rotation)),
                center.clone().add(HowaDraw.getPolar(1.5, rotation + 90)),
                center.clone().add(HowaDraw.getPolar(1.5, rotation + 180)),
                center.clone().add(HowaDraw.getPolar(1.5, rotation + 270))
        ));

        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(center.clone().add(range, 0, range))
                .setEnd(center.clone().add(range, 0, -range))
                .setParticle(Particle.END_ROD)
                .setDensity(1)
                .draw();
        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(center.clone().add(range, 0, -range))
                .setEnd(center.clone().add(-range, 0, -range))
                .setParticle(Particle.END_ROD)
                .setDensity(1)
                .draw();
        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(center.clone().add(-range, 0, -range))
                .setEnd(center.clone().add(-range, 0, range))
                .setParticle(Particle.END_ROD)
                .setDensity(1)
                .draw();
        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(center.clone().add(-range, 0, range))
                .setEnd(center.clone().add(range, 0, range))
                .setParticle(Particle.END_ROD)
                .setDensity(1)
                .draw();
        for (Location location : sides) {
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(top)
                    .setEnd(location)
                    .setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.AQUA, 1))
                    .setDensity(0.5)
                    .draw();
            new HowaLine(UltimateAbility.getPlugin())
                    .setBegin(bottom)
                    .setEnd(location)
                    .setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.AQUA, 1))
                    .setDensity(0.5)
                    .draw();
        }

        rotation += 3;
        if (rotation >= 360) rotation -= 360;

        health--;
    }

    public void attack() {
        World world = Objects.requireNonNull(center.getWorld());
        final ArrayList<Entity> attackableEntities = new ArrayList<>(world.getNearbyEntities(center, range, range, range, entity -> {
            if (!(entity instanceof LivingEntity)) return false;
            if (entity.getType().equals(EntityType.ARMOR_STAND) || entity.getType().equals(EntityType.SHULKER))
                return false;
            return !entity.getUniqueId().equals(owner.getUniqueId());
        }));
        if (attackableEntities.size() == 0) return;
        LivingEntity target = (LivingEntity) attackableEntities.get(new Random().nextInt(attackableEntities.size()));
        target.damage(damage, core.getCoreEntity());
        new HowaLine(UltimateAbility.getPlugin())
                .setBegin(center)
                .setEnd(target.getLocation().add(0, target.getEyeHeight(false) / 2, 0))
                .setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.YELLOW, 3))
                .draw();
    }

    public void destroy() {
        core.destroy();
    }
}
