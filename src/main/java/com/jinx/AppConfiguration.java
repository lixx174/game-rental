package com.jinx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "system")
public class AppConfiguration {

    private Whitelist whitelist = new Whitelist();
    private Business business = new Business();


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

    @Getter
    @Setter
    public static class Business{
        private BigDecimal deposit;
    }
}
