package com.null8.uncraftedminecraft.mixin.fixes;

import com.null8.uncraftedminecraft.UncraftedMinecraft;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CancellationException;

@Mixin(Block.class)
public abstract class BlockMixin
{
    @Shadow private BlockState defaultBlockState;

    @Shadow protected abstract Block asBlock();

    @Inject(method = "defaultBlockState", at = @At("HEAD"), cancellable = true)
    private void defaultBlockstate(CallbackInfoReturnable<BlockState> cir)
    {
        if (this.asBlock() == null) {
            UncraftedMinecraft.LOGGER.error(this.asBlock().getName().getString() + " | fail");
            cir.cancel();
        } else {
            UncraftedMinecraft.LOGGER.debug(this.asBlock().getName().getString() + " | success | " + this.defaultBlockState.toString());
            cir.setReturnValue(this.defaultBlockState);

            cir.cancel();

        }

        cir.cancel();

    }
}

