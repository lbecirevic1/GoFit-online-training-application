package com.GoFit.apigateway.Security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

//@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
@EnableReactiveMethodSecurity
public class WebSecurityConfig {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and().cors().configurationSource(request -> {
                   CorsConfiguration cc = new CorsConfiguration();
                    cc.addAllowedOriginPattern( "*" );
                    cc.addAllowedOrigin("http://localhost:3000");
                    cc.addAllowedHeader("*");
                    // If you really want to allow all methods
                     cc.addAllowedMethod( HttpMethod.GET );
                     cc.addAllowedMethod( HttpMethod.DELETE );
                    cc.addAllowedMethod( HttpMethod.PUT );
                    cc.addAllowedMethod( HttpMethod.POST );
                    cc.addAllowedMethod( HttpMethod.OPTIONS );

                    return cc;
                }).and()
                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/user/login", "/user/changePassword", "/user/register").permitAll()
                .pathMatchers("/user/users", "/training/exercise/delete/*", "/training/exercise/put/*", "/training/training/delete/*", "/training/training/put/*").hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().build();


    }
}
