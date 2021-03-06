package mod.functionalbits.item;

import mod.functionalbits.ModFunctionalBits;
import mod.functionalbits.interfaces.RemoteBlockContainer;
import mod.functionalbits.network.SetRemoteBlockPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFunctionalBinder extends Item {

	public ItemFunctionalBinder() {
		setRegistryName("functional_binder");
		setUnlocalizedName(getRegistryName().toString());
		setMaxStackSize(1);
		setMaxDamage(9);
	}
	
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumFacing facing, float hitX, float hitY, float hitZ, EnumHand hand) {
		try {
			if(!((RemoteBlockContainer) worldIn.getTileEntity(pos)).setRemoteBlock(getSavedPos(stack))) return EnumActionResult.FAIL;
			stack.damageItem(1, playerIn);
			ModFunctionalBits.NETWORK_WRAPPER.sendToServer(new SetRemoteBlockPacket(pos, getSavedPos(stack)));
		}
		catch(ClassCastException | NullPointerException e) {
			savePos(stack, pos);
			ModFunctionalBits.NETWORK_WRAPPER.sendToServer(new SetRemoteBlockPacket(pos, BlockPos.ORIGIN));
		}
		return EnumActionResult.SUCCESS;
	}

	private BlockPos getSavedPos(ItemStack stack) {
		NBTTagCompound nbt;
		if((nbt = stack.getTagCompound()) != null) {
			return new BlockPos(
			nbt.getInteger("remoteX"),
			nbt.getInteger("remoteY"),
			nbt.getInteger("remoteZ"));
		}
		return null;
	}

	private void savePos(ItemStack stack, BlockPos pos) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("remoteX", pos.getX());
		nbt.setInteger("remoteY", pos.getY());
		nbt.setInteger("remoteZ", pos.getZ());
		stack.setTagCompound(nbt);
	}
	
}
