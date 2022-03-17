package com.github.yzqdev
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand
import taboolib.common.util.addSafely
import taboolib.common5.Coerce
import taboolib.module.chat.colored
import taboolib.platform.util.isAir
import taboolib.platform.util.modifyLore
object CommandLore {


    val command = subCommand {
        literal("append") {
            // text
            dynamic {
                execute<Player> { sender, _, argument ->
                   val  playerInventory= sender.inventory
                   val itemStack= playerInventory.itemInMainHand
                    if (itemStack.isAir()) {
                        return@execute
                    }
                    itemStack.modifyLore {
                        add(argument.colored())
                    }
                }
            }
        }
        literal("insert") {
            // line
            dynamic {
                suggestion<Player>(uncheck = true) { sender, _ ->
                    val  playerInventory= sender.inventory
                    val itemStack= playerInventory.itemInMainHand
                    (1..(itemStack.itemMeta?.lore?.size ?: 1)).map { it.toString() }
                }
                restrict<Player> { _, _, argument ->
                    Coerce.asInteger(argument).isPresent
                }
                // text
                dynamic {
                    execute<Player> { sender, context, argument ->
                        val  playerInventory= sender.inventory
                        val itemStack= playerInventory.itemInMainHand
                        if (itemStack.isAir()) {
                            return@execute
                        }
                        itemStack.modifyLore {
                            addSafely(context.argument(-1)!!.toInt()-1, argument.colored(), "")
                        }
                    }
                }
            }
        }
        literal("pop") {
            // line
            dynamic(optional = true) {
                restrict<Player> { _, _, argument ->
                    Coerce.asInteger(argument).isPresent
                }
                execute<Player> { sender, _, argument ->
                    val  playerInventory= sender.inventory
                    val itemStack= playerInventory.itemInMainHand
                    if (itemStack.isAir()) {
                        return@execute
                    }
                    itemStack.modifyLore {
                        try {
                            sender.sendMessage("已移除Lore: ${this.removeAt(argument.toInt()-1)}")
                        }
                        catch (e: IndexOutOfBoundsException) {
                            sender.sendMessage("该物品第${argument}行没有Lore!")
                        }
                    }
                }
            }
            execute<Player> { sender, _, _ ->
                val  playerInventory= sender.inventory
                val itemStack= playerInventory.itemInMainHand
                if (itemStack.isAir()) {
                    return@execute
                }
                itemStack.modifyLore {
                    if (isEmpty()) return@modifyLore
                    this.removeLast()
                }
            }
        }
    }
}