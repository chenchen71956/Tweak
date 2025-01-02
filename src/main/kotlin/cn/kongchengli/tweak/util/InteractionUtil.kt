package cn.kongchengli.tweak.util

import net.minecraft.block.ShulkerBoxBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.screen.ShulkerBoxScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.inventory.SimpleInventory

object InteractionUtil {
    fun isShulkerBox(stack: ItemStack): Boolean {
        return stack.item is BlockItem && (stack.item as BlockItem).block is ShulkerBoxBlock
    }

    fun openBlockUI(player: PlayerEntity, stack: ItemStack) {
        if (stack.count > 1) {
            return
        }

        val inventory = SimpleInventory(27)
        val nbt = stack.nbt
        if (nbt?.contains("BlockEntityTag", 10) == true) {
            val blockEntityTag = nbt.getCompound("BlockEntityTag")
            if (blockEntityTag.contains("Items", 9)) {
                DefaultedList.ofSize(27, ItemStack.EMPTY).apply {
                    blockEntityTag.getList("Items", 10).forEach { itemTag ->
                        if (itemTag is NbtCompound) {
                            val slot = itemTag.getByte("Slot").toInt()
                            if (slot in 0..26) {
                                inventory.setStack(slot, ItemStack.fromNbt(itemTag))
                            }
                        }
                    }
                }
            }
        }

        player.openHandledScreen(SimpleNamedScreenHandlerFactory(
            { syncId, playerInventory, _ ->
                ShulkerBoxScreenHandler(syncId, playerInventory, inventory)
            },
            getBlockTitle(stack)
        ))
    }

    private fun getBlockTitle(stack: ItemStack): Text {
        return if (stack.hasCustomName()) {
            stack.name
        } else {
            Text.translatable("container.shulkerBox")
        }
    }

    fun canStackShulkerBox(stack: ItemStack): Boolean {
        val nbt = stack.nbt ?: return true
        if (!nbt.contains("BlockEntityTag", 10)) return true
        val blockEntityTag = nbt.getCompound("BlockEntityTag")
        return !blockEntityTag.contains("Items", 9)
    }
} 