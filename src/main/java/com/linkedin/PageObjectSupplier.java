package com.linkedin;

import com.esotericsoftware.reflectasm.ConstructorAccess;

public final class PageObjectSupplier {

    private PageObjectSupplier() {
        throw new IllegalAccessError("Illegal access to private constructor");
    }

    public static <T> T page(Class<T> pageObject) {
        return ConstructorAccess.get(pageObject).newInstance();
    }
}
