package com.jinx.domain;

/**
 * @author Jinx
 */
public interface Query {

    /**
     * Current domain query is required？
     * If false，meaning the query is direct query that don't need to domain transform.
     *
     * @return true：必须
     */
    boolean isRequired();
}
