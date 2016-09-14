package ru.sbt.net;

import ru.sbt.net.net.net.CalculatorImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerRegistrator {
    public static void listen(int port, Object impl) {
        String methodName;
        List<Object> args = new ArrayList<>();
        ServerSocket server;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Socket socket = server.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Object o = input.readObject();
            methodName = (String) o;
            for (int i = 0; i < 2; i++) {
                args.add(input.readObject());
            }
            Method method = getMethod(impl, methodName, args);
            Object result = method.invoke(impl, args.toArray());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getMethod(Object impl, String methodName, List<Object> args) throws NoSuchMethodException {
        Class<?> types[] = new Class[args.size()];
        for (int i = 0; i < types.length; i++) {
            types[i] = args.get(i).getClass();
        }
        return impl.getClass().getMethod(methodName, types);
    }

    public static void main(String[] args) {
        ServerRegistrator.listen(5000, new CalculatorImpl());
    }
}