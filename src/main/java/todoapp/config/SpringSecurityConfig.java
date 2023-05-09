package todoapp.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//Enables method level security on REST API endpoint methods
@EnableMethodSecurity
public class SpringSecurityConfig {
    //    Injects db service authentication
    @Autowired
    public SpringSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authorize) -> {
//            authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//            authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//            authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
//            authorize.anyRequest().permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
//    In-memory authentication
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails marcus = User.builder()
//                .username("marcus")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(marcus, admin);
//
//    }
}
