package com.gmail.kobun127.ultimateability;

import com.gmail.kobun127.ultimateability.Ability.YaeAbility.YaeAbility;
import com.gmail.kobun127.ultimateability.HUD.HUDManager;

public class Main {
    public static void update() {
        HUDManager.update();
        YaeAbility.update();
    }
}