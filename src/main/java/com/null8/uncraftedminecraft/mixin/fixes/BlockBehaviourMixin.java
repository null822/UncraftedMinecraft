package com.null8.uncraftedminecraft.mixin.fixes;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.Properties.class)
public abstract class BlockBehaviourMixin
{
    @Inject(method = "copy", at = @At("HEAD"), cancellable = true)
    private static void copy(BlockBehaviour blockBehaviour, CallbackInfoReturnable<BlockBehaviour.Properties> cir)
    {
        if (blockBehaviour == null) {
            cir.setReturnValue(BlockBehaviour.Properties.copy(Blocks.AIR));
            cir.cancel();
        }

    }
}

