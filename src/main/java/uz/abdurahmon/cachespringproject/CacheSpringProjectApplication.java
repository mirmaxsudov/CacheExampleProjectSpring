package uz.abdurahmon.cachespringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheSpringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheSpringProjectApplication.class, args);
    }
}
