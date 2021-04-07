package com.backend.cheezeapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource


@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
class WebSecurityConfig(
    private val dataSource: DataSource
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/change_actions").permitAll()
            .anyRequest().permitAll()
            .and().httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("select name, password, enabled from system_user where name=?")
            .authoritiesByUsernameQuery(
                "select su.name, at.actions from" +
                        " system_user su join role r on (su.role_id = r.id)" +
                        " join action_type at on (su.role_id = at.role_id)" +
                        " where su.name=?"
            )
    }

//    @Bean
//    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()
}