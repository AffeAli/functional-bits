package mod.functionalbits.tileentity;

import mod.chiselsandbits.chiseledblock.NBTBlobConverter;
import mod.chiselsandbits.chiseledblock.data.VoxelBlob;
import mod.chiselsandbits.helpers.ModUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class SimpleFunctionalTileEntity extends TileEntity {
	
	private ItemStack item;
	private VoxelBlob blob;

	public ItemStack getAsItemStack() {
		return item;
	}
	
	public void setStructure(ItemStack stack) {
		setItem(stack);
		markDirty();
		world.checkLightFor(EnumSkyBlock.BLOCK, pos);
	}

	public boolean rightClickAction(EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		BlockPos newPos = pos.add(0, -1, 0);
		return getWorld().getBlockState(newPos).getBlock().onBlockActivated(getWorld(), newPos, getWorld().getBlockState(newPos), playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagCompound itemStack = (NBTTagCompound) compound.getTag("structure");
		if(itemStack != null) setItem(ItemStack.loadItemStackFromNBT(itemStack));
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

	public int getLight() {
		if(item == null) return 0;
		float light = blob.getVoxelStats().blockLight;
		return Math.max(0, Math.min(15, (int)(light * 15)));
	}
	
	private void setItem(ItemStack newItem) {
		item = newItem;
		NBTBlobConverter c = new NBTBlobConverter();
		c.readChisleData(item.getSubCompound(ModUtil.NBT_BLOCKENTITYTAG, false));
		blob = c.getBlob();
	}
	

}
