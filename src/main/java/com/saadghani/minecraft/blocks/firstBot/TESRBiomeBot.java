package com.saadghani.minecraft.blocks.firstBot;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.io.*;

public class TESRBiomeBot extends TileEntitySpecialRenderer<TileEntityBiomeBot> {

    private int delayTicks = 10;

    @Override
    public boolean isGlobalRenderer(TileEntityBiomeBot te) {
        return true;
    }

    @Override
    public void render(TileEntityBiomeBot te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
       if(te.getPlayer()==null) return;
        delayTicks--;
        if (delayTicks<=0){
            delayTicks=10;

            World world = Minecraft.getMinecraft().world;
            BlockPos pos = te.getPos();
            BlockPos newPos = getGroundAboveSeaLevel(world, te.getPos().add(5,0,5)).add(0,1,0);

            IBlockState block = world.getBlockState(pos);
            te.setPos(newPos);
            //world.setBlockState(newPos, block);
            //world.setBlockToAir(pos);
            EntityPlayer player = te.getPlayer();
            player.setPositionAndUpdate(newPos.getX(), newPos.getY()+10, newPos.getZ());
            ArrayList<String> data = row(newPos, world);

            try {
                String line = String.join(",",data);
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("data3.txt", true)));
                writer.println(line);
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("pos: "+pos.getX()+" "+pos.getY()+" "+pos.getZ());
        }
    }

    private ArrayList row(BlockPos pos, World world){
        ArrayList<String> row = new ArrayList<String>();

        for(int i = -2; i <= 2; i++){
            for(int k = -2; k <= 2; k++){
                BlockPos gPos = pos.add(i,0,k);
                gPos = getGroundAboveSeaLevel(world, gPos);
                IBlockState block = world.getBlockState(gPos);

                row.add(String.valueOf(i));
                row.add(String.valueOf(gPos.getY()));
                row.add(String.valueOf(k));
                row.add(String.valueOf(Block.getStateId(block)));
            }
        }
        row.add(String.valueOf(Biome.getIdForBiome(world.getBiome(pos))));

        return row;
    }
    private BlockPos getGroundAboveSeaLevel(World worldIn, BlockPos pos)
    {
        BlockPos blockpos;

        for (blockpos = new BlockPos(pos.getX(), worldIn.getSeaLevel(), pos.getZ()); !worldIn.isAirBlock(blockpos.up()); blockpos = blockpos.up())
        {
            ;
        }

        return blockpos;
    }

}
