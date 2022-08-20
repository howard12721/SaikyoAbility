package com.gmail.kobun127.ultimateability.Ability.YaeAbility;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashSet;

public class YaeAbility {
    private static final HashSet<YaeShooter> shooters = new HashSet<>();

    public static void spawnShooter(Location location, Entity owner) {
        shooters.add(new YaeShooter(location, owner));
    }

    public static void update() {
        for (YaeShooter shooter : shooters) {
            shooter.update();
            if (shooter.isDead()) shooter.destroy();
        }
        shooters.removeIf(YaeShooter::isDead);
    }
}
