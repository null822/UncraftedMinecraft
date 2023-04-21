package com.null8.uncraftedminecraft.mixin.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlockRegistryMixin
{
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void register(String name, Block block, CallbackInfoReturnable<Block> cir)
    {
        if (name.equals("air") || name.equals("cave_air") || name.equals("void_air"))
        {
            cir.setReturnValue(Registry.register(Registry.BLOCK, name, new AirBlock(BlockBehaviour.Properties.of(Material.AIR).noCollission().noDrops().air())));
        } else {
            cir.cancel();
        }
    }
}
