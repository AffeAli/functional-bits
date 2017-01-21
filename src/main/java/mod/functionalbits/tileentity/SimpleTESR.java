package mod.functionalbits.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
