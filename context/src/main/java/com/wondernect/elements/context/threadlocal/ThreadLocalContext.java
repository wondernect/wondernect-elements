package com.wondernect.elements.context.threadlocal;

import com.wondernect.elements.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * thread local context
 */
public class ThreadLocalContext implements ApplicationContext {

    /** * The map. */
    private Map<String, Object> map = new HashMap<String, Object>();

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public void set(String key, Object value) {
        map.put(key, value);
    }
}
