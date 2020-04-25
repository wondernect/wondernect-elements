package com.wondernect.elements.context;

/**
 * application context
 */
public interface ApplicationContext {

    /**
     * Obtains the currently value specified by key.
     */
    Object get(String key);

    /**
     * Changes the key-value pair in currently thread, or removes value.
     */
    void set(String key, Object value);
}
