package kite.collin.valorantapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ValorantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValorantApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new
                WebMvcConfigurer() {

                    @Override
                    public void addCorsMappings(CorsRegistry registry) {

                        //this example only allows requests from localhost:8082
                        //registry.addMapping("/**").allowedOrigins("http://localhost:8082/").allowedMethods("*");

                        //this example allows everything
                        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");

                    }
                };
    }

}
