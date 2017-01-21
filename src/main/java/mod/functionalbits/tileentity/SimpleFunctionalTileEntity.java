package mod.functionalbits.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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
		markDirty();
	}

	public boolean rightClickAction(EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		BlockPos newPos = pos.add(0, -1, 0);
		return getWorld().getBlockState(newPos).getBlock().onBlockActivated(getWorld(), newPos, getWorld().getBlockState(newPos), playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagCompound itemStack = (NBTTagCompound) compound.getTag("structure");
		if(itemStack != null) item = ItemStack.loadItemStackFromNBT(itemStack);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(item != null) {
			NBTTagCompound itemTag = item.writeToNBT(new NBTTagCompound());
			compound.setTag("structure", itemTag);
		}
		return compound;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	

}
