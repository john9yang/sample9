package com.tutorialspoint.greetings;

public class ProcessAPITest {
    public static void main(String[] args) {
        ProcessHandle currProcess = ProcessHandle.current();
        System.out.println("PID:"+currProcess.pid());
        ProcessHandle.Info currProcessInfo = currProcess.info();
        System.out.println("Info:"+currProcessInfo);
    }
}