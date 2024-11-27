package com.jinx.app.application;

/**
 * Authorization util, offer authentication info.
 *
 * @author Jinx
 */
public class SecuritySupport {

    public static String getUserId() {
        // TODO
        return null;
    }

    public static String getUserIdOrDefault() {
        try {
            return getUserId();
        } catch (Exception e) {
            return "system";
        }
    }
}
