package mod.functionalbits;

import mod.functionalbits.item.ItemFunctionalBinder;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
	
	public void registerRecipies(){
		GameRegistry.addRecipe(new ShapedOreRecipe(functionalBinder, " W ", "WbW", " W ", 'W', new ItemStack(ModFunctionalBits.instance.modBlocks.simpleFunctionalBlock, 1), 'b', new ItemStack(Items.ENDER_PEARL, 1, OreDictionary.WILDCARD_VALUE)));
	}

}
