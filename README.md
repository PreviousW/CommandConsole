<h1 align="center">
<img src="" alt="logo" width="20%"/>
</h1>

<h2 align="center">SocKetlin Command Console</h2>
<p align="center">easily command everywhere.</p>

## How
### does it work?
It creates a socket server with the default port 31324. When a Python client connects to this server and sends data matching the configuration of "Socket-Client-Data-Code", the Kotlin socket server allows input and sends command socket data to the server. The Kotlin server then receives the socket data and dispatches that command data synchronously.

### to use it?
Simply place the plugin into the /plugins folder, and run it. Then execute the Python client. You can find an example of the Python client in the releases tab, along with the configuration file's header.

## Warning!
Please refrain from using this system as a backdoor.
