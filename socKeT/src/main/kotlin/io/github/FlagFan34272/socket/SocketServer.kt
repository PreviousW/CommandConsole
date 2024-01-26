package io.github.FlagFan34272.socket

import io.github.FlagFan34272.socket.SocketManager.Companion.INSTANCE
import org.bukkit.Bukkit
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.*
import java.util.concurrent.Callable

class SocketServer(private val port: Int = 3000) {
    private val server = ServerSocket(port)

    fun defineSocketConnection() {
        println("SERVER | Socket server was binded on localhost:$port.")
        Bukkit.getScheduler().runTaskTimerAsynchronously(INSTANCE, Runnable {
            try {
                val client = server.accept()
                println("SERVER | Pingged by ${client.inetAddress.hostAddress}!")

                val request = ByteArray(1024)
                val inputStream = client.getInputStream()
                inputStream.read(request)
                val res = request.decodeToString().strip()
                println(res)

                if (res.contains("!!WebServer!!")) { //이 부분을 바꿔 자신만의 로그인 소켓 데이터 생성하기.
                    println("${client.inetAddress.hostAddress} is a valid connect.")
                    val outputStream = client.getOutputStream()
                    outputStream.write("Server connected successfully!".toByteArray())
                    outputStream.flush()

                    Bukkit.getScheduler().runTaskTimerAsynchronously(INSTANCE, Runnable {
                        val command = ByteArray(1024)
                        val inputtedCommand = client.getInputStream()
                        inputtedCommand.read(command)
                        val cmd = command.decodeToString().strip()

                        println("Remote Client Excuted Command: ${cmd}")
                        Bukkit.getScheduler().callSyncMethod(INSTANCE, Callable {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd)
                            return@Callable null
                        })
                    },0L, 1L)

                } else {
                    val outputStream = client.getOutputStream()
                    outputStream.write("It went Something wrong. you are not socket-client device.".toByteArray())
                    outputStream.flush()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, 0L, 1L)
    }



}