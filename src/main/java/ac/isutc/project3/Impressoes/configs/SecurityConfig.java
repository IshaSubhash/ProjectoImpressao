package ac.isutc.project3.Impressoes.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf(csrf -> csrf
//	                .ignoringRequestMatchers(new AntPathRequestMatcher("/api/users"), new AntPathRequestMatcher("/api/users/login")) // Disable CSRF for registration endpoint
//	            )
//	            .authorizeHttpRequests(authorize -> authorize
//	                .requestMatchers("/api/users", "/api/users/login").permitAll()         // Permit access to registration endpoint
//	                .anyRequest().authenticated()                                               // Require authentication for all other paths
//	            )
//	            .formLogin(withDefaults())
//	            .httpBasic(withDefaults());
//
//	        return http.build();
//	    }

}
