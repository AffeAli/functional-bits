package mod.functionalbits;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "functionalbits")
public class ModFunctionalBits {
	
	public ModBlocks modBlocks;
	public ModItems modItems;

	@SidedProxy(
	clientSide="mod.functionalbits.ClientProxy", 
	serverSide="mod.functionalbits.CommonProxy")
	public static CommonProxy proxy;
	public static ModFunctionalBits instance;
	
	public ModFunctionalBits() {
		instance = this;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		modBlocks.registerRecipies();
	}
	
}
