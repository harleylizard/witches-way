package com.harleylizard.witches_way.common.power;

import net.minecraft.nbt.CompoundTag;

public final class PowerSource implements HasPower {
    private int amount;

    public void save(CompoundTag tag) {
        tag.putInt("Amount", amount);
    }

    public void load(CompoundTag tag) {
        amount = tag.getInt("Amount");
    }

    public void set(int amount) {
        this.amount = amount;
    }

}
