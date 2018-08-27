package com.saadghani.minecraft.client;

import com.saadghani.minecraft.McMod;
import com.saadghani.minecraft.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class AiTab extends CreativeTabs {

    public AiTab() {
        super(McMod.MODID);
    }


    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.ingotCopper);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

}
