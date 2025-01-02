package cn.kongchengli.tweak

import cn.kongchengli.tweak.command.CommandRegistry
import cn.kongchengli.tweak.listener.ItemStackListener
import cn.kongchengli.tweak.listener.ShulkerBoxListener
import net.fabricmc.api.DedicatedServerModInitializer
import net.minecraft.util.Identifier

class Tweak : DedicatedServerModInitializer {
    companion object {
        const val MOD_ID = "tweak"
        
        fun identifier(path: String): Identifier {
            return Identifier(MOD_ID, path)
        }
    }

    override fun onInitializeServer() {
        // 注册事件监听器
        ShulkerBoxListener.register()
        ItemStackListener.register()
        // 注册命令
        CommandRegistry.register()
    }
}
