package edu.hw6.task6;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void checkPorts() {
            ArrayList<String> table=Task6.checkPorts();
            assertEquals(6,table.size());
    }
}
