package cn.kongchengli.tweak.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object CommandRegistry {
    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            registerShulkerBoxCommand(dispatcher)
        }
    }

    private fun registerShulkerBoxCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(CommandManager.literal("shulkerbox")
            .executes { context ->
                context.source.player?.sendMessage(Text.literal("ShulkerBox command executed!"), false)
                1
            }
        )
    }
} 