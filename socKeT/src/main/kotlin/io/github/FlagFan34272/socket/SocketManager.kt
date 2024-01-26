package io.github.FlagFan34272.socket

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SocketManager: JavaPlugin() {
    companion object {
        lateinit var INSTANCE: SocketManager
            private set
    }

    override fun onEnable() {
        INSTANCE = this
        saveDefaultConfig();
        if (!config.contains("Settings.Port")) {
            config.options().header("""
                SocKetlin configuration file.
                Settings:
                    Port: (default) 31323
                    Settings.Socket-Client-Data-Code: (default) "!!WebServer!!"
                
                Settings.Socket-Client-Data-Code is for a client which sends socket data to the server.
                usage example: (Python 3.10)
                    server_address = ('localhost', 31323)
                    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    client_socket.setsockopt(socket.SOL_SOCKET, socket.SO_KEEPALIVE, 1)
                    try:
                        client_socket.connect(server_address)
                        pwd = "!!WebServer!!"
                        client_socket.sendto(pwd.encode(), server_address)
                
                        data = client_socket.recv(1024)
                        print(f"SERVER | {data.decode()}")
                
                        while True:
                            print("input Commands for server")
                            i = input()
                
                            print("Sent Successfully!") # command dispatch is a synchoronous task.
                            client_socket.sendto(i.encode(), server_address)
                
                
                        except Exception as e:
                            print(f"CLIENT | ERR: {e}")
            """.trimIndent())
            config.set("Settings.Port", 31323)
            saveConfig();
        }
        if (!config.contains("Settings.Socket-Client-Data-Code")) {
            config.set("Settings.Socket-Client-Data-Code", "!!WebServer!!")
            saveConfig()
        }

        SocketServer(config.getInt("Settings.Port") ?: 31323).defineSocketConnection()
    }
}