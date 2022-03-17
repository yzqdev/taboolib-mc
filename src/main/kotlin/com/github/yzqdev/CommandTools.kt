package com.github.yzqdev

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader

@CommandHeader("fltools", ["ft"], "FlTools主命令", permission = "fltools.access")
object CommandTools {



    @CommandBody(permission = "fltools.command.sendtoast")
    val sendToast = CommandSendToast.command

    @CommandBody(permission = "fltools.command.lore")
    val lore = CommandLore.command
}