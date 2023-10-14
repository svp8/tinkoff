package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {

    public static CallingInfo callingInfo(StackTraceElement[] stackTraceElements) {
        return new CallingInfo(stackTraceElements[0].getClassName(), stackTraceElements[0].getMethodName());
    }
}
