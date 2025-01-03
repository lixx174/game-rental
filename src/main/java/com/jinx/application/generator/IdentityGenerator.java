package com.jinx.application.generator;

/**
 * Generate universally unique id for schema.
 *
 * @author Jinx
 */
public interface IdentityGenerator {

    /**
     * Generate String id and compatible with table sharding.
     *
     * @param schema table name
     * @return id
     */
    String generate(Schema schema);
}
