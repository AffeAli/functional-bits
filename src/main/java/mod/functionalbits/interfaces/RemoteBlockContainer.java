package mod.functionalbits.interfaces;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;

public interface RemoteBlockContainer {
	
	public boolean setRemoteBlock(@Nullable BlockPos pos);

}
