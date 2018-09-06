package com.saadghani.minecraft.blocks.firstBot;

import com.saadghani.minecraft.blocks.BlockBase;
import com.saadghani.minecraft.blocks.BlockTileEntity;
import jline.internal.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BBStationary extends BlockBase {

    EntityPlayer player;

    public BBStationary(){
        super(Material.ROCK, "bb_stationary");
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        BlockPos gpos = BBHelper.getGroundAboveSeaLevel(worldIn, pos);

        ArrayList<String> data = BBHelper.getWorldDetails(gpos, worldIn);
        BBHelper.writeDataToFile(data, "data5.csv");

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        BlockPos gpos = BBHelper.getGroundAboveSeaLevel(worldIn, pos);

        ArrayList<String> data = BBHelper.getWorldDetails(gpos, worldIn);
        BBHelper.writeDataToFile(data, "data4.csv");
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
