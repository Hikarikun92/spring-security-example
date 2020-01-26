package com.hikarikun92.springsecurity.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

public final class ApplicationUtils {
    private ApplicationUtils() {
        throw new AssertionError();
    }

    public static String quote(String value) {
        return value == null ? "null" : "\"" + value + '"';
    }

    public static <T, R> Set<R> convertSet(Set<? extends T> input, Function<? super T, ? extends R> mapper) {
        final Set<R> converted = new LinkedHashSet<>(input.size());
        input.forEach(o -> converted.add(mapper.apply(o)));
        return converted;
    }
}
