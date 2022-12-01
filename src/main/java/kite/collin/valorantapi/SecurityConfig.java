package kite.collin.valorantapi;

import kite.collin.valorantapi.BLL.UserBLL;
import kite.collin.valorantapi.Model.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("user").password(passwordEncoder().encode("test")).roles("USER").build());
        userDetailsList.add(User.withUsername("admin").password(passwordEncoder().encode("test")).roles("ADMIN").build());
        UserBLL ub = new UserBLL();
        for (UserModel u : ub.findAll()) {
            userDetailsList.add(User.withUsername(u.getName()).password(passwordEncoder().encode(u.getPassword())).roles(u.getAccess()).build());
        }

        return new InMemoryUserDetailsManager(userDetailsList);
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
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user/*").permitAll()
                .antMatchers("/user/**").hasRole("ADMIN")
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


