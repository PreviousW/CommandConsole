import socket

def main():
    server_address = ('localhost', 31323)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.setsockopt(socket.SOL_SOCKET, socket.SO_KEEPALIVE, 1)
    try:
        client_socket.connect(server_address)
        print(f"CLIENT | connected {server_address}")

        message = "!!WebServer!!"
        client_socket.sendto(message.encode(), server_address)
        print(f"CLIENT | sended {message}")

        data = client_socket.recv(1024)
        print(f"SERVER | {data.decode()}")

        while True:
            print("원하는 마인크래프트 커맨드를 입력하세요.")
            i = input()
            
            print("Sent Successfully! (synchoronous task because of command dispatch.)")
            client_socket.sendto(i.encode(), server_address)
            


    except Exception as e:
        print(f"CLIENT | ERR: {e}")

if __name__ == "__main__":
    main()
