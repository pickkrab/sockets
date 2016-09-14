package ru.sbt.net;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try(Socket client = new Socket("127.0.0.1", 7500)) {
            byte[] b = new byte[1024];
            int count = client.getInputStream().read(b);
            System.out.println(new String(b, 0, count));
        }

    }
}