package com.gmail.kobun127.ultimateability.Mana;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManaContainer {
    private static final Map<UUID, ManaHolder> manaMap = new HashMap<>();
    public static ManaHolder getManaHolder(UUID key) {
        manaMap.putIfAbsent(key, new ManaHolder(2000));
        return manaMap.get(key);
    }
    public static Collection<ManaHolder> getValues() {
        return manaMap.values();
    }
}