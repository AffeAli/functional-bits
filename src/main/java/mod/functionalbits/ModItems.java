package mod.functionalbits;

import mod.functionalbits.item.ItemFunctionalBinder;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public ItemFunctionalBinder functionalBinder;
	
	public void preInit() {
		functionalBinder = new ItemFunctionalBinder();
		GameRegistry.register(functionalBinder);
	}

	@SideOnly(Side.CLIENT)
	public void preInitClient() {
		ModelLoader.setCustomModelResourceLocation(functionalBinder, 0, new ModelResourceLocation("functionalbits" + ":" + "functional_binder", "inventory"));
	}

}
