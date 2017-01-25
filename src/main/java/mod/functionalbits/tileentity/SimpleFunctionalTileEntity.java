package mod.functionalbits.tileentity;

import mod.chiselsandbits.chiseledblock.NBTBlobConverter;
import mod.chiselsandbits.chiseledblock.data.VoxelBlob;
import mod.chiselsandbits.helpers.ModUtil;
import mod.functionalbits.interfaces.RemoteBlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class SimpleFunctionalTileEntity extends TileEntity implements RemoteBlockContainer {
	
	private ItemStack item;
	private VoxelBlob blob;
	int rotX, rotY, rotZ;
	private BlockPos remoteBlock = null;

	public ItemStack getAsItemStack() {
		return item;
	}
	
	public void setStructure(ItemStack stack) {
		setItem(stack);
		markDirty();
		world.checkLightFor(EnumSkyBlock.BLOCK, pos);
	}

	public boolean rightClickAction(EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		//BlockPos newPos = getPos().down();
		BlockPos newPos = getRemoteBlockPos();
		System.out.println(remoteBlock);
		return getWorld().getBlockState(newPos).getBlock().onBlockActivated(getWorld(), newPos, getWorld().getBlockState(newPos), playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagCompound itemStack = (NBTTagCompound) compound.getTag("structure");
		if(itemStack != null) setItem(ItemStack.loadItemStackFromNBT(itemStack));
		rotX = compound.getInteger("rotX");
		rotY = compound.getInteger("rotY");
		rotZ = compound.getInteger("rotZ");
		remoteBlock = new BlockPos(compound.getInteger("remoteX"), compound.getInteger("remoteY"), compound.getInteger("remoteZ"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(item != null) {
			NBTTagCompound itemTag = item.writeToNBT(new NBTTagCompound());
			compound.setTag("structure", itemTag);
		}
		compound.setInteger("rotX", rotX);
		compound.setInteger("rotY", rotY);
		compound.setInteger("rotZ", rotZ);
		compound.setInteger("remoteX", getRemoteBlockPos().getX());
		compound.setInteger("remoteY", getRemoteBlockPos().getY());
		compound.setInteger("remoteZ", getRemoteBlockPos().getZ());
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

	public boolean rotate(EnumFacing axis) {
		if(item == null) return false;
		switch (axis) {
		case UP:
			rotY += 90;
			break;
		case DOWN:
			rotY += 90;
			break;
		case NORTH:
			rotZ += 90;
			break;
		case SOUTH:
			rotZ += 90;
			break;
		case WEST:
			rotX += 90;
			break;
		case EAST:
			rotX += 90;
			break;
		}
		if(rotX >= 360) rotX -= 360;
		if(rotY >= 360) rotY -= 360;
		if(rotZ >= 360) rotZ -= 360;
		return true;
	}

	@Override
	public boolean setRemoteBlock(BlockPos pos) {
		if(pos != null && pos.distanceSq(getPos()) < 50) {
			remoteBlock = pos;
			markDirty();
			return true;
		}
		return false;
	}
	
	private BlockPos getRemoteBlockPos() {
		return remoteBlock != null ? remoteBlock : getPos().down();
	}
	

}
