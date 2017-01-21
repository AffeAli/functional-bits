package mod.functionalbits;

public class CommonProxy {
	
	public void preInit()  {
		ModFunctionalBits.instance.modBlocks = new ModBlocks();
		ModFunctionalBits.instance.modBlocks.preInit();
	}

}
