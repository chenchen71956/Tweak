package cn.kongchengli.tweak.listener

import cn.kongchengli.tweak.util.InteractionUtil
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.util.TypedActionResult

object ItemStackListener {
    fun register() {
        UseItemCallback.EVENT.register { player, world, hand ->
            val stack = player.getStackInHand(hand)
            if (!world.isClient && InteractionUtil.isShulkerBox(stack) && !InteractionUtil.canStackShulkerBox(stack)) {
                try {
                    val field = stack.javaClass.getDeclaredField("count")
                    field.isAccessible = true
                    field.setInt(stack, 1)
                } catch (e: Exception) {
                    // 忽略错误
                }
            }
            TypedActionResult.pass(stack)
        }
    }
} 