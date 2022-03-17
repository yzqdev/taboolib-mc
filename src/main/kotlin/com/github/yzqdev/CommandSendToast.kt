package com.github.yzqdev

import org.bukkit.Bukkit
import org.bukkit.Material
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.onlinePlayers
import taboolib.module.chat.colored
import taboolib.module.nms.sendToast
import taboolib.module.nms.type.ToastFrame
object CommandSendToast {

    val command = subCommand {
        // player
        dynamic {
            suggestion<ProxyCommandSender> { _, _ ->
                onlinePlayers().map { it.name }
            }
            // toast frame
            dynamic {
                suggestion<ProxyCommandSender> { _, _ ->
                    ToastFrame.values().map { it.toString() }
                }
                // material
                dynamic {
                    suggestion<ProxyCommandSender> { _, _ ->
                        Material.values().map { it.toString() }
                    }
                    // message
                    dynamic {
                        execute<ProxyCommandSender> { _, context, argument ->
                            val player = Bukkit.getPlayer(context.argument(-3)!!)
                            player ?: kotlin.run {
                                return@execute
                            }
                            player.sendToast(
                                Material.valueOf(context.argument(-1)!!),
                                argument.colored(),
                                ToastFrame.valueOf(context.argument(-2)!!)
                            )
                        }
                    }
                }
            }
        }
    }
}