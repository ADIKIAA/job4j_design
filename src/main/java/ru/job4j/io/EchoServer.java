package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    System.out.println(str);
                    str = str.substring(str.indexOf('=') + 1, str.lastIndexOf(' '));
                    switch (str) {
                        case "Hello" :
                            System.out.println("Hello");
                            break;
                        case "Exit" :
                            server.close();
                            break;
                        case "What" :
                            System.out.println("What");
                            break;
                        default:
                            System.out.println(str);
                            break;
                    }
                    out.flush();
                }
            }
        }
    }
}