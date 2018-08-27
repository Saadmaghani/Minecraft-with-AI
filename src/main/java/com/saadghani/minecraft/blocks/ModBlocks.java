package com.saadghani.minecraft.blocks;

import com.saadghani.minecraft.blocks.firstBot.BiomeBot;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks  {


    public static BiomeBot counter = new BiomeBot();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                counter
        );
        GameRegistry.registerTileEntity(counter.getTileEntityClass(), counter.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(

                counter.createItemBlock()
        );
    }

    public static void registerModels() {

        counter.registerItemModel(Item.getItemFromBlock(counter));
    }
}
