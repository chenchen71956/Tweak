package cn.kongchengli.tweak.mixin

import net.minecraft.block.ShulkerBoxBlock
import net.minecraft.item.BlockItem
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(BlockItem::class)
abstract class ShulkerBoxItemMixin {
    @Shadow abstract fun getBlock(): net.minecraft.block.Block

    @Inject(at = [At("HEAD")], method = ["getMaxCount()I"], cancellable = true)
    private fun onGetMaxCount(callbackInfo: CallbackInfoReturnable<Int>) {
        if (getBlock() is ShulkerBoxBlock) {
            callbackInfo.returnValue = 64
        }
    }

    @Inject(at = [At("HEAD")], method = ["canBeNested()Z"], cancellable = true)
    private fun onCanBeNested(callbackInfo: CallbackInfoReturnable<Boolean>) {
        if (getBlock() is ShulkerBoxBlock) {
            callbackInfo.returnValue = true
        }
    }
} 