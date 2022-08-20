package com.gmail.kobun127.ultimateability.Mana;

public class ManaHolder {
    ManaHolder(int maxMana) {
        this(maxMana, 3);
    }

    ManaHolder(int maxMana, int manaRegenAmount) {
        this.mana = maxMana;
        this.maxMana = maxMana;
        this.manaRegenAmount = manaRegenAmount;
    }

    private int mana;
    private int maxMana;
    private int manaRegenAmount;

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getManaRegenAmount() {
        return manaRegenAmount;
    }

    public void setMana(int newValue) {
        this.mana = Math.max(0, Math.min(getMaxMana(), newValue));
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        setMana(getMana());
    }

    public void setManaRegenAmount(int manaRegenAmount) {
        this.manaRegenAmount = manaRegenAmount;
    }

    public void regenerate() {
        setMana(getMana() + getManaRegenAmount());
    }

    public boolean use(int amount) {
        if (amount > getMana()) {
            return false;
        }
        setMana(getMana() - amount);
        return true;
    }
}
