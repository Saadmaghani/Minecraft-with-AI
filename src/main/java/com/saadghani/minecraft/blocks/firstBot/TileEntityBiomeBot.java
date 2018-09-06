package com.saadghani.minecraft.blocks.firstBot;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.swing.plaf.PanelUI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.ArrayList;

public class TileEntityBiomeBot extends TileEntity {

    EntityPlayer player=null;

    public EntityPlayer getPlayer(){return player;}
    TileEntityBiomeBot(EntityPlayer p){
        super();
        player = p;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB infiniteExample = INFINITE_EXTENT_AABB;
        return infiniteExample;

    }

}
