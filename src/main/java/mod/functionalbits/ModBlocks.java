package mod.functionalbits;

import mod.functionalbits.block.SimpleFunctionalBlock;
import mod.functionalbits.tileentity.SimpleFunctionalTileEntity;
import mod.functionalbits.tileentity.SimpleTESR;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public SimpleFunctionalBlock simpleFunctionalBlock;
	
	public void preInit() {
		
		simpleFunctionalBlock = new SimpleFunctionalBlock();
		GameRegistry.register(simpleFunctionalBlock);
		GameRegistry.register(new ItemBlock(simpleFunctionalBlock), simpleFunctionalBlock.getRegistryName());
		GameRegistry.registerTileEntity(SimpleFunctionalTileEntity.class, "simple_functional_tileentity");
	}

	@SideOnly(Side.CLIENT)
	public void preInitClient() {
		ClientRegistry.bindTileEntitySpecialRenderer(SimpleFunctionalTileEntity.class, new SimpleTESR());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModFunctionalBits.instance.modBlocks.simpleFunctionalBlock), 0, new ModelResourceLocation("functionalbits" + ":" + "simple_functional_block", "inventory"));
	}

}
