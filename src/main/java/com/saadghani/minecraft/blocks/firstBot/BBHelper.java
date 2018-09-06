package com.saadghani.minecraft.blocks.firstBot;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BBHelper {


    public static BlockPos getGroundAboveSeaLevel(World worldIn, BlockPos pos)
    {
        BlockPos blockpos;

        for (blockpos = new BlockPos(pos.getX(), worldIn.getSeaLevel(), pos.getZ()); !worldIn.isAirBlock(blockpos.up()); blockpos = blockpos.up())
        {
            ;
        }

        return blockpos;
    }

    public static ArrayList getWorldDetails(BlockPos pos, World world){
        ArrayList<String> row = new ArrayList<>();
        IBlockState me = world.getBlockState(pos);
        for(int i = -2; i <= 2; i++){
            for(int k = -2; k <= 2; k++){
                BlockPos gPos = pos.add(i,0,k);
                gPos = getGroundAboveSeaLevel(world, gPos);
                gPos = (i==0 && k==0) ? gPos.down(): gPos;
                IBlockState block = world.getBlockState(gPos);
                while(block == me){
                    gPos = gPos.down();
                    block = world.getBlockState(gPos);
                }
                //row.add(String.valueOf(i)); //x
                row.add(String.valueOf(gPos.getY())); //ratio data
                //row.add(String.valueOf(k)); //z
                row.add(String.valueOf(Block.getStateId(block))); //nominal data

            }
        }
        row.add(String.valueOf(Biome.getIdForBiome(world.getBiome(pos)))); //nominal data

        return row;
    }

    public static boolean writeDataToFile(ArrayList data, String file){

        try {
            String line = String.join(",",data);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.println(line);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }


}
