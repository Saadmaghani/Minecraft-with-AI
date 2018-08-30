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

    EntityPlayer player;

    public BiomeBot(){
        super(Material.ROCK, "biome_bot");
    }

    @Override
    public  Class<TileEntityBiomeBot> getTileEntityClass(){
        return TileEntityBiomeBot.class;
    }

    @Nullable
    @Override
    public TileEntityBiomeBot createTileEntity(World world, IBlockState state){
        EntityPlayer player = world.playerEntities.get(0);
        return new TileEntityBiomeBot(player);
    }




}
