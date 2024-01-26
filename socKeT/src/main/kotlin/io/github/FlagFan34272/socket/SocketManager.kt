package io.github.FlagFan34272.socket

import org.bukkit.plugin.java.JavaPlugin

class SocketManager: JavaPlugin() {
    companion object {
        lateinit var INSTANCE: SocketManager
            private set
    }

    override fun onEnable() {
        INSTANCE = this
        SocketServer(31323).defineSocketConnection()
    }
}