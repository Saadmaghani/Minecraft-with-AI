package com.saadghani.minecraft.blocks.firstBot;

import com.saadghani.minecraft.McMod;
import com.saadghani.minecraft.blocks.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;


import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class BBTeller extends BlockBase {
    public BBTeller() { super(Material.ROCK, "bb_teller"); }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        try {

           /* int featureColumns = 25;

            //load json blocks
            JSONObject metaObj = (JSONObject)  new JSONParser().parse(  new FileReader( new ClassPathResource("meta1.json").getFile().getPath()));

            JSONArray blocks = (JSONArray) metaObj.get("blocks");
            for(int i=0; i<blocks.size(); i++){
                JSONObject block = (JSONObject) blocks.get(i);
                JSONArray dummyBlocks = (JSONArray) block.get("block"+(i+1));
                featureColumns += dummyBlocks.size();
            }

            // load the model
            String simpleMlp = new ClassPathResource("network1.h5").getFile().getPath();
            MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
            */


            // make a random sample
            int inputs = 50;
            INDArray raw_data = Nd4j.zeros(inputs);
            ArrayList<String> blocksAround = BBHelper.getWorldDetails(pos, worldIn);

            for(int i=0; i<blocksAround.size()-1; i+=2)
                raw_data.putScalar(new int[] {i}, Integer.parseInt(blocksAround.get(i)));
            for(int i=1; i<blocksAround.size()-1; i+=2)
                raw_data.putScalar(new int[] {i}, Integer.parseInt(blocksAround.get(i)));

            INDArray features = Nd4j.zeros(McMod.featureColumns);
            for(int i=0; i<inputs; i+=2)
                features.putScalar(new int[] {i/2}, raw_data.getDouble(new int[] {0,i}));

            int whereWeAre = 25;
            JSONArray blocks = (JSONArray) McMod.metaObj.get("blocks");
            for(int i=1; i<inputs; i+=2){
                Double rawBlock = raw_data.getDouble(new int[] {0, i});
                JSONObject jsonBlock = (JSONObject) blocks.get((i-1)/2);
                JSONArray dummyBlocks = (JSONArray) jsonBlock.get("block"+((i+1)/2));
                for(int j = 0; j< dummyBlocks.size(); j++, whereWeAre++){
                    Long b = (Long) dummyBlocks.get(j);
                    if(rawBlock == b.doubleValue()  ){
                        features.putScalar(new int[] {whereWeAre}, 1);
                    }

                }
            }

            // get the prediction
            INDArray output = McMod.model.output(features);

            JSONArray biomes = (JSONArray) McMod.metaObj.get("biomes");
            for(int i=0; i<biomes.size(); i++){
                playerIn.sendMessage(new TextComponentString("biome: "+biomes.get(i)+" probability: " +output.getDouble(new int[] {0, i})));
                System.out.println("biome: "+biomes.get(i)+" probability: " +output.getDouble(new int[] {0, i}));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
