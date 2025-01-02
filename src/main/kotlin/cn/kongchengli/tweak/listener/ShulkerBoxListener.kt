package cn.kongchengli.tweak.listener

import cn.kongchengli.tweak.util.InteractionUtil
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.util.TypedActionResult

object ShulkerBoxListener {
    fun register() {
        UseItemCallback.EVENT.register { player, world, hand ->
            val stack = player.getStackInHand(hand)
            if (InteractionUtil.isShulkerBox(stack)) {
                if (!world.isClient) {
                    InteractionUtil.openBlockUI(player, stack)
                }
                TypedActionResult.success(stack)
            } else {
                TypedActionResult.pass(stack)
            }
        }
    }
} 