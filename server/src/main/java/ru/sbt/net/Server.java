package ru.sbt.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7500);
        while (true) {
            try (Socket client = serverSocket.accept()) {
                OutputStream outputStream = client.getOutputStream();
                outputStream.write("hello world!".getBytes());
                outputStream.flush();
            }
            System.out.println(1);
        }

//        System.out.println("end");
    }
}


