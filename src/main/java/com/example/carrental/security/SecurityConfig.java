package com.example.carrental.security;

import com.example.carrental.confi.ConfigUsers;
import com.example.carrental.domain.User.CarRentalUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ConfigUsers configUsers;

    private static InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public SecurityConfig(ConfigUsers configUsers) {
        this.configUsers = configUsers;
        this.inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
    }

    public static void addUserSecurity(CarRentalUser user) {
        inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username(user.getUserLogin())
                .password(user.getUserPassword())
                .roles(user.getRole())
                .build());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        for (ConfigUsers.User u : configUsers.getUsersList()) {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username(u.getName())
                    .password(u.getPassword())
                    .roles(u.getRole())
                    .build();
            inMemoryUserDetailsManager.createUser(user);
        }
        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()

                .antMatchers(HttpMethod.GET, "/actuator/**").hasRole("ADMIN")

                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}
