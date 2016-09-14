package ru.sbt.net;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] bytes = "Hello".getBytes();
        InetAddress address = InetAddress.getLocalHost();
        socket.send(new DatagramPacket(bytes, 0, bytes.length, address, 6000));
    }
}
