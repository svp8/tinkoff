package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("RegexpSinglelineJava")
public final class Task6 {

    public static final String EPMAP = "EPMAP";
    public static final int EPMAP_PORT = 135;
    public static final int NAME_PORT = 137;
    public static final int DATAGRAM_PORT = 138;
    public static final int SESSION_PORT = 139;
    public static final int POSTGRES_PORT = 5432;
    public static final int MYSQL_PORT = 3306;

    private Task6() {

    }

    public static ArrayList<String> checkPorts() {
        Map<Integer, String> map = Stream.of(new Object[][] {
            {MYSQL_PORT, "mySql"},
            {POSTGRES_PORT, "postgres"},
            {EPMAP_PORT, EPMAP},
            {NAME_PORT, "Служба имен NetBIOS"},
            {DATAGRAM_PORT, "Служба датаграмм NetBIOS"},
            {SESSION_PORT, "Служба сеансов NetBIOS"}
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        ArrayList<String> info = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> service = iterator.next();
            boolean inUse = false;
            String protocol = "TCP";
            String name = "";
            int port = service.getKey();
            try (ServerSocket server = new ServerSocket(port)) {
                try (DatagramSocket ignored = new DatagramSocket(port)) {
                    name = "open";
                } catch (IOException e) {
                    inUse = true;
                    protocol = "UDP";
                }
            } catch (IOException e) {
                inUse = true;
            }
            if (inUse) {
                name = map.get(port);
            }
            info.add(String.format("%-10s%-7d%s", protocol, port, name));

        }
        System.out.println("Протокол  Порт   Сервис");
        info.forEach(System.out::println);
        return info;
    }
}
