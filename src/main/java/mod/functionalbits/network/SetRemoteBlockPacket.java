package mod.functionalbits.network;

import io.netty.buffer.ByteBuf;
import mod.functionalbits.interfaces.RemoteBlockContainer;
import mod.functionalbits.item.ItemFunctionalBinder;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SetRemoteBlockPacket implements IMessage {
	
	BlockPos changing;
	BlockPos to;
	
	public SetRemoteBlockPacket() {
	}
	
	public SetRemoteBlockPacket(BlockPos changing, BlockPos to) {
		this.changing = changing;
		this.to = to;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		changing = buffer.readBlockPos();
		to = buffer.readBlockPos();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		buffer.writeBlockPos(changing);
		buffer.writeBlockPos(to);
	}
	
	public static class Handler implements IMessageHandler<SetRemoteBlockPacket, IMessage> {

		@Override
		public IMessage onMessage(SetRemoteBlockPacket message, MessageContext ctx) {
			EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
			if(message.to.equals(BlockPos.ORIGIN)) {
				ItemStack stack = playerEntity.getHeldItemMainhand();
				if(stack.getItem() instanceof ItemFunctionalBinder) {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setInteger("remoteX", message.changing.getX());
					nbt.setInteger("remoteY", message.changing.getY());
					nbt.setInteger("remoteZ", message.changing.getZ());
					stack.setTagCompound(nbt);
					playerEntity.sendMessage(new TextComponentString("Saved Block \"" + playerEntity.world.getBlockState(message.changing).getBlock().getLocalizedName() + "\""));
				}
				return null;
			}
			((RemoteBlockContainer) playerEntity.world.getTileEntity(message.changing)).setRemoteBlock(message.to);
			ItemStack stack;
			if((stack = playerEntity.getHeldItemMainhand()) != null && stack.getItem() instanceof ItemFunctionalBinder) {
				stack.damageItem(1, playerEntity);
			}
			playerEntity.sendMessage(new TextComponentString("Bound Block to " + playerEntity.world.getBlockState(message.to).getBlock().getLocalizedName()));
			return null;
		}
		
	}

}
