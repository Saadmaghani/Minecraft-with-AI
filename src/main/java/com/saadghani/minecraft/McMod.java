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
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.nd4j.linalg.io.ClassPathResource;
import scala.util.parsing.json.JSON;

import java.io.FileReader;


@Mod(modid = McMod.MODID, name = McMod.NAME, version = McMod.VERSION)
public class McMod
{
    public static final String MODID = "smagsmod";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "Minecraft with AI";

    public static final AiTab creativeTab = new AiTab();

    public static int featureColumns = 25;
    public static MultiLayerNetwork model;
    public static JSONObject metaObj;


    @Mod.Instance(MODID)
    public static McMod instance;

    @SidedProxy(serverSide = "com.saadghani.minecraft.CommonProxy", clientSide = "com.saadghani.minecraft.ClientProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(NAME + " is loading!");
        proxy.registerRenderers();

        try {

            //load json blocks
            metaObj = (JSONObject) new JSONParser().parse(new FileReader(new ClassPathResource("meta1.json").getFile().getPath()));

            JSONArray blocks = (JSONArray) metaObj.get("blocks");
            for (int i = 0; i < blocks.size(); i++) {
                JSONObject block = (JSONObject) blocks.get(i);
                JSONArray dummyBlocks = (JSONArray) block.get("block" + (i + 1));
                featureColumns += dummyBlocks.size();
            }

            // load the model
            String simpleMlp = new ClassPathResource("network1.h5").getFile().getPath();
            model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
        }catch (Exception e){
            e.printStackTrace();
        }
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
