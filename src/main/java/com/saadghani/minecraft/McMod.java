package com.saadghani.minecraft;

import com.saadghani.minecraft.client.AiTab;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.saadghani.minecraft.blocks.ModBlocks;
import com.saadghani.minecraft.items.ModItems;
import net.minecraftforge.fml.common.network.NetworkRegistry;


@Mod(modid = McMod.MODID, name = McMod.NAME, version = McMod.VERSION)
public class McMod
{
    public static final String MODID = "smagsmod";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Minecraft with AI";

    public static final AiTab creativeTab = new AiTab();



    @Mod.Instance(MODID)
    public static McMod instance;

    @SidedProxy(serverSide = "com.saadghani.minecraft.CommonProxy", clientSide = "com.saadghani.minecraft.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(NAME + " is loading!");
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }
}
