package com.wondernect.elements.context;

import com.wondernect.elements.context.threadlocal.ThreadLocalContextHolderStrategy;

/**
 * application context holder
 */
public class ApplicationContextHolder {

    /** * The strategy name. */
    private static String strategyName;

    /** * The strategy. */
    private static ApplicationContextHolderStrategy strategy;

    static {
        initialize();
    }

    /**
     * Explicitly clears the context value from the current thread.
     */
    public static void clearContext() {
        strategy.clearContext();
    }

    /**
     * Obtain the current ApplicationContext
     */
    public static ApplicationContext getContext() {
        return strategy.getContext();
    }

    /**
     * Initialize.
     */
    private static void initialize() {
        if ((strategyName == null) || "".equals(strategyName)) {
            // Set default
            strategy = new ThreadLocalContextHolderStrategy();
            return;
        }

        // Try to load a custom strategy
        try {
            Class<?> clazz = Class.forName(strategyName);
            strategy = (ApplicationContextHolderStrategy) clazz.newInstance();
        } catch (Exception ex) {
            throw new IllegalStateException("Initialize application context error: " + ex.getMessage());
        }

    }

    /**
     * Associates a new ApplicationContext.
     */
    public static void setContext(ApplicationContext context) {
        strategy.setContext(context);
    }

    /**
     * Changes the preferred strategy.
     */
    public static void setStrategyName(String strategyName) {
        ApplicationContextHolder.strategyName = strategyName;
        initialize();
    }

    /**
     * Allows retrieval of the context strategy.
     */
    public static ApplicationContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    /**
     * Delegates the creation of a new, empty context to the configured strategy.
     */
    public static ApplicationContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

    @Override
    public String toString() {
        return "ApplicationContextHolder(应用全局存储空间)[strategy='" + strategyName + "]";
    }
}
