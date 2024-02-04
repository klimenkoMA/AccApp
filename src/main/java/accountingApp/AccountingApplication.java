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

        /*Starting of index.js script,the part of React.js*/
        try {

            String npmPath = "C:\\npmstart.bat.lnk";
            // D:\JAVA\REPOSITORY\AccApp\frontend\src\training.js
            System.out.println("npm run start:dev");

            //Create a ProcessBuilder object with the command to be executed
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", npmPath);
            // Задаем путь к исполняемому файлу mongod
            String mongoDBPath = "C:\\mongod.exe.lnk";

            //Set the working directory for the command
            processBuilder.directory(new File("C:\\"));

            // Создаем ProcessBuilder
            ProcessBuilder processBuilder2 = new ProcessBuilder("cmd.exe", "/c", mongoDBPath);

            // Запускаем процесс
            Process process2 = processBuilder2.start();

            //Start the process
            Process process = processBuilder.start();

        } catch (IOException
//                | InterruptedException
                e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }


}
