package accountingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main class
 */
@SpringBootApplication
public class AccountingApplication implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/",
            "classpath:/static/js/", "classpath:/js/",
            "classpath:/resources/static/js/", "classpath:/resources/static/",
            "classpath:/resources/img/", "classpath:/resources/css/"
    };

    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }


}
