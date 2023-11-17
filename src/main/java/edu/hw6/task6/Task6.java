package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("RegexpSinglelineJava")
public final class Task6 {

    private Task6() {

    }

    public static ArrayList<String> checkPorts(Map<Integer, String> services) {
        ArrayList<String> info = new ArrayList<>();
        services.forEach((port, service) -> {
            String name = "";
            Protocol protocol = isPortOpen(port);
            if (!protocol.equals(Protocol.NONE)) {
                name = service;
            }
            info.add(String.format("%-10s%-7d%s", protocol, port, name));
        });
        System.out.println("Протокол  Порт   Сервис");
        info.forEach(System.out::println);
        return info;
    }

    public static Protocol isPortOpen(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            try (DatagramSocket ignored = new DatagramSocket(port)) {
                return Protocol.NONE;
            } catch (IOException e) {
                return Protocol.UDP;
            }
        } catch (IOException e) {
            return Protocol.TCP;
        }
    }

    enum Protocol {
        UDP,
        TCP,
        NONE
    }
}
