package com.gmail.kobun127.ultimateability.Ability.YaeAbility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Shulker;

import java.util.Objects;

public class ShooterCore {
    public static ShooterCore spawn(Location spawnPos) {
        Location location = spawnPos.clone().add(0, -0.5, 0);
        World world = Objects.requireNonNull(location.getWorld());
        ArmorStand stand = world.spawn(location, ArmorStand.class, e -> {
            e.setMarker(true);
            e.setInvisible(true);
            e.setSilent(true);
            e.setInvulnerable(true);
        });
        Shulker core = world.spawn(location, Shulker.class, e -> {
            e.setAI(false);
            e.setSilent(true);
            e.setInvisible(true);
        });

        FallingBlock blockSkin = world.spawnFallingBlock(location, Bukkit.createBlockData(Material.LIGHT_BLUE_STAINED_GLASS));
        blockSkin.setGravity(false);
        blockSkin.setHurtEntities(false);
        blockSkin.setSilent(true);
        blockSkin.setDropItem(false);
        blockSkin.setTicksLived(2147483647);
        blockSkin.setInvulnerable(true);
        blockSkin.setGlowing(true);

        stand.addPassenger(core);
        stand.addPassenger(blockSkin);

        return new ShooterCore(stand, core, blockSkin);
    }

    private ShooterCore(ArmorStand carrier, Shulker core, FallingBlock blockSkin) {
        this.stand = carrier;
        this.core = core;
        this.blockSkin = blockSkin;
    }

    public boolean isDead() {
        return core.isDead() || stand.isDead() || blockSkin.isDead();
    }

    private final ArmorStand stand;
    private final Shulker core;
    private final FallingBlock blockSkin;

    public void destroy() {
        stand.remove();
        core.remove();
        blockSkin.remove();
    }

    public Shulker getCoreEntity() {
        return core;
    }
}
