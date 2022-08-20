package com.gmail.kobun127.ultimateability.HUD;

public class HUDData {
    public HUDData() {
        maxMana = "N/A";
        mana = "N/A";
        selectedAbility = "N/A";
    }

    private String maxMana;
    private String mana;
    private String selectedAbility;

    public void setMaxMana(String maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public void setSelectedAbility(String selectedAbility) {
        this.selectedAbility = selectedAbility;
    }

    public String getMaxMana() {
        return maxMana;
    }

    public String getMana() {
        return mana;
    }

    public String getSelectedAbility() {
        return selectedAbility;
    }
}
