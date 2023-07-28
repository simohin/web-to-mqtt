package ru.simohin.webtomqtt

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    @Value("\${app.username:web-to-mqtt}")
    private val username: String,
    @Value("\${app.password:password}")
    private val password: String
) {
    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        val userDetails = User.withDefaultPasswordEncoder()
            .username(username)
            .password(password)
            .roles("USER")
            .build()
        return MapReactiveUserDetailsService(userDetails)
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http
        .authorizeExchange {
            it.anyExchange().authenticated()
        }
        .formLogin {
            it.disable()
        }
        .httpBasic {  }
        .csrf { it.disable() }
        .build()
}
