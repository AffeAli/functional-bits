package mod.functionalbits;

import mod.functionalbits.network.SetRemoteBlockPacket;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	
	public void preInit()  {
		ModFunctionalBits.instance.modBlocks = new ModBlocks();
		ModFunctionalBits.instance.modBlocks.preInit();
		ModFunctionalBits.instance.modItems = new ModItems();
		ModFunctionalBits.instance.modItems.preInit();
	}
	
	public void init() {
		ModFunctionalBits.NETWORK_WRAPPER.registerMessage(SetRemoteBlockPacket.Handler.class, SetRemoteBlockPacket.class, 0, Side.SERVER);
	}

}
