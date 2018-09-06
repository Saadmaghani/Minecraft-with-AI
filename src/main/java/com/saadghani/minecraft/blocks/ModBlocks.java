package com.saadghani.minecraft.blocks;

import com.saadghani.minecraft.blocks.firstBot.BBStationary;
import com.saadghani.minecraft.blocks.firstBot.BiomeBot;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks  {


    public static BiomeBot biomeBot = new BiomeBot();
    public static BBStationary bb_stationary = new BBStationary();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                biomeBot, bb_stationary
        );
        GameRegistry.registerTileEntity(biomeBot.getTileEntityClass(), biomeBot.getRegistryName().toString());

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(

                biomeBot.createItemBlock(),
                bb_stationary.createItemBlock()
        );
    }

    public static void registerModels() {
        bb_stationary.registerItemModel(Item.getItemFromBlock(bb_stationary));
        biomeBot.registerItemModel(Item.getItemFromBlock(biomeBot));
    }
}
