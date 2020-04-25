package com.wondernect.elements.context.threadlocal;

import com.wondernect.elements.context.ApplicationContext;
import com.wondernect.elements.context.ApplicationContextHolderStrategy;

/**
 * thread local context holder
 */
public class ThreadLocalContextHolderStrategy implements ApplicationContextHolderStrategy {

    /** * The Constant context. */
    private static final ThreadLocal<ApplicationContext> CONTEXT = new InheritableThreadLocal<ApplicationContext>();

    @Override
    public void clearContext() {
        CONTEXT.remove();
    }

    @Override
    public ApplicationContext getContext() {
        ApplicationContext ctx = CONTEXT.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            CONTEXT.set(ctx);
        }

        return ctx;
    }

    @Override
    public void setContext(ApplicationContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("Only non-null ApplicationContext instances are permitted");
        }
        CONTEXT.set(ctx);
    }

    @Override
    public ApplicationContext createEmptyContext() {
        return new ThreadLocalContext();
    }
}
