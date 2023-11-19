package edu.hw6.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {
    public static final String EPMAP = "EPMAP";
    public static final int EPMAP_PORT = 135;
    public static final int NAME_PORT = 137;
    public static final int DATAGRAM_PORT = 138;
    public static final int SESSION_PORT = 139;
    public static final int POSTGRES_PORT = 5432;
    public static final int MYSQL_PORT = 3306;
    @Test
    void getTable() {
        Map<Integer, String> map = Map.of(
            MYSQL_PORT, "mySql",
            POSTGRES_PORT, "postgres",
            EPMAP_PORT, EPMAP,
            NAME_PORT, "Служба имен NetBIOS",
            DATAGRAM_PORT, "Служба датаграмм NetBIOS",
            SESSION_PORT, "Служба сеансов NetBIOS"
        );
            ArrayList<String> table=Task6.getTable(map);
            assertEquals(map.size(),table.size());
    }
    @Test
    void testAvailability(){
        Map<Integer, String> map = Map.of(
            MYSQL_PORT, "mySql"
        );
        Map<Integer,Task6.Protocol> services=Task6.checkPorts(map);
        assertEquals(Task6.Protocol.TCP,services.get(MYSQL_PORT));
    }
}
