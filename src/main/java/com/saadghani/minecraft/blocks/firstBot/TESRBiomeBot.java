package com.saadghani.minecraft.blocks.firstBot;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.util.ArrayList;
public class TESRBiomeBot extends TileEntitySpecialRenderer<TileEntityBiomeBot> {

    private int delayTicks = 10;

    /*
       0 - don't add
       1 - 1 up
       2 - {1 + 2i} left
       3 - {2 + 2i} down
       4 - {2 + 2i} right
       5 - {2 + 2i} up
       i++
       i = squares
    */
    private int stepNo = 0;
    private int squares = 0;
    private int loops = 0;
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
            BlockPos toAdd = new BlockPos(0,0,0);

            switch (stepNo){
                case 0:
                    toAdd = new BlockPos(0,0,0);
                    stepNo++;
                    break;
                case 1:
                    toAdd = new BlockPos(0,0,5);
                    stepNo++;
                    break;
                case 2:
                    toAdd = new BlockPos(-5,0,0);
                    loops++;
                    if(loops == 1+2*squares){
                        stepNo++;
                        loops = 0;
                    }
                    break;
                case 3:
                    toAdd = new BlockPos(0,0,-5);
                    loops++;
                    if(loops == 2+2*squares){
                        stepNo++;
                        loops=0;
                    }
                    break;
                case 4:
                    toAdd = new BlockPos(5,0,0);
                    loops++;
                    if(loops == 2+2*squares){
                        stepNo++;
                        loops=0;
                    }
                    break;
                case 5:
                    toAdd = new BlockPos(0,0,5);
                    loops++;
                    if(loops==2+2*squares){
                        loops=0;
                        stepNo=1;
                        squares++;
                    }
                    break;
            }
            System.out.println("toAddX: "+toAdd.getX()+ " toAddY: "+ toAdd.getY()+ " toAddZ: "+toAdd.getZ());
            BlockPos newPos = BBHelper.getGroundAboveSeaLevel(world, te.getPos().add(toAdd)).add(0,1,0);

            IBlockState block = world.getBlockState(pos);
            te.setPos(newPos);
            world.setBlockState(newPos, block);
            world.setBlockToAir(pos);
            EntityPlayer player = te.getPlayer();
            player.setPositionAndUpdate(newPos.getX(), newPos.getY()+10, newPos.getZ());

            ArrayList<String> data = BBHelper.getWorldDetails(newPos, world);
            BBHelper.writeDataToFile(data, "data3.csv");
            System.out.println("stepNo: "+stepNo+" pos: "+newPos.getX()+" "+newPos.getY()+" "+newPos.getZ());
        }
    }

}
