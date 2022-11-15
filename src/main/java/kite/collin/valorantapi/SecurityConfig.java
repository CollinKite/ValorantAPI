package kite.collin.valorantapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("collin")
                .password(passwordEncoder().encode("test"))
                .roles("USER");
    }

    //Stop browser from asking for credentials
    public class NoAuthPopup implements AuthenticationEntryPoint {
        @Override
        public void commence(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws java.io.IOException, javax.servlet.ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/agent").permitAll()
                .antMatchers(HttpMethod.GET, "/agent/*").permitAll()
                .antMatchers(HttpMethod.GET, "/weapon").permitAll()
                .antMatchers(HttpMethod.GET, "/weapon/*").permitAll()
                .antMatchers(HttpMethod.GET, "/map").permitAll()
                .antMatchers(HttpMethod.GET, "/map/*").permitAll()
                .anyRequest().authenticated() //All other requests use httpBasic authentication
                .and().csrf().disable() //Disable cross site request forgery checking
                .httpBasic()
                .authenticationEntryPoint(new NoAuthPopup())
                .and().cors();
    }

    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}


