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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * @author Jinx
 * @deprecated 该白名单拦截器显得较为鸡肋 因为还是需要继续调用拦截器链 否则无法 servlet.service()
 * 既然都要往后走 不如直接在 defaultSecurityFilterChain 的拦截器中放行（AuthorizationFilter）
 */
@Slf4j
//@Component
@Deprecated
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
                // org.apache.catalina.core.ApplicationFilterChain.internalDoFilter
                // 如果不调用original filterChain则无法走到后面的servlet.service()
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
