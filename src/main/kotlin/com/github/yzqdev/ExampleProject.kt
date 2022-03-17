package com.github.yzqdev

import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.common.platform.function.info

object ExampleProject : Plugin() {

    override fun onEnable() {
        info("Successfully running ExamplePlugin!")
    }

    override fun onLoad() {
        console()    // 打印插件名称
        console(    ).sendMessage(Bukkit.getVersion())
    }
}