package ru.sbt.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(host, port);
        Object result;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(method.getName());
            for (Object o : args) {
                oos.writeObject(o);
            }
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            result = ois.readObject();
        } catch (IOException e) {
            result = e;
        }
        return result;
    }
}
