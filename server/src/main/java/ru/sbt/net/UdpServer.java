package ru.sbt.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(6000);
        byte[] buffer = new byte[1024];
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
        server.receive(datagram);

        System.out.println(new String(datagram.getData(), 0, datagram.getLength()));
    }
}
