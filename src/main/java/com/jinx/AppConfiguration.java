package com.jinx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
public class AppConfiguration {

    private Whitelist whitelist = new Whitelist();


    /**
     * 白名单
     */
    @Getter
    @Setter
    public static class Whitelist {
        /**
         * TODO path support http method
         */
        private Set<String> ips = new HashSet<>(), paths = new HashSet<>();
    }
}
