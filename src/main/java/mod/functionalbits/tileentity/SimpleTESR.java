package mod.functionalbits.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class SimpleTESR extends TileEntitySpecialRenderer<SimpleFunctionalTileEntity> {
	
	@Override
	public void renderTileEntityAt(SimpleFunctionalTileEntity te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(2, 2, 2);
		GlStateManager.translate(.25, .25, .25);
		Minecraft.getMinecraft().getRenderItem().renderItem(te.getAsItemStack(), ItemCameraTransforms.TransformType.NONE);
		GlStateManager.popMatrix();
	}

}
