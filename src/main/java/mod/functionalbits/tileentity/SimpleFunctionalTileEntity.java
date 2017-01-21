package mod.functionalbits.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class SimpleFunctionalTileEntity extends TileEntity {
	
	ItemStack item;

	public ItemStack getAsItemStack() {
		return item;
	}
	
	public void setStructure(ItemStack stack) {
		item = stack;
	}

	public boolean rightClickAction(EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		BlockPos newPos = pos.add(0, -1, 0);
		return getWorld().getBlockState(newPos).getBlock().onBlockActivated(getWorld(), newPos, getWorld().getBlockState(newPos), playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	

}
