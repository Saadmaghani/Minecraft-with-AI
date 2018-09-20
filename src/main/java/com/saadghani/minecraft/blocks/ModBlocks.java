package com.saadghani.minecraft.blocks;

import com.saadghani.minecraft.blocks.firstBot.BBStationary;
import com.saadghani.minecraft.blocks.firstBot.BBTeller;
import com.saadghani.minecraft.blocks.firstBot.BiomeBot;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks  {


    public static BiomeBot biomeBot = new BiomeBot();
    public static BBStationary bb_stationary = new BBStationary();
    public static BBTeller bb_teller = new BBTeller();
    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                biomeBot, bb_stationary, bb_teller
        );
        GameRegistry.registerTileEntity(biomeBot.getTileEntityClass(), biomeBot.getRegistryName().toString());

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                biomeBot.createItemBlock(),
                bb_stationary.createItemBlock(),
                bb_teller.createItemBlock()
        );
    }

    public static void registerModels() {
        bb_stationary.registerItemModel(Item.getItemFromBlock(bb_stationary));
        biomeBot.registerItemModel(Item.getItemFromBlock(biomeBot));
        bb_teller.registerItemModel(Item.getItemFromBlock(bb_teller));
    }
}
