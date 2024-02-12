package accountingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class
 */
@SpringBootApplication
public class AccountingApplication implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/",
            "classpath:/static/js/", "classpath:/js/",
            "classpath:/resources/static/js/", "classpath:/resources/static/"
    };

    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);

        /*Starting of index.cjs script,the part of React.js*/
        try {

            //Set the working directory for the command`s
            String npmPath = "C:\\npmstart.bat.lnk";
            String mongoDBPath = "C:\\mongod.exe.lnk";

            //Create a ProcessBuilder objects with the command to be executed
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", npmPath);
            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd.exe", "/c", mongoDBPath);

            //Start the processes
            processBuilder.start();
            processBuilder2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }


}
