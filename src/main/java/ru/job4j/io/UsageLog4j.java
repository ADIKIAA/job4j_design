package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = false;
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4;
        float f = 5.0f;
        double d = 6.0;
        char c = 'c';
        LOG.debug("Boolean : {}, Byte : {}", bool, b);
        LOG.info("Short : {}, Int : {}", s, i);
        LOG.warn("Long : {}, Float : {}", l, f);
        LOG.error("Double : {}, Char : {}", d, c);
    }
}