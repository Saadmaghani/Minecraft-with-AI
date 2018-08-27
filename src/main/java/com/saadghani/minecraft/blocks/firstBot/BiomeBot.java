package com.saadghani.minecraft.blocks.firstBot;

import com.saadghani.minecraft.blocks.BlockTileEntity;
import jline.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeBot extends BlockTileEntity<TileEntityBiomeBot> {

    public BiomeBot(){
        super(Material.ROCK, "biome_bot");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote){

            for(int i = -2; i <= 2; i++){
                for(int k = -2; k <= 2; k++){
                    BlockPos gPos = pos.add(i,0,k);
                    gPos = getGroundAboveSeaLevel(worldIn, gPos);
                    IBlockState block = worldIn.getBlockState(gPos);

                    playerIn.sendMessage(new TextComponentString("x: "+i+ " y: "+gPos.getY()+" z: "+k+" id: "+ Block.getStateId(block)));
                }
            }

            playerIn.sendMessage(new TextComponentString("biome: "+ Biome.getIdForBiome(playerIn.world.getBiome(pos))));

        }
        return true;
    }

    @Override
    public  Class<TileEntityBiomeBot> getTileEntityClass(){
        return TileEntityBiomeBot.class;
    }

    @Nullable
    @Override
    public TileEntityBiomeBot createTileEntity(World world, IBlockState state){
        return new TileEntityBiomeBot();
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
