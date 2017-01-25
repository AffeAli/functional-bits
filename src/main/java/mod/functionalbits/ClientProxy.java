package mod.functionalbits;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit() {
		super.preInit();
		ModFunctionalBits.instance.modBlocks.preInitClient();
		ModFunctionalBits.instance.modItems.preInitClient();
	}

}
