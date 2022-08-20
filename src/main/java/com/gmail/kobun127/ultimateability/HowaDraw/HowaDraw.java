package com.gmail.kobun127.ultimateability.HowaDraw;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class HowaDraw {
    public static Vector getPolar(double r, double deg) {
        return new Vector(r * Math.cos(Math.toRadians(deg)), 0, r * Math.sin(Math.toRadians(deg)));
    }

    public static Vector getVector(Location start, Location end) {
        return start.clone().multiply(-1).add(end).multiply(1 / start.distance(end)).toVector();
    }
}

