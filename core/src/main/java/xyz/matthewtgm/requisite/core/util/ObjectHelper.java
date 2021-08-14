package xyz.matthewtgm.requisite.core.util;

import java.util.Objects;

public class ObjectHelper {

    /**
     * @param o The object to stringify.
     * @return The stringified object.
     */
    public String stringify(Object o) {
        return o == null ? "" : Objects.toString(o);
    }

}