package com.saadghani.minecraft.blocks.firstBot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

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
