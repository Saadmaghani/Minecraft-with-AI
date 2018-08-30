package com.saadghani.minecraft;

import com.saadghani.minecraft.blocks.firstBot.TESRBiomeBot;
import com.saadghani.minecraft.blocks.firstBot.TileEntityBiomeBot;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(McMod.MODID + ":" + id, "inventory"));
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBiomeBot.class, new TESRBiomeBot());
    }
}
