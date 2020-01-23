package com.hikarikun92.springsecurity.util;

public final class ApplicationUtils {
    private ApplicationUtils() {
        throw new AssertionError();
    }

    public static String quote(String value) {
        return value == null ? "null" : "\"" + value + '"';
    }
}
