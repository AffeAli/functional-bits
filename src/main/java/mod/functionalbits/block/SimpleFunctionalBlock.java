package mod.functionalbits.block;

import mod.functionalbits.tileentity.SimpleFunctionalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SimpleFunctionalBlock extends Block implements ITileEntityProvider {

	public SimpleFunctionalBlock() {
		super(Material.ROCK, MapColor.AIR);
		setRegistryName("simple_functional_block");
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		SimpleFunctionalTileEntity te = (SimpleFunctionalTileEntity) world.getTileEntity(pos);
		return te.getLight();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		SimpleFunctionalTileEntity te = (SimpleFunctionalTileEntity) worldIn.getTileEntity(pos);
		if(heldItem != null && heldItem.getUnlocalizedName().contains("chiselsandbits.chiseled_")) {
			te.setStructure(heldItem);
			return true;
		}
		return te.rightClickAction(playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SimpleFunctionalTileEntity();
	}
	
	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		return super.rotateBlock(world, pos, axis);
	}
	
	

}
