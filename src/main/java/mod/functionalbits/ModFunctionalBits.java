package mod.functionalbits;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "functionalbits")
public class ModFunctionalBits {
	
	public ModBlocks modBlocks;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		modBlocks = new ModBlocks();
		modBlocks.preInit();
	}
	
}
