package com.jinx.infra.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinx.AppConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
@Import(AntPathMatcher.class)
public class SecurityConfig implements RequestMatcher {

    private final ObjectMapper om;
    private final AppConfiguration configuration;
    private final AntPathMatcher matcher;

    @Override
    public boolean matches(HttpServletRequest request) {
        String ipPattern = request.getRemoteAddr();
        String pathPattern = request.getRequestURI();
        AppConfiguration.Whitelist whitelist = configuration.getWhitelist();

        return whitelist.getIps().stream().anyMatch(ip -> matcher.match(ip, ipPattern))
                || whitelist.getPaths().stream().anyMatch(path -> matcher.match(path, pathPattern));
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 这里控制的是 AuthorizationFilter，仅仅针对这一个拦截器
                // 所以这里配置的放行，也是会经过 defaultSecurityFilterChain 这个拦截器链的
                .authorizeHttpRequests(request -> request
                        .requestMatchers(this).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(configurer -> configurer
                        .failureHandler((request, response, exception) -> {
                            if (exception instanceof InternalAuthenticationServiceException e) {
                                // 内部认证异常
                                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
                            } else {
                                //
                                response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
                            }
                        })
                )
                .exceptionHandling(handing -> handing
                        .authenticationEntryPoint((request, response, exception) ->
                                response.sendError(
                                        HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()
                                )
                        )
                )
                // FilterOrderRegistration 配置了拦截器的默认顺序
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .build();
    }

    /**
     * Web security build callback.
     * <p>
     * WebSecurity 包含 HttpSecurity，配置一个 ignore url，就会新增一个 HttpSecurity 的空 FilterChain 实现放行，
     * 因为 FilterChainProxy 在获取 FilterChain 时是根据 matcher来获取的，然后获取其中的filters进行控制，因为是空的所以直接放行。
     *
     * @return callback
     * @deprecated 该配置会增加 SecurityFilterChain 拦截器链的数量
     */
//    @Bean
    @Deprecated
    public WebSecurityCustomizer webSecurityCustomizer() {
        // webSecurity includes many httpSecurity filters
        // all httpSecurity filters will be ignored if the web is ignored
        // will add a new defaultFilterChain in FilterChainProxy but has no filters
        // FIXME One pattern will create a new FilterChain, the more patterns the more filters.
        return web -> web.ignoring().requestMatchers("/**");
    }
}
