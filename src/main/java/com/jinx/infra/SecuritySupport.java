package com.jinx.infra;

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
