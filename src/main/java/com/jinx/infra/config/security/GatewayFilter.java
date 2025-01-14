package com.jinx.infra.config.security;

import com.jinx.AppConfiguration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * @author Jinx
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Import(AntPathMatcher.class)
public class GatewayFilter extends GenericFilterBean {

    private final AppConfiguration configuration;
    private final AntPathMatcher matcher;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request instanceof HttpServletRequest req && response instanceof HttpServletResponse res) {
            String ip = req.getRemoteAddr();
            String path = req.getRequestURI();

            if (isAnyMatch(ip, path)) {
                log.info("ip: {}, path: {} is permitted without filter", ip, path);
            } else {
                filterChain.doFilter(req, res);
            }
        } else {
            throw new ServletException("GatewayFilter only supports HTTP requests");
        }
    }


    private boolean isAnyMatch(String ipPattern, String pathPattern) {
        AppConfiguration.Whitelist whitelist = configuration.getWhitelist();

        return whitelist.getIps().stream().anyMatch(ip -> matcher.match(ip, ipPattern))
                || whitelist.getPaths().stream().anyMatch(path -> matcher.match(path, pathPattern));
    }
}
