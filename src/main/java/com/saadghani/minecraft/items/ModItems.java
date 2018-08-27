package com.saadghani.minecraft.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase ingotCopper = new ItemBase("ingot_copper");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(ingotCopper);
    }

    public static void registerModels() {
        ingotCopper.registerItemModel(ingotCopper);
    }


}