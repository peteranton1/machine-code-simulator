package org.slf4j;

public class Logger {
    public void println(String s) {
        System.out.println(s);
    }

    public void debug(String s) {
        println(s);
    }

    public void info(String s) {
        println(s);
    }

    public void error(String s) {
        println(s);
    }
}

