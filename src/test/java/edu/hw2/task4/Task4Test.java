package edu.hw2.task4;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.managers.FaultyConnectionManager;
import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Test of faultyConnection")
    void testCallingInfo() {
        PopularCommandExecutor cmd = new PopularCommandExecutor(new FaultyConnectionManager(), 0);
        try {
            cmd.updatePackages();
        } catch (Exception e) {
            CallingInfo callingInfo = CallingInfo.callingInfo(e.getStackTrace());
            Assertions.assertEquals("edu.hw2.task3.PopularCommandExecutor", callingInfo.className());
            Assertions.assertEquals("tryExecute", callingInfo.methodName());
        }
    }

    @Test
    @DisplayName("Test should return first function that returned exception")
    void testMultipleFunctions() {
        try {
            call1();
        } catch (Exception e) {
            CallingInfo callingInfo = CallingInfo.callingInfo(e.getStackTrace());
            Assertions.assertEquals("call3", callingInfo.methodName());
        }
    }


    private void call1(){
        call2();
    }
    private void call2(){
        call3();
    }
    private void call3(){
        throw new RuntimeException();
    }
}
