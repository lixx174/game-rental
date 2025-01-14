package com.jinx.infra.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Jinx
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final GatewayFilter gatewayFilter;
    private final ObjectMapper om;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 这里控制的是 AuthorizationFilter，仅仅针对这一个拦截器
                // 所以这里配置的放行，也是会经过 defaultSecurityFilterChain 这个拦截器链的
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
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
                // 将路由拦截器放第一个 这样避免使用webSecurity创建过多的chain
                // 同时 在第一个放行 避免性能损耗（但是 其大多都是OncePerRequestFilter，损耗不严重）
                // FilterOrderRegistration 配置了拦截器的默认顺序
                .addFilterBefore(gatewayFilter, DisableEncodeUrlFilter.class)
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
     */
//    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // webSecurity includes many httpSecurity filters
        // all httpSecurity filters will be ignored if the web is ignored
        // will add a new defaultFilterChain in FilterChainProxy but has no filters
        // FIXME One pattern will create a new FilterChain, the more patterns the more filters.
        return web -> web.ignoring().requestMatchers("/**");
    }
}
