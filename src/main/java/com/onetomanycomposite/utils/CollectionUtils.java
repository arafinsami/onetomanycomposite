package com.onetomanycomposite.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

    public static <T> List<T> distinct(List<T> l) {
        Set<T> set = new LinkedHashSet<>();
        set.addAll(l);
        l.clear();
        l.addAll(set);
        return l;
    }
}
