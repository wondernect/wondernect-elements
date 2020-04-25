package com.wondernect.elements.context;

/**
 * application context holder strategy
 */
public interface ApplicationContextHolderStrategy {

    /**
     * Clears the current context.
     */
    void clearContext();

    /**
     * Obtains the current context.
     */
    ApplicationContext getContext();

    /**
     * Sets the current context.
     */
    void setContext(ApplicationContext context);

    /**
     * Creates a new, empty context implementation, usually for the first time.
     */
    ApplicationContext createEmptyContext();
}
