package mod.functionalbits.tileentity;

import mod.functionalbits.ModFunctionalBits;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SimpleTESR extends TileEntitySpecialRenderer<SimpleFunctionalTileEntity> {
	
	private final ItemStack FALLBACK_ITEM;
	
	public SimpleTESR() {
		super();
		FALLBACK_ITEM = new ItemStack(ModFunctionalBits.instance.modBlocks.simpleFunctionalBlock);
	}
	
	@Override
	public void renderTileEntityAt(SimpleFunctionalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(.5, .5, .5);
		GlStateManager.rotate(te.rotX, 1, 0, 0);
		GlStateManager.rotate(te.rotY, 0, 1, 0);
		GlStateManager.rotate(te.rotZ, 0, 0, 1);
		if(te.getAsItemStack() != null) {
			GlStateManager.scale(2, 2, 2);
			Minecraft.getMinecraft().getRenderItem().renderItem(te.getAsItemStack(), ItemCameraTransforms.TransformType.NONE);
		}
		else {
			Minecraft.getMinecraft().getRenderItem().renderItem(FALLBACK_ITEM, ItemCameraTransforms.TransformType.NONE);
		}
		GlStateManager.popMatrix();
	}

}
