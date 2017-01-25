package mod.functionalbits;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = "functionalbits")
public class ModFunctionalBits {
	
	public static final String VERSION = "";
	public static final String MODID = "functionalbits";
	
	public ModBlocks modBlocks;
	public ModItems modItems;

	@SidedProxy(
	clientSide="mod.functionalbits.ClientProxy", 
	serverSide="mod.functionalbits.CommonProxy")
	public static CommonProxy proxy;
	public static ModFunctionalBits instance;
	public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	public ModFunctionalBits() {
		instance = this;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		modBlocks.registerRecipies();
		modItems.registerRecipies();
	}
	
}
